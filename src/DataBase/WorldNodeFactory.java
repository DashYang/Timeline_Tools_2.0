package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import storage.Timenode;
import storage.Worldnode;

import com.mysql.jdbc.Statement;

public class WorldNodeFactory implements Isqltool {
	private Connection conn;
	
	@Override
	public boolean add(int prenodeid,Object item) {
		conn = SQLconnect.getConnection();
		if(item instanceof Worldnode)
		{
			Worldnode newitem = (Worldnode) item;
			ResultSet res;
			int newid = 0;
			String sql = "INSERT INTO `worlds` (`name`, `creator`, `creattime`, `display`,`time_headnode`) VALUES ('%s', '%s', '%s', 'true',0);";
			try {  
	            sql = String.format(sql,newitem.getName(),newitem.getCreator(),newitem.getCreattime());
	            Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
	            System.out.println(sql);
	            int count = st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);  // 执行插入操作的sql语句，并返回插入数据的个数  
	            if(count > 0) {//记录保存成功  
	                res=st.getGeneratedKeys();  
	                if(res.next())  
	                	newid =res.getInt(1);  
	            
	            }  
	            System.out.println("newid " + newid);
	            System.out.println("insert " + count + " entries" + " newid" + newid); //输出插入操作的处理结果  */
	            conn.close();   //关闭数据库连接  
	            int headnode = new TimeNodeFactory().initTimenode(newid);
	            newitem.setTime_headnode(headnode);
	            newitem.setId(newid);
	            update(newitem);
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
		String sql = "UPDATE `worlds` SET `name`='%s', `time_headnode`='%s' WHERE `id`='%s'";
		if (item instanceof Worldnode) {
			Worldnode newitem = (Worldnode) item;
			try {
					sql = String.format(sql, newitem.getName(),
						newitem.getTime_headnode(), newitem.getId());

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
	public ArrayList<Object> show(int id) {
		conn = new SQLconnect().getConnection();
		ArrayList<Object> tns = new ArrayList<Object>();
		try {
			String sql = "select * from worlds where display = 'true'";
			Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
			ResultSet rs = st.executeQuery(sql);  // 执行查询操作的sql语句，并返回插入数据的个数  
			while (rs.next())
			{
				int world_id = rs.getInt("id");
				String name = rs.getString("name");
				String creator = rs.getString("creator");
				String creattime = rs.getString("creattime");
				Worldnode tmp = new Worldnode(world_id, name,creator,creattime);
				tns.add(tmp);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tns;
	}
	
	/*****
	 * 
	 * @param id
	 * @return Headnodeid
	 */
	public int getHeadNode(int id){
		conn = new SQLconnect().getConnection();
		int headnodeId = 0;
		try {
			String sql = "select * from worlds where display = 'true' and id ="+id;
			Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
			ResultSet rs = st.executeQuery(sql);  // 执行查询操作的sql语句，并返回插入数据的个数  
			while (rs.next())
			{
				headnodeId = rs.getInt("time_headnode");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headnodeId;
	}

	@Override
	public boolean delete(int prenodeid, int nownodeid) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Worldnode getWorldnodeByID(int id){
		conn = new SQLconnect().getConnection();
		Worldnode tmp = new Worldnode();
		try {
			String sql = "select * from worlds where display = 'true' and id =" + id;
			Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
			ResultSet rs = st.executeQuery(sql);  // 执行查询操作的sql语句，并返回插入数据的个数  
			while (rs.next())
			{
				int world_id = rs.getInt("id");
				String name = rs.getString("name");
				String creator = rs.getString("creator");
				String creattime = rs.getString("creattime");
				tmp = new Worldnode(world_id, name,creator,creattime);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}
}
