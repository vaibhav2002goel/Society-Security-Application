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

@WebServlet("/regularVisitorUpload")
public class SupervisorRegularVisitorDetailsUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			String code = req.getParameter("code");
			int flatNumber = Integer.parseInt(req.getParameter("flatNumber"));
			int buildingNumber = Integer.parseInt(req.getParameter("buildingNumber"));
			
			String username = req.getParameter("username");
			
			Calendar c = Calendar.getInstance();
			String checkInTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) ;	
			
			String query = "select * from FLAT";
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			
			boolean flag = false;
			
			while(rs.next()) {
				if(rs.getInt("flatNumber")==flatNumber && rs.getInt("flatBuildingNumber")==buildingNumber) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				req.setAttribute("page", "SupervisorRegularVisitorSignUp.jsp");
				req.setAttribute("message", "The entered flat details does not exist. Please enter the correct details!!");
				req.setAttribute("pageName", "Registration Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			String query1 = "INSERT INTO regularVisitor VALUES(?,?,?,?,?,?,?,?,?);";
			PreparedStatement st1 = con.prepareStatement(query1);
			
			st1.setString(1, firstName);
			st1.setString(2, lastName);
			st1.setString(3, phone);
			st1.setString(4, code);
			st1.setString(5, username);
			st1.setInt(6, flatNumber);
			st1.setInt(7, buildingNumber);
			st1.setString(8, checkInTime);
			st1.setString(9, "00:0");
			
			st1.executeUpdate();
			
			RequestDispatcher rd = req.getRequestDispatcher("SupervisorRegularVisitorSignUp.jsp");		
			rd.forward(req, resp);
		
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
		
	}
}
