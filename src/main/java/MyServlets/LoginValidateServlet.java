package MyServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginValidateServlet
 */
public class LoginValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String unm = request.getParameter("unm");
		String pwd = request.getParameter("pwd");
		String contextunm = getServletContext().getInitParameter("unm");
		String initpwd = getServletConfig().getInitParameter("pwd");
		
		//unm=admin & pwd=1234
		
		if(unm.equals(contextunm) && pwd.equals(initpwd)){
			//RequestDispatcher rd = request.getRequestDispatcher("AddData.jsp");
			//request.setAttribute("username", unm);
			//rd.forward(request, response);
			
			HttpSession session = request.getSession();
			session.setAttribute("username", unm);
			response.sendRedirect("AddData.jsp");
		}
		else {
			response.getWriter().print("Invalid Credentials");
		}
	}

}
