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

@WebServlet("/FlatDetails")
public class AdminstratorFlatDetails extends HttpServlet {
	
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
			String flatNumberString = req.getParameter("flatNumber");
			
			int buildingNumber = Integer.parseInt(req.getParameter("buildingNumber"));
			
			String username = req.getParameter("person");
			int bhk = Integer.parseInt(req.getParameter("bhk"));
			double maintaienceCharge = Double.parseDouble(req.getParameter("charge"));
			double cost = Double.parseDouble(req.getParameter("cost"));
			String imageUrl = req.getParameter("image");
			
			
			String query = "select * from BUILDING;";
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			
			boolean x = false;
			int flatsPerFloor=0;
			int numberOfFloors=0;
			
			while(rs.next()) {
				if( rs.getInt("number")==buildingNumber && rs.getString("buildingAdmin").equals(req.getParameter("adminUsername")) ) {
					x = true;
					flatsPerFloor = rs.getInt("flatsPerFloor");
					numberOfFloors = rs.getInt("numberOfFloors");
					break;
				}
			}
			
			if(!x) {
				req.setAttribute("page", "FlatDetails.jsp");
				req.setAttribute("message", "Entered Building Number does Not Exist!!");
				req.setAttribute("pageName", "Flat Details Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			
			if(flatNumberString.length()<=2 || flatNumberString.length()>=5 ) {
				req.setAttribute("page", "FlatDetails.jsp");
				req.setAttribute("message", "Invalid Flat Details Has been entered. Please Enter Flat Number within the range!!");
				req.setAttribute("pageName", "Flat Details Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			if(flatNumberString.length()==3) {
				int floor = flatNumberString.charAt(0) - '0';
				int flat = Integer.parseInt(flatNumberString.substring(1,3));
				
				if(floor>numberOfFloors || flat>flatsPerFloor) {
					req.setAttribute("page", "FlatDetails.jsp");
					req.setAttribute("message", "Invalid Flat Number Has been entered. Please Enter within the range!!");
					req.setAttribute("pageName", "Flat Details Page");
					
					RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
					rd.forward(req, resp);
					
					return;
				}
			}
			
			if(flatNumberString.length()==4) {
				int floor = Integer.parseInt(flatNumberString.substring(0,2));
				int flat = Integer.parseInt(flatNumberString.substring(2,4));
				
				if(floor>numberOfFloors || flat>flatsPerFloor) {
					req.setAttribute("page", "FlatDetails.jsp");
					req.setAttribute("message", "Invalid Flat Number Has been entered. Please Enter within the range!!");
					req.setAttribute("pageName", "Flat Details Page");
					
					RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
					rd.forward(req, resp);
					
					return;
				}
			}
			
			String query2 = "select * from FLAT;";
			PreparedStatement st2 = con.prepareStatement(query2);
			ResultSet rs2 = st2.executeQuery();
			
			boolean y = false;
			
			while(rs2.next()) {
				if(rs2.getInt("flatNumber")==flatNumber && rs2.getInt("flatBuildingNumber")==buildingNumber) {
					y = true;
					break;
				}
			}
			
			if(y) {
				req.setAttribute("page", "FlatDetails.jsp");
				req.setAttribute("message", "Flat Number "+flatNumber+" in the Building Number "+buildingNumber+" has already been allocated. Please allocate some other flat!!");
				req.setAttribute("pageName", "Flat Details Page");
				
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
				
				return;
			}
			
			String query1 = "INSERT INTO FLAT VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement st1 = con.prepareStatement(query1);
			
			st1.setInt(1, flatNumber);
			st1.setInt(2, buildingNumber);
			st1.setString(3, username);
			st1.setInt(4, bhk);
			st1.setDouble(5, maintaienceCharge);
			st1.setDouble(6, cost);
			st1.setString(7, imageUrl);
			st1.setString(8, req.getParameter("adminUsername"));
			
			st1.executeUpdate();
			
			RequestDispatcher rd = req.getRequestDispatcher("FlatDetails.jsp");
			rd.include(req, resp);
			out.println("<br><br><br><br><br><br><br><br><br><h1 style='text-align:center;'> SUCCESSFULLY INSERTED THE FLAT DETAILS </h1>");
			
			
		}catch(Exception e) {
			RequestDispatcher rd = req.getRequestDispatcher("serverError.html");
			rd.forward(req, resp);
			e.printStackTrace();
		}
	}

}
