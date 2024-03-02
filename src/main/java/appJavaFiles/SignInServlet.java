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
import jakarta.servlet.http.HttpSession;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
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
			String passwordEntered = req.getParameter("password");
			String role = "";
			String email="";
			String firstName = "";
			String lastName = "";
			
			boolean flag = false;
			
			String query1 = "select * from USERS;";
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				if( rs1.getString("username").equals(username) && rs1.getString("password").equals(passwordEntered) ) {
					flag = true;
					role = rs1.getString("role");
					email = rs1.getString("email");
					firstName = rs1.getString("firstName");
					lastName = rs1.getString("lastName");
					break;
				}
			}
			
			if(flag) {
				
				if(role.equals("Administrator")) {					
					HttpSession session = req.getSession();
					session.setAttribute("username", username);
					session.setAttribute("password", passwordEntered);		
					session.setAttribute("role", role);
					session.setAttribute("email", email);
					session.setAttribute("firstName", firstName);
					session.setAttribute("lastName", lastName);
					
					RequestDispatcher rd = req.getRequestDispatcher("Administrator.jsp");
					rd.forward(req, resp);					
				}
				
				else if(role.equals("Supervisor")) {	
					HttpSession session = req.getSession();
					session.setAttribute("username", username);
					session.setAttribute("password", passwordEntered);		
					session.setAttribute("role", role);
					session.setAttribute("email", email);
					session.setAttribute("firstName", firstName);
					session.setAttribute("lastName", lastName);
					
					RequestDispatcher rd = req.getRequestDispatcher("Supervisor.jsp");
					rd.forward(req, resp);					
				}
				
				else if(role.equals("FlatResident")) {	
					HttpSession session = req.getSession();
					session.setAttribute("username", username);
					session.setAttribute("password", passwordEntered);		
					session.setAttribute("role", role);
					session.setAttribute("email", email);
					session.setAttribute("firstName", firstName);
					session.setAttribute("lastName", lastName);
					
					RequestDispatcher rd = req.getRequestDispatcher("FlatResident.jsp");
					rd.forward(req, resp);
				}
			}
			else {
				req.setAttribute("page", "index.jsp");
				req.setAttribute("message", "Username or Password does Not match. Please Enter Correct Details.");
				req.setAttribute("pageName", "Sign In Page");
				
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
