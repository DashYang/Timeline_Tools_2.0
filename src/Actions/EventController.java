package Actions;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import storage.Eventnode;

import DataBase.EventNodeFactory;
import Serverlet.IprocessHttpRequest;

public class EventController implements IprocessHttpRequest {

	@Override
	public boolean process(HttpServletRequest request) {
		String event_description ="";
		try {
			request.setCharacterEncoding("UTF-8");
			event_description = new String(request.getParameter("event_description").getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int edtnodeid= Integer.parseInt(request.getParameter("edtnodeid"));
		System.out.println(edtnodeid + " " + event_description);
		Eventnode item = new Eventnode();
		item.setId(edtnodeid);
		item.setEvent_description(event_description);
		new EventNodeFactory().update(item);
		return false;
	}

}
