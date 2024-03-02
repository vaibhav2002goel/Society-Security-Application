<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png">
<title>Supervisor Home Page</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  
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
	
	#items{
		display:flex;
		flex-wrap:wrap;
 		justify-content:center; 
 		width:100%;
		height:auto;
		margin-top:1%;
		padding:4px;
		
	}
	
	#img1{
		height: 50%;
		width:10vw;
		margin:0 auto;
	}
	
	.h1_1 {
	  position: relative;
	  padding: 0;
	  margin: 0;
	  font-family: "Raleway", sans-serif;
	  font-weight: 300;
	  font-size: 40px;
	  color: #080808;
	  -webkit-transition: all 0.4s ease 0s;
	  -o-transition: all 0.4s ease 0s;
	  transition: all 0.4s ease 0s;
	}
	
	.h1_1 span {
	  display: block;
	  font-size: 0.5em;
	  line-height: 1.3;
	}
	.h1_1 em {
	  font-style: normal;
	  font-weight: 600;
	}
	
	.seven .h1_1 {
	  position:relative; font-size:20px; font-weight:700;  letter-spacing:0px; text-transform:uppercase; width:500px; 
	  text-align:center; margin-left:30px; white-space:nowrap; border:2px solid #222;padding:5px 11px 3px 11px;
	  border-width:thick;
	}
	.seven .h1_1:before, .seven .h1_1:after {
	    background-color: #c50000;
	    position:absolute; 
	    content: '';
	    height: 20px;
	
	    width: 20px; border-radius:50%;
	    bottom: 40px;
	}
	.seven .h1_1:before {
	   left:-30px;
	}
	.seven .h1_1:after {
	   right:-30px;
	}
	
  
  </style>


</head>
<body>
	
	<%@ include file="./header/SupervisorHeader.jsp" %>

	<div class="bg">
		<br><br><br><br><br>
<%-- 		<h1>** <%= (String) session.getAttribute("firstName") %> <%= (String) session.getAttribute("lastName") %> </h1> --%>
		
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
			String query1 = "select * from BUILDING;";
			String query2 = "select * from FLAT;";
	    %>
	    
	    <%    	
		    try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try(Connection con = DriverManager.getConnection(url,user,password)) {
								
				PreparedStatement st1 = con.prepareStatement(query1);
				ResultSet rs1 = st1.executeQuery();
		%>
			<h1>BUILDING DETAILS</h1>
			<div id="items">
		<%		
					while(rs1.next()){
		%>
			
					<div style="margin:0 1vw;" class="card w-25 text-bg-light mb-3 border-secondary " style="width: 18rem;">
<%-- 					  <img id="img1" src="<%= rs1.getString("imageUrl") %>" class="card-img-top" alt="Product Image"> --%>
					  <div class="card-body">
					    <h5 class="card-title"><%= rs1.getString("buildingAdmin") %></h5>
					    <p class="card-text"> BUILDING NUMBER : <%= rs1.getInt("number") %></p>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item">NUMBER OF FLOORS : <b><%= rs1.getDouble("numberOfFloors") %></b> </li>
					  </ul>
					  <div class="card-body">
					  <p class="card-link"> FLATS PER FLOOR : <b><%= rs1.getString("flatsPerFloor") %></b>  </p>
					  </div>	  
		       		</div>
				
		<%
				}
		%>		
			</div>
		
		<% 
				PreparedStatement st2 = con.prepareStatement(query2);
				ResultSet rs2 = st2.executeQuery();
		%>
		<h1>FLAT DETAILS</h1>
		<div id="items">
		<% 
			while(rs2.next()){ 
		%>
				<div style="margin:0 1vw;" class="card w-25 text-bg-light mb-3 border-secondary " style="width: 18rem;">
					  <img id="img1" src="<%= rs2.getString("imageUrl") %>" class="card-img-top" alt="Product Image">
					  <div class="card-body">
					    <h5 class="card-title"><%= rs2.getString("allocatedPersonUsername") %></h5>
					    <p class="card-text"> FLAT NUMBER : <%= rs2.getInt("flatNumber") %></p>
					  </div>
					  <ul class="list-group list-group-flush">
					    <li class="list-group-item">BUILDING NUMBER : <b><%= rs2.getInt("flatBuildingNumber") %></b></li>
					    <li class="list-group-item">BHK : <b><%= rs2.getString("BHK") %></b></li>
					    <li class="list-group-item">MAINTENANCE CHARGES : <b><%= rs2.getDouble("maintenanceCharges") %></b> </li>
					    <li class="list-group-item">FLAT PRICE : <b><%= rs2.getDouble("costOfBuying") %></b> </li>
					  </ul>
					  <div class="card-body">
					  <p class="card-link"> FLAT ADMINSTRATOR : <b><%= rs2.getString("flatAdmin") %></b>  </p>
					  </div>	  
		       		</div>
		       <%} %>
		
		</div>
		<%
			}catch(Exception e){
				RequestDispatcher rd = request.getRequestDispatcher("serverError.html");
				rd.forward(request, response);
				e.printStackTrace();
			}
	    
	    %>		
	</div>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/a0b4a376bd.js" crossorigin="anonymous"></script>
	
</body>
</html>