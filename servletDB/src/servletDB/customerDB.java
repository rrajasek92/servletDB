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
@WebServlet("/customerDB")
public class customerDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customerDB() {
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
	      out.println( "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">" + "</head>");	

	      try {
	    	  
	    	  result = retrieveInfo(); 
	    	  out.println("<table class=\"table table-striped table table-bordered table table-hover\">");
	    	while(result.next()){
	    		String custid=result.getString("customer_id");
			out.println("<tr><td><a href=\"http://localhost:8080/servletDB/custDetail?custid=" +custid+"\">" + result.getString("cust_first_name")+" "+result.getString("cust_last_name") + "</a></td></tr>");
			
	    	}
	    	out.println("</table>");
	    	out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script><script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script></body>");
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
	public static ResultSet retrieveInfo() throws ClassNotFoundException 
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
	 
			String sql ="select customer_id,cust_first_name,cust_last_name from demo_customers";
						
			
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
