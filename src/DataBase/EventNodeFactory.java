package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import storage.Eventnode;
import storage.Timenode;

import com.mysql.jdbc.Statement;

public class EventNodeFactory implements Isqltool{
	private Connection conn;
	
	public EventNodeFactory(){
	}
	
	@Override
	public boolean add(int prenodeid,Object item) {
		conn = new SQLconnect().getConnection();
		String sqlcommand = "INSERT INTO `Eventnodes` (`owner`, `description`, `display`) VALUES ('%s', '%s', '%s');";
		if(item instanceof Eventnode)
		{
			Eventnode newitem = new Eventnode();
			newitem = (Eventnode)item;
			try {  
	            String sql = String.format(sqlcommand,newitem.getOwner(),newitem.getEvent_description(),"true"  );

	            Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
	            
	            System.out.println(sql);
	            int count = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数  
	            System.out.println("insert " + count + " entries"); //输出插入操作的处理结果  
	            conn.close();   //关闭数据库连接  
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
		conn = new SQLconnect().getConnection();
		String sqlcommand = "UPDATE `Eventnodes` SET `description`='%s' WHERE `owner`='%s';";
		if(item instanceof Eventnode)
		{
			Eventnode newitem = new Eventnode();
			newitem = (Eventnode)item;
			try {  
	            	String sql = String.format(sqlcommand,newitem.getEvent_description(),newitem.getId());

	            	Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
	            
	            	System.out.println(sql);
	            	int count = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数  
	            	System.out.println("insert " + count + " entries"); //输出插入操作的处理结果  
	            	conn.close();   //关闭数据库连接  
	            	return true;
	        } catch (SQLException e) {  
	            System.out.println("插入数据失败 " + e.getMessage());  
	            return false;
	        }
		}
		return false;
	}

	public boolean delete(Object item) {
		conn = new SQLconnect().getConnection();
		String sqlcommand = "UPDATE `Timeline_Database`.`Eventnodes` SET `display`='false' WHERE `id`='%s';";
		if(item instanceof Eventnode)
		{
			Eventnode newitem = new Eventnode();
			newitem = (Eventnode)item;
			try {  
					String sql = String.format(sqlcommand,newitem.getId());
					Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
					System.out.println(sql);
					int count = st.executeUpdate(sql);  // 执行插入操作的sql语句，并返回插入数据的个数  
					System.out.println("delete " + count + " entries"); //输出插入操作的处理结果
					conn.close();
					return true;
			}catch (SQLException e) {
				System.out.println("删除数据失败 " + e.getMessage());
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
			String sql = "select * from Eventnodes where display = 'true' and owner = "+ id + ";";
			Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
			ResultSet rs = st.executeQuery(sql);  // 执行查询操作的sql语句，并返回插入数据的个数  
			while (rs.next())
			{
				int event_id = rs.getInt("id");
				int owner = rs.getInt("owner");
				String description = rs.getString("description");
				
				Eventnode tmp = new Eventnode(owner, description);
				tmp.setId(event_id);
				
				tns.add(tmp);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tns;
	}

	@Override
	public boolean delete(int prenodeid, int nownodeid) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void initEventnode(int owner){
		Eventnode node = new Eventnode();
		node.setDisplay("true");
		node.setEvent_description("暂无描述");
		node.setOwner(owner);
		add(owner, node);
	}
}
