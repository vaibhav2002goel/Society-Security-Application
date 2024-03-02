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

@WebServlet("/normalVisitorDetails")
public class SupervisorNormalVisitorDetails extends HttpServlet {
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
			
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String phone = req.getParameter("phoneNumber");
			int flatNumber = Integer.parseInt(req.getParameter("flatNumber"));
			String supervisorUsername = req.getParameter("supervisorUsername");
			String residentUsername = "";
			int buildingNumber = Integer.parseInt(req.getParameter("buildingNumber"));
			
			boolean flag = false;
			
			String query1 = "select * from FLAT;";
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				if(rs1.getInt("flatNumber")==flatNumber && rs1.getInt("flatBuildingNumber")==buildingNumber) {
					flag = true;
					residentUsername = rs1.getString("allocatedPersonUsername");
					break;
				}
			}
			
			if(!flag) {
				req.setAttribute("page", "SupervisorNormalVisitor.jsp");
				req.setAttribute("message", "There is no flat resident in the entered flat number. Please enter the correct flat number!!");
				req.setAttribute("pageName", "Visitor Details Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			String query2 = "INSERT INTO normalVisitor VALUES(?,?,?,?,?,?,?,?);";
			PreparedStatement st2 = con.prepareStatement(query2);
			
			st2.setString(1, firstName);
			st2.setString(2, lastName);
			st2.setString(3, phone);
			st2.setInt(4, flatNumber);
			st2.setString(5, supervisorUsername);
			st2.setString(6, residentUsername);
			st2.setInt(7, buildingNumber);
			st2.setString(8, "Pending");
			
			st2.executeUpdate();
			
			RequestDispatcher rd = req.getRequestDispatcher("SupervisorNormalVisitor.jsp");
			rd.forward(req, resp);
			
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
		
	}
}
