<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	<style>
		.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}


.dropdown:hover .dropdown-content {
  display: block;
}
	
	</style>



</head>
<body>

	<nav class="navbar navbar-expand-lg fixed-top" style="background-color: #e3f2fd;">
  		<div class="container-fluid">
  			
    		<span class="navbar-brand" >
      			<img style="border-radius:20px;" src="./images/logo.jpg" alt="image" width="70" height="70">
    		</span>
  			
  			
    		<span class="navbar-brand mb-0 h1" style="font-size:30px;">Society Security Application</span>
		    <div class="collapse navbar-collapse" id="navbarText">
			    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
			      
			      <li class="nav-item" style="margin:0 46%;">
			        <a class="nav-link active" aria-current="page" href="Administrator.jsp" style="font-size:20px;">Home</a>
			      </li>			  
			      
			      <li class="nav-item" style="margin:0 46%;">
			        <a class="nav-link active" aria-current="page" href="BuildingDetails.jsp" style="font-size:20px;">Building Details</a>
			      </li>
			      
			      <li class="nav-item" style="margin:0 46%;">
			        <a class="nav-link active" aria-current="page" href="FlatDetails.jsp" style="font-size:20px;">Flat Details</a>
			      </li>
			     
			    </ul>
		        	<i class="fa-sharp fa-solid fa-door-open" style="height:15px;width:15px;margin-right:15px;"></i>
			        <a class="nav-link active d-flex" aria-current="page" href="AdministratorSignOut" style="font-size:20px;">SignOut</a>
 				   
		    </div>
  		</div>
	</nav>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/a0b4a376bd.js" crossorigin="anonymous"></script>

</body>
</html>