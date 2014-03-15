package Actions;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import storage.Timenode;

import DataBase.TimeNodeFactory;
import Serverlet.HttpRequest;
import Serverlet.IprocessHttpRequest;

public class TimeController implements IprocessHttpRequest{
	
	private boolean Addtimenode1(HttpServletRequest request) {
		String time_description ="";
		try {
			request.setCharacterEncoding("UTF-8");
			time_description = new String(request.getParameter("time_description").getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int prenodeid = Integer.parseInt(request.getParameter("prenodeid"));
		int worldid = Integer.parseInt(request.getParameter("worldid"));
		Timenode item = new Timenode(0, time_description, worldid, 0, "true");
		new TimeNodeFactory().add(prenodeid, item);
		return false;
	}
	
	private boolean Deletetimenode(HttpServletRequest request) {
		int prenodeid = Integer.parseInt(request.getParameter("fathernodeid"));
		int nownodeid = Integer.parseInt(request.getParameter("prenodeid"));
		new TimeNodeFactory().delete(prenodeid,nownodeid);
		return false;
	}
	
	private boolean Edittimenode(HttpServletRequest request) {
		String time_description ="";
		try {
			request.setCharacterEncoding("UTF-8");
			time_description = new String(request.getParameter("time_description").getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int edtnodeid = Integer.parseInt(request.getParameter("edtnodeid"));
		new TimeNodeFactory().update(edtnodeid,time_description);
		return false;
	}
	@Override
	public boolean process(HttpServletRequest request) {
		String type = request.getParameter("type");
		HttpRequest req = Enum.valueOf(HttpRequest.class, type.trim());
		switch (req) {
		case addtimenode:
			Addtimenode1(request);
			break;
		case deletetimenode:
			Deletetimenode(request);
			break;
		case edittimenode:
			Edittimenode(request);
			break;
		default:
			break;
		}
		return false;
	}

}
