package appJavaFiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regularVisitorSignIn")
public class SupervisorRegularVisitorSignIn extends HttpServlet {
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
			
			String code = req.getParameter("code");
			String username = req.getParameter("username");
			
			boolean flag = false;
			
			String query1 = "select * from regularVisitor;";
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				if( rs1.getString("securityCode").equals(code) && rs1.getString("supervisorUsername").equals(username) ) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				req.setAttribute("page", "SupervisorRegularVisitor.jsp");
				req.setAttribute("message", "User with given security code does not exist. Please complete the registration process for the user!!");
				req.setAttribute("pageName", "Visitor Login Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			Calendar c = Calendar.getInstance();
			String checkInTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) ;
			
			String query2 = "UPDATE regularVisitor SET checkInTime=?,checkOutTime=\"00:00\" WHERE securityCode=? AND supervisorUsername=? ;";
			PreparedStatement st2 = con.prepareStatement(query2);
			st2.setString(1, checkInTime);
			st2.setString(2, code);
			st2.setString(3, username);
			
			st2.executeUpdate();
			
			RequestDispatcher rd = req.getRequestDispatcher("SupervisorRegularVisitor.jsp");
			rd.forward(req, resp);
			
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
		
	}
}
