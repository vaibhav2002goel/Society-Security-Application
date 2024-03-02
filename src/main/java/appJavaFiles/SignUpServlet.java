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

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
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
			
			String username = req.getParameter("username");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String email = req.getParameter("email");
			String role = req.getParameter("role");
			String passwordEntered = req.getParameter("password");
			
			
			boolean flag1 = true;
			boolean flag2 = true;
			String query1 = "select * from USERS;";
			
						
			PreparedStatement st1_1 = con.prepareStatement(query1);
			ResultSet rs1_1 = st1_1.executeQuery();
			while(rs1_1.next()) {
				if(rs1_1.getString("username").equals(username) && rs1_1.getString("password").equals(passwordEntered)) {
					flag1 = false;
					break;
				}
			}
			
			if(!flag1) {
				req.setAttribute("page", "signUp.jsp");
				req.setAttribute("message", "The person with the entered username and password Already Exists. Please enter a different username or password!!");
				req.setAttribute("pageName", "Sign Up Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			
			
			
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			while(rs1.next()) {
				if(rs1.getString("username").equals(username) && rs1.getString("role").equals(role)) {
					flag2 = false;
					break;
				}
			}
			
			if(flag2) {
				String query2 = "INSERT INTO USERS VALUES(?,?,?,?,?,?);";
				PreparedStatement st2 = con.prepareStatement(query2);
				st2.setString(1, username);
				st2.setString(2, firstName);
				st2.setString(3, lastName);
				st2.setString(4, email);
				st2.setString(5, role);
				st2.setString(6, passwordEntered);
				
				st2.executeUpdate();
				
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req, resp);
			}
			else {
				req.setAttribute("page", "signUp.jsp");
				req.setAttribute("message", "The person with the entered username and role Already Exists. Please enter a different username or role!!");
				req.setAttribute("pageName", "Sign Up Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
			}
			
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
		
	}
}
