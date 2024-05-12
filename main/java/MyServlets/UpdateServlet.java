package MyServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		//doGet(request, response);
		HttpSession session = request.getSession();
		String ename = session.getAttribute("ename").toString();
		String econtact = request.getParameter("econtact").toString();
		String ecity = request.getParameter("ecity");
		String esalary = (String)request.getParameter("esalary");
		ArrayList<Employee> array = new ArrayList<>();

		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhumika", "root", "");
			System.out.print(conn + "JDBC Connection Successfull");
			
			PreparedStatement ps = conn.prepareStatement("Update EmployeeDetails Set Contact=?, City=?,Salary=? where Name=?");
			ps.setString(1, econtact);
			ps.setString(2, ecity);
			ps.setString(3, esalary);
			ps.setString(4, ename);
			
			int rowsaffected = ps.executeUpdate();
			System.out.print("Data Sucessfull updated for " + ename + "\n" + rowsaffected + "rows  affected");
			
			//retrieving data
			Statement st = conn.createStatement();
			String query = "Select * from EmployeeDetails";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				Employee E = new Employee();
				E.name = rs.getString("Name");
				E.contact = rs.getBigDecimal("contact");
				E.city=rs.getString("City");
				E.salary = rs.getBigDecimal("salary");
				array.add(E);
			}
			
			conn.close();
			
			RequestDispatcher rd = request.getRequestDispatcher("Display.jsp");
			request.setAttribute("data", array);
			rd.forward(request, response);
			
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
