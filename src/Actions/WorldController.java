package Actions;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import storage.Timenode;
import storage.Worldnode;

import DataBase.TimeNodeFactory;
import DataBase.WorldNodeFactory;
import Serverlet.IprocessHttpRequest;

public class WorldController implements IprocessHttpRequest {

	@Override
	public boolean process(HttpServletRequest request) {
		String worldname = "";
		try {
			request.setCharacterEncoding("UTF-8");
			worldname = new String(request.getParameter(
					"worldname").getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("worldname:"  + worldname);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		Worldnode node = new Worldnode(0, worldname, "admin", time);
		new WorldNodeFactory().add(0, node);
		return false;
	}

}
