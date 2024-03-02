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

@WebServlet("/maintainenceChargeStatus")
public class SupervisorMaintainenceChargeStatus extends HttpServlet{
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
			
			int flatNumber = Integer.parseInt(req.getParameter("flatNumber"));
			int buildingNumber = Integer.parseInt(req.getParameter("buildingNumber"));
			
			String query1 = "select * from maintainenceCharge;";
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				if(rs1.getInt("flatNumber")==flatNumber && rs1.getInt("buildingNumber")==buildingNumber) {
					
					String query2 = "UPDATE maintainenceCharge SET status=\"Paid\" WHERE flatNumber=? AND buildingNumber=? ;";
					PreparedStatement st2 =con.prepareStatement(query2);
					st2.setInt(1, flatNumber);
					st2.setInt(2, buildingNumber);
					
					st2.executeUpdate();
					
					break;
				}
			}

			
			RequestDispatcher rd = req.getRequestDispatcher("SupervisorMaintainenceChargeLogs.jsp");
			rd.forward(req, resp);
			
			
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
		
	}
}
