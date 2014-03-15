package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Actions.WorldController;

/**
 * Servlet implementation class ImportantAffairHandler
 */
public class ImportantAffairHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportantAffairHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		HttpRequest req = Enum.valueOf(HttpRequest.class, type.trim());
		System.out.println("importantaffair " + req);
		String url = "2.0/index.jsp";
		System.out.println(session.getAttribute("user"));
		if(session.getAttribute("user") != null && session.getAttribute("user").equals("admin")){
			switch (req) {
			case addworldnode:
				IprocessHttpRequest iRequest = new WorldController();
				System.out.println("addworldnode process");
				iRequest.process(request);
				url = request.getHeader("Referer");
				response.sendRedirect(url);
				break;
			default:
				break;
			}
		}else {
			response.sendRedirect(url);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
