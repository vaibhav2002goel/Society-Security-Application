<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png">
<title>Maintainence Charge Payment Page</title>

	<style>  
  	.bg {
		  background-image: url("./images/DBMS.jpeg");
		  min-height: 100vh;
		  height: auto; 
/*  		  opacity: 0.5;  */
		  z-index:1;
		
		  background-position: center;
		  background-repeat: no-repeat;
		  background-size: cover;
	}
	</style>

</head>
<body>

	
	<%@ include file="./header/FlatResidentHeader.jsp" %>

	<div class="bg">
		<br><br><br><br><br><br><br>
		
		<%@ page import="java.io.IOException" %>
	    <%@ page import="java.sql.Connection" %>
	    <%@ page import ="java.sql.DriverManager" %>
	    <%@ page import ="jakarta.servlet.ServletException" %>
	    <%@ page import ="jakarta.servlet.http.HttpServlet" %>
	    <%@ page import ="jakarta.servlet.http.HttpServletRequest" %>
	    <%@ page import ="jakarta.servlet.http.HttpServletResponse" %>
	    <%@ page import ="java.sql.PreparedStatement" %>
	    <%@ page import ="java.sql.ResultSet" %>
	    <%@ page import ="jakarta.servlet.RequestDispatcher" %>
	    
	    <%!
			String url = "jdbc:mysql://localhost:3306/SOCIETY?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "demo";
			String password = "password";			
	    %>
		
		<table class="table table-striped" style="width:80%; margin:auto; ">
		  <thead>
		    <tr class="table-success">
		      <th scope="col">SERIAL NO.</th>
		      <th scope="col">FLAT NUMBER</th>
		      <th scope="col">BUILDING NUMBER</th>
		      <th scope="col">MAINTAINENCE CHARGE</th>
		      <th scope="col">MODE OF PAYMENT</th>
		      <th scope="col">DUE DATE</th>
		      <th scope="col">STATUS</th>		      
		    </tr>
		  </thead>
		  <tbody class="table-group-divider">
			  
			  
	    <%
		    try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("In jdbc dricer error");
				e.printStackTrace();
			}
			try(Connection con = DriverManager.getConnection(url,user,password)) {
			
		    		String username = (String) session.getAttribute("username");
					String query1 = "select * from maintainenceCharge where residentUsername=? ;";
					
					PreparedStatement st1 = con.prepareStatement(query1);
					st1.setString(1,username);
					
					ResultSet rs1 = st1.executeQuery();
					
					int i = 1;
		%>
		
							
		
		<%
					while(rs1.next()){	
		%>	
					    <tr>
					      <th scope="row"> <%= i %> </th>
					      <td><%= rs1.getInt("flatNumber") %></td>
					      <td><%= rs1.getInt("buildingNumber") %></td>
					      <td><%= rs1.getDouble("charge") %></td>
					      <td> 
					      		<%= rs1.getString("modeOfPayment") %>						        
					      </td>
					      <td> 
					      		<%= rs1.getString("dueDate") %>						        
					      </td>
					      <td><%= rs1.getString("status") %></td>	
				   		</tr>
		    
	   			<% 
	   			i++;
					}
				%>
		    
		  </tbody>
		<%
			}
			catch(Exception e){ 
				RequestDispatcher rd = request.getRequestDispatcher("serverError.html");
				rd.forward(request, response);
				e.printStackTrace();
			}
		%>			
		</table>
	
		
	</div>

</body>
</html>