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

@WebServlet("/BuildingDetails")
public class AdminstratorBuildingDetails extends HttpServlet {
	
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
			
			int buildingNumber = Integer.parseInt(req.getParameter("number"));
			int numberOfFloors = Integer.parseInt(req.getParameter("floorNumber"));
			int flatsPerFloor = Integer.parseInt(req.getParameter("flatFloorNumber"));
			
			
			if(numberOfFloors>=100) {
				req.setAttribute("page", "BuildingDetails.jsp");
				req.setAttribute("message", "Number Of Floors cannot be greater than 99!!");
				req.setAttribute("pageName", "Building Details Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			if(flatsPerFloor>=100) {
				req.setAttribute("page", "BuildingDetails.jsp");
				req.setAttribute("message", "Number Of Flats Per floor cannot be greater than 99!!");
				req.setAttribute("pageName", "Building Details Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			
			boolean flag = true;
			
			String query1 = "select * from BUILDING;";
			PreparedStatement st1 = con.prepareStatement(query1);
			ResultSet rs1 = st1.executeQuery();
			
			while(rs1.next()) {
				if(rs1.getInt("number")==buildingNumber) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				
				String query2 = "INSERT INTO BUILDING VALUES(?,?,?,?);";
				PreparedStatement st2 = con.prepareStatement(query2);
				st2.setInt(1, buildingNumber);
				st2.setInt(2, numberOfFloors);
				st2.setInt(3, flatsPerFloor);
				st2.setString(4, req.getParameter("adminUsername"));
				
				st2.executeUpdate();
				

				RequestDispatcher rd = req.getRequestDispatcher("BuildingDetails.jsp");
				rd.include(req, resp);
				out.println("<h1 style='text-align:center;'> SUCCESSFULLY INSERTED THE BUILDING DETAILS </h1>");
				
			}
			else {
				req.setAttribute("page", "BuildingDetails.jsp");
				req.setAttribute("message", "Building With same Number already exist. Please enter a new Building Number!!");
				req.setAttribute("pageName", "Building Details Page");
				
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
