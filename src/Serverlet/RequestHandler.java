package Serverlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.catalina.Session;
import org.apache.coyote.Request;

import Actions.EventController;
import Actions.LoginController;
import Actions.TimeController;

/**
 * Servlet implementation class RequestHandler
 */
public class RequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		HttpRequest req = Enum.valueOf(HttpRequest.class, type.trim());
		IprocessHttpRequest iRequest = null;
		HttpSession session = request.getSession();
		String url = "2.0/index.jsp";
		System.out.println(req);
		switch (req) {
		case login:
			String vcode = (String) session.getAttribute("rand");
			String svcode = (String) request.getParameter("verifycode");
//			System.out.println(vcode + " " + svcode);
			if(vcode.equals(svcode))
			{
				iRequest = new LoginController();
				if(iRequest.process(request)){
					System.out.println("success");
				}else {
					System.out.println("failed");
				}
				response.sendRedirect("2.0/index.jsp");
			}else {
				response.sendRedirect("2.0/login.jsp");
			}
			break;
		case addtimenode:
			iRequest = new TimeController();
			iRequest.process(request);
			url = request.getHeader("Referer");
			response.sendRedirect(url);
			break;
		case deletetimenode:
			iRequest = new TimeController();
			iRequest.process(request);
			url = request.getHeader("Referer");
			response.sendRedirect(url);
			break;
		case edittimenode:
			iRequest = new TimeController();
			iRequest.process(request);
			url = request.getHeader("Referer");
			response.sendRedirect(url);
			break;
		case editeventnode:
			iRequest = new EventController();
			iRequest.process(request);
			url = request.getHeader("Referer");
			response.sendRedirect(url);
			break;
		case logout:
			session.setAttribute("user", "");
			url = request.getHeader("Referer");
			response.sendRedirect(url);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
