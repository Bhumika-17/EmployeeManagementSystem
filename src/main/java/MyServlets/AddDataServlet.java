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

/**
 * Servlet implementation class AddDataServlet
 */
public class AddDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDataServlet() {
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
		String ename = request.getParameter("ename");
		String econtact = request.getParameter("econtact").toString();
		String ecity = request.getParameter("City");
		String esalary = (String)request.getParameter("esalary");
		ArrayList<Employee> array = new ArrayList<>();
		System.out.println(ename+econtact+ecity+esalary);
		
		Connection conn = null;
		
		try {
			getClass().forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/bhumika", "root", "");
			System.out.print(conn + "JDBC Connection Successfull\n");
			
			//inserting data
			PreparedStatement ps = conn.prepareStatement("Insert into EmployeeDetails values (?,?,?,?)");
			ps.setString(1, ename);
			ps.setString(2, econtact);
			ps.setString(3, ecity);
			ps.setString(4, esalary);
			int rowsaffected = ps.executeUpdate();
			System.out.println("Data Sucessfully inserted\n" + rowsaffected + " rows affected");
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
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("JDBC Connection Error");
		}
		
		
	}

}
