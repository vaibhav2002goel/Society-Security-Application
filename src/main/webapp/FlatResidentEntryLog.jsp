<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png">
<title>Visitor's Log</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">


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
		<br><br><br><br><br><br><br><br><br><br>
		
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
		      <th scope="col">Serial No.</th>
		      <th scope="col">VISITOR FIRST NAME</th>
		      <th scope="col">VISITOR LAST NAME</th>
		      <th scope="col">VISITOR PHONE NUMBER</th>
		      <th scope="col">CURRENT STATUS</th>
		      <th scope="col">APPROVE VISITOR ENTRY</th>
		      <th scope="col">DECLINE VISITOR ENTRY</th>
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
					String query1 = "select * from normalVisitor where residentUsername=?";
					
					PreparedStatement st1 = con.prepareStatement(query1);
					st1.setString(1,username);
					
					ResultSet rs1 = st1.executeQuery();
					
					int i = 1;
					while(rs1.next()){	
		%>	
					    <tr>
					      <th scope="row"> <%= i %> </th>
					      <td><%= rs1.getString("visitorFirstName") %></td>
					      <td><%= rs1.getString("visitorLastName") %></td>
					      <td> 
					      		<%= rs1.getString("visitorPhoneNumber") %>						        
					      </td>
					      <td><%= rs1.getString("status") %></td>					  
					      
					      <td>
					      	<form style="margin:0 60px;" action="FlatResidentEntryApproval?type=approve&firstName=<%= rs1.getString("visitorFirstName") %>&lastName=<%= rs1.getString("visitorLastName") %>&username=<%= (String) session.getAttribute("username") %>" method="post">						   
						      	<input type="submit" value="Approve"> 
						    </form>							      
					      </td>
					      
					      <td>
					      	<form style="margin:0 60px;" action="FlatResidentEntryApproval?type=decline&firstName=<%= rs1.getString("visitorFirstName") %>&lastName=<%= rs1.getString("visitorLastName") %>&username=<%= (String) session.getAttribute("username") %>" method="post">						   
						      	<input type="submit" value="Decline"> 
						    </form>	
					      </td>
					      
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
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>