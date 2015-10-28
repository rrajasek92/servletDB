package servletDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class custDB
 */
@WebServlet("/custDetail")
public class custDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public custDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ResultSet result = null;
		
	      out.println("<head><title>" + "Customer Name" + "</title>");	
	      out.println( "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">" + "</head><body>");	

	      try {
	    	  String custid=request.getParameter("custid");
	    	  
	    	  result = retrieveInfo(custid); 
	    	  out.println("<table class=\"table table-striped table table-bordered table table-hover \">");
	    	while(result.next()){
			out.println("<tr><td>" + result.getString("cust_first_name")+" "+result.getString("cust_last_name") + "</td><td>"+result.getString("customer_id")+"</td><td>"+result.getString("cust_city")+"</td><td>"+result.getString("cust_state")+"</td></tr>");
			
	    	}
	    	out.println("</table>");
	 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public static ResultSet retrieveInfo(String custid) throws ClassNotFoundException 
	{ 
		try 
	{ 
	//URL of Oracle database server 
			String url = "jdbc:oracle:thin:system/password@localhost"; 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//properties for creating connection to Oracle database 
			Properties props = new Properties(); 
			props.setProperty("user", "TestUserDB"); 
			props.setProperty("password", "password"); 
	 
	 //creating connection to Oracle database using JDBC 
			Connection conn = DriverManager.getConnection(url,props); 
	 
			String sql ="select * from demo_customers where customer_id="+custid;
						
			
	 //creating PreparedStatement object to execute query 
			PreparedStatement preStatement = conn.prepareStatement(sql); 
	 
			ResultSet result = preStatement.executeQuery();
			return result;
		 
		
		}catch(SQLException e) 
		{ 
			
			System.out.println(e.getMessage()); 
			
			e.printStackTrace();
			return null;
		}}

}