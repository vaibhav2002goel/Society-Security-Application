<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png">
<title>Flat Resident Page</title>

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
		<br><br><br><br><br>
		<h1> <%= (String) session.getAttribute("firstName") %> <%= (String) session.getAttribute("lastName") %> </h1>
		
	</div>

</body>
</html>