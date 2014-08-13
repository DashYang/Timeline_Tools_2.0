package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import storage.Eventnode;
import storage.Imagenode;

import com.mysql.jdbc.Statement;

public class ImageFactory implements Isqltool {
	
	private Connection conn;
	
	@Override
	public boolean add(int prenodeid, Object item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int prenodeid, int nownodeid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Object> show(int id) {
		conn = SQLconnect.getConnection();
		ArrayList<Object> tns = new ArrayList<Object>();
		try {
			String sql = "SELECT * FROM hero_image limit %d,%d";
			sql = String.format(sql, id , id +20);
			Statement st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象  
			ResultSet rs = st.executeQuery(sql);  // 执行查询操作的sql语句，并返回插入数据的个数  
			while (rs.next())
			{
				int image_id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String url = rs.getString("url");
				String md5 = rs.getString("md5");
				String source = rs.getString("source");
				
				Imagenode tmp = new Imagenode(image_id , title , content , url ,md5 , source);
				tns.add(tmp);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tns;
	}
}
