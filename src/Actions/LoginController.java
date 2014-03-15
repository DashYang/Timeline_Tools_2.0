package Actions;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Serverlet.IprocessHttpRequest;
import Tools.MD5Util;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import DataBase.SQLconnect;

public class LoginController implements IprocessHttpRequest{
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginController() {
	}
	
	public LoginController(String username, String password) {
		super();
		this.username = username;
		this.password = MD5Util.MD5(password);
	}

	public boolean login() {
		boolean flag = false;
		String sql = "SELECT * FROM db99527iu6w4ye96.users where username = '"+ username+"'";
		System.out.println(sql);
		Connection conn = (Connection) SQLconnect.getConnection();
		try {
			Statement st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象
			ResultSet rs = st.executeQuery(sql); // 执行查询操作的sql语句，并返回插入数据的个数
			while (rs.next()) {
				String sqlpassword = rs.getString("password");
				System.out.println(sqlpassword);
				System.out.println(password);
				if(sqlpassword.equals(password)){
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean process(HttpServletRequest request) {
		username = request.getParameter("username");
		password = MD5Util.MD5(request.getParameter("password"));
		if(login())
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			return true;
		}
		else {
			return false;
		}
	}
}
