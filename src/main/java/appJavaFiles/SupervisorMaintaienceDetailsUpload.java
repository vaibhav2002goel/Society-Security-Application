package appJavaFiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/maintaienceChargeUpload")
public class SupervisorMaintaienceDetailsUpload extends HttpServlet{
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
			
			int buildingNumber = Integer.parseInt(req.getParameter("buildingNumber"));
			int flatNumber = Integer.parseInt(req.getParameter("flatNumber"));
			String status = req.getParameter("status");
			String username = req.getParameter("username");
			String modeOfPayment = req.getParameter("modeOfPayment");
			double charge = 0;
			
			
			String dueDate = req.getParameter("dueDate");			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dueDate_DateFormat = dateFormat.parse(dueDate);
            java.sql.Date sqlDueDate = new java.sql.Date(dueDate_DateFormat.getTime());             
			
			
			boolean flag = false;
			String residentUsername = "";
			String query1 = "select * from FLAT ;";
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				if(rs1.getInt("flatBuildingNumber")==buildingNumber && rs1.getInt("flatNumber")==flatNumber) {
					residentUsername = rs1.getString("allocatedPersonUsername");
					charge = rs1.getDouble("maintenanceCharges");
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				req.setAttribute("page", "supervisorMaintainenceUpload.jsp");
				req.setAttribute("message", "Entered Flat Details does not exist. Please enter the correct Details!!");
				req.setAttribute("pageName", "Maintaience Charge Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			String query2 = "INSERT INTO maintainenceCharge VALUES(?,?,?,?,?,?,?,?) ;";
			PreparedStatement st2 = con.prepareStatement(query2);
			st2.setInt(1, flatNumber);
			st2.setInt(2,buildingNumber);
			st2.setString(3, username);
			st2.setString(4,status);
			st2.setString(5, residentUsername);
			st2.setDate(6,sqlDueDate);
			st2.setDouble(7, charge);
			st2.setString(8,modeOfPayment);
			
			st2.executeUpdate();

			RequestDispatcher rd = req.getRequestDispatcher("supervisorMaintainenceUpload.jsp");
			rd.forward(req, resp);
			
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
		
	}
}
