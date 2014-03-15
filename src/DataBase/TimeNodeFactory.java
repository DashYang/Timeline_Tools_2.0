package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import storage.Timenode;

import com.mysql.jdbc.Statement;

public class TimeNodeFactory implements Isqltool {
	private Connection conn;

	public TimeNodeFactory() {
	}

	@Override
	public boolean add(int prenodeid, Object item) {
		Timenode preitem = getTimenode(prenodeid); // 不然会关闭数据库链接
		System.out.println("preid " + preitem.getId());
		conn = SQLconnect.getConnection();
		if (item instanceof Timenode) {
			Timenode newitem = new Timenode();
			newitem = (Timenode) item;
			int newid = 0;
			ResultSet res;
			try {
				String sql = String
						.format("INSERT INTO Timenodes (`nextnode`, `time_description`, `time_owner`, `display`,`event_headnode`)"
								+ " VALUES ('%s', '%s', '%s','%s','%s')",
								preitem.getNextnode(),
								newitem.getTime_description(),
								newitem.getTime_owner(), "true", 0);
				Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象
				System.out.println(sql);
				int count = st.executeUpdate(sql,
						Statement.RETURN_GENERATED_KEYS); // 执行插入操作的sql语句，并返回插入数据的个数
				if (count > 0) {// 记录保存成功
					res = st.getGeneratedKeys();
					if (res.next())
						newid = res.getInt(1);

				}
				System.out.println("newid " + newid);
				System.out.println("insert " + count + " entries" + " newid"
						+ newid); // 输出插入操作的处理结果 */
				conn.close(); // 关闭数据库连接
				preitem.setNextnode(newid);
				update(preitem);
				new EventNodeFactory().initEventnode(newid);
				return true;
			} catch (SQLException e) {
				System.out.println("插入数据失败 " + e.getMessage());
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean update(Object item) {
		conn = SQLconnect.getConnection();
		String sqlcommand = "UPDATE Timenodes SET `nextnode`='%s', `time_description`='%s' ,`display`='%s' WHERE `id`='%s'";
		if (item instanceof Timenode) {
			Timenode newitem = new Timenode();
			newitem = (Timenode) item;
			try {
				String sql = String.format(sqlcommand, newitem.getNextnode(),
						newitem.getTime_description(), newitem.getDisplay(),
						newitem.getId());

				Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象

				System.out.println(sql);
				int count = st.executeUpdate(sql); // 执行插入操作的sql语句，并返回插入数据的个数
				System.out.println("insert " + count + " entries"); // 输出插入操作的处理结果
				conn.close(); // 关闭数据库连接
				return true;
			} catch (SQLException e) {
				System.out.println("插入数据失败 " + e.getMessage());
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean delete(int prenodeid, int nownodeid) {
		boolean flag = false;
		System.out.println(prenodeid + " " + nownodeid);
		Timenode prenode = getTimenode(prenodeid);
		Timenode nownode = getTimenode(nownodeid);
		prenode.setNextnode(nownode.getNextnode());
		nownode.setDisplay("false");
		if (update(nownode) && update(prenode))
			flag = true;
		return flag;
	}

	@Override
	public ArrayList<Object> show(int id) {
		conn = new SQLconnect().getConnection();
		ArrayList<Object> tns = new ArrayList<Object>();
		try {
			String sql = "select * from Timenodes where display = 'true' and time_owner = "
					+ id;
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象
			ResultSet rs = st.executeQuery(sql); // 执行查询操作的sql语句，并返回插入数据的个数
			while (rs.next()) {
				int time_id = rs.getInt("id");
				int nextnode = rs.getInt("nextnode");
				String t_description = rs.getString("time_description");
				int owner = rs.getInt("time_owner");
				int event_headnode = rs.getInt("event_headnode");
				Timenode tmp = new Timenode(nextnode, t_description, owner,
						event_headnode, "true");
				tmp.setId(time_id);
				tns.add(tmp);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSorted(id, tns);
	}

	/****
	 * 
	 * @param id
	 *            specific timenode_id
	 * @return timenode info
	 */
	public Timenode getTimenode(int id) {
		conn = new SQLconnect().getConnection();
		Timenode node = new Timenode();
		try {
			String sql = "select * from Timenodes where display = 'true' and id = "
					+ id;
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象
			ResultSet rs = st.executeQuery(sql); // 执行查询操作的sql语句，并返回插入数据的个数
			while (rs.next()) {
				int time_id = rs.getInt("id");
				int nextnode = rs.getInt("nextnode");
				String t_description = rs.getString("time_description");
				int owner = rs.getInt("time_owner");
				int event_headnode = rs.getInt("event_headnode");
				node = new Timenode(time_id, nextnode, t_description, owner,
						event_headnode, "true");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return node;
	}

	public Timenode getnode(int id, ArrayList<Object> list) {
		Timenode node = new Timenode();
		for (Object tmp : list) {
			node = (Timenode) tmp;
			if (node.getId() == id)
				return node;
		}
		return node;
	}

	public ArrayList<Object> getSorted(int id, ArrayList<Object> list) {
		int nowid = new WorldNodeFactory().getHeadNode(id);
		ArrayList<Object> result = new ArrayList<Object>();
		while (nowid != 0) {
			Timenode nextnode = getnode(nowid, list);
			result.add(nextnode);
			nowid = nextnode.getNextnode();
		}
		return result;
	}

	public boolean update(int id, String description) {
		Timenode node = getTimenode(id);
		node.setTime_description(description);
		if (update(node))
			return true;
		else {
			return false;
		}
	}

	public int initTimenode(int owner) {
		Timenode node = new Timenode();
		node.setDisplay("true");
		node.setEvent_headnode(0);
		node.setNextnode(0);
		node.setTime_description("世界诞生");
		node.setTime_owner(owner);
		int newid = 0;
		conn = SQLconnect.getConnection();
		ResultSet res;
		try {
			String sql = String
					.format("INSERT INTO Timenodes (`nextnode`, `time_description`, `time_owner`, `display`,`event_headnode`)"
							+ " VALUES ('%s', '%s', '%s','%s','%s')",
							node.getNextnode(), node.getTime_description(),
							node.getTime_owner(), "true", 0);
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象
			System.out.println(sql);
			int count = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); // 执行插入操作的sql语句，并返回插入数据的个数
			if (count > 0) {// 记录保存成功
				res = st.getGeneratedKeys();
				if (res.next())
					newid = res.getInt(1);

			}
			System.out.println("newid " + newid);
			System.out.println("insert " + count + " entries" + " newid"
					+ newid); // 输出插入操作的处理结果 */
			conn.close(); // 关闭数据库连接
			new EventNodeFactory().initEventnode(newid);
		} catch (SQLException e) {
			System.out.println("插入数据失败 " + e.getMessage());
		}
		return newid;
	}
}
