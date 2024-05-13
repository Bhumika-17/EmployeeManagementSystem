package MyServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateRetrieveServlet
 */
public class UpdateRetrieveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRetrieveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = (String) request.getParameter("name");
		System.out.println("Name passed in servlet " + name);
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhumika", "root", "");
			System.out.println(conn);
			
			//retrieving data of that student
			PreparedStatement ps = conn.prepareStatement("Select * from EmployeeDetails where Name=?");
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			Employee E = new Employee();
			while(rs.next()){
				E.name = rs.getString("Name");
				E.contact = rs.getBigDecimal("contact");
				E.city=rs.getString("City");
				E.salary = rs.getBigDecimal("salary");
				System.out.println(E.name);

			}
			
			RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
			request.setAttribute("data", E);
			rd.forward(request, response);
			conn.close();
			
		}catch(Exception e ) {
			e.printStackTrace();
			System.out.println("JDBC Connection Error");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
