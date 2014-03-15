package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;


import Tools.PropertiesReader;

import com.mysql.jdbc.Statement;

/***
 * to get SQl connenction
 * @author dash
 *
 */
public class SQLconnect {
//	private String driver = "com.mysql.jdbc.Driver";
//	private String url = "jdbc:mysql://127.0.0.1:3306/Timeline_Database?useUnicode=true&characterEncoding=utf8";
//	private String user = "root";
//	private String pwd = "123456";

	private static String driver ;
	private static String url ;
	private static String user;
	private static String pwd;
	
	public static Connection getConnection() {
		try {
			driver = PropertiesReader.getValue("driver");
			url = PropertiesReader.getValue("url");
			user = PropertiesReader.getValue("user");
			pwd = PropertiesReader.getValue("pwd");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			// load driver
			Class.forName(driver);
			// link to database
			Connection conn = DriverManager.getConnection(url, user, pwd);
			if (!conn.isClosed())
			{
				System.out.println("Succeeded connecting to the Database!");
				return conn;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}