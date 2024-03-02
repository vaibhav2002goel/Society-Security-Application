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

@WebServlet("/regularVisitorCheckOutTime")
public class SupervisorRegularVisitorCheckoutTime extends HttpServlet {
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
			
			System.out.println(req.getParameter("code"));
			System.out.println(req.getParameter("username"));
			System.out.println(req.getParameter("checkOutTime"));
			
			String code = req.getParameter("code");
			String username = req.getParameter("username");
			String checkOutTime = req.getParameter("checkOutTime");
			
			
//			boolean flag = false;
//			String checkInTime = "";
//			String query = "select * from regularVisitor;";
//			PreparedStatement st = con.prepareStatement(query);
//			st.setString(1, code);
//			st.setString(2, username);
//			ResultSet rs = st.executeQuery();
//			
//			while(rs.next()) {
//				if(rs.getString("securityCode").equals(code) && rs.getString("supervisorUsername").equals(username)) {
//					checkInTime = rs.getString("checkInTime");
//					break;
//				}
//			}
//			
//			String[] inTimes = checkInTime.split(":");
//			int inTimeHour = Integer.parseInt(inTimes[0]);
//			int inTimeMinute = Integer.parseInt(inTimes[1]);
//			
//			String[] outTimes = checkOutTime.split(":");
//			int outTimeHour = Integer.parseInt(outTimes[0]);
//			int outTimeMinute = Integer.parseInt(outTimes[1]);
//			
//			if(inTimeHour>outTimeHour) {
//				req.setAttribute("page", "SupervisorRegularVisitorLogs.jsp");
//				req.setAttribute("message", "Check-Out Time cannot be less than than Check-In time.");
//				req.setAttribute("pageName", "Logs Page");
//				
//				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
//				rd.forward(req, resp);
//				
//				return;				
//			}
			
			
			
			String query1 = "UPDATE regularVisitor SET checkOutTime=? WHERE securityCode=? AND supervisorUsername=? ;";
			PreparedStatement st1 = con.prepareStatement(query1);
			st1.setString(1, checkOutTime);
			st1.setString(2, code);
			st1.setString(3, username);
			
			st1.executeUpdate();
			
			RequestDispatcher rd = req.getRequestDispatcher("SupervisorRegularVisitorLogs.jsp");
			rd.forward(req, resp);
	
			
		}catch(Exception e) {
			
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
			
		}
		
	}
}
