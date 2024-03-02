package appJavaFiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FlatResidentEntryApproval")
public class FlatResidentEntryApproval extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String url = "jdbc:mysql://localhost:3306/SOCIETY?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "demo";
		String password = "password";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection(url,user,password)) {
			
//			System.out.println(req.getParameter("firstName"));
//			System.out.println(req.getParameter("lastName"));
//			System.out.println(req.getParameter("username"));
//			System.out.println(req.getParameter("type"));
			
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String username = req.getParameter("username");
			String type = req.getParameter("type");
			
			String query1 = "select * from normalVisitor;";
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				if( rs1.getString("visitorFirstName").equals(firstName) && rs1.getString("visitorLastName").equals(lastName) && rs1.getString("residentUsername").equals(username) ) {
					
					if(type.equals("approve")) {
						String query2 = "UPDATE normalVisitor SET status = \"approve\" WHERE visitorFirstName=\""+firstName+"\" AND visitorLastName=\""+lastName+"\" AND residentUsername=\""+username+"\" ;";  
						PreparedStatement st2 = con.prepareStatement(query2);
						
						
						st2.executeUpdate();
					}
					else {
						String query2 = "UPDATE normalVisitor SET status = \"decline\" WHERE visitorFirstName=\""+firstName+"\" AND visitorLastName=\""+lastName+"\" AND residentUsername=\""+username+"\" ;";  
						PreparedStatement st2 = con.prepareStatement(query2);
						
						st2.executeUpdate();
					}
					break;
				}
			}
			
			RequestDispatcher rd = req.getRequestDispatcher("FlatResidentEntryLog.jsp");
			rd.forward(req, resp);
			
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
		
	}
}
