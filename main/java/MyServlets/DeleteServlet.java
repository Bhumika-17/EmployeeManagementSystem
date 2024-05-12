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
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String name = request.getParameter("name").toString();
		ArrayList<Employee> array = new ArrayList<>();

		
		Connection conn = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhumika", "root", "");
			System.out.println(conn + " JDBC Connection Successfull!\n");
			
			PreparedStatement ps = conn.prepareStatement("Delete from EmployeeDetails where Name=?");
			ps.setString(1, name);
			int rowsAffected = ps.executeUpdate();
			System.out.println(name + "Data Deleted Sucessfully! " + rowsAffected + " rows affected");
			
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
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
