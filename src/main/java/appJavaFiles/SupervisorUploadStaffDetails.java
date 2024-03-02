package appJavaFiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/uploadStaffDetails")
public class SupervisorUploadStaffDetails extends HttpServlet {
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
			String phone = req.getParameter("phone");
			String occupation = req.getParameter("occupation");
			double salary = Double.parseDouble(req.getParameter("salary"));
			String username = req.getParameter("username");
			
			String query1 = "INSERT INTO staffDetails VALUES(?,?,?,?,?,?);";
			PreparedStatement st1 = con.prepareStatement(query1);
			st1.setString(1, firstName);
			st1.setString(2, lastName);
			st1.setString(3, phone);
			st1.setString(4, occupation);
			st1.setDouble(5, salary);
			st1.setString(6, username);
			
			st1.executeUpdate();
			
			RequestDispatcher rd = req.getRequestDispatcher("SupervisorUploadStaffDetails.jsp");
			rd.forward(req, resp);
			
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
	}
}
