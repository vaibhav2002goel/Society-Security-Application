<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="shortcut icon" type="image/x-icon" href="./images/logo.png">
  <title>Flat Details</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
  
  
  <style>
  
  	.bg {
		  background-image: url("./images/DBMS.jpeg");
		
		  height: 100vh; 
/*  		  opacity: 0.5;  */
		  z-index:1;
		
		  background-position: center;
		  background-repeat: no-repeat;
		  background-size: cover;
	}
	
	
* {
    margin: 0;
}


.main {
    margin-top: 5%;
    margin-left: 25%;
    font-size: 28px;
    padding: 0 10px;
    width: 58%;
}

.main h1 {
    color: black;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 24px;
    margin-bottom: 10px;
}

.main .card {
    background-color: #fff;
    border-radius: 18px;
    box-shadow: 1px 1px 8px 0 grey;
    height: auto;
    margin-bottom: 20px;
    padding: 20px 0 20px 50px;
}

.main .card table {
    border: none;
    font-size: 16px;
    height: 270px;
    width: 80%;
}

	
  
  </style>
  
</head>
	
<body>

	<%@ include file="./header/AdministratorHeader.jsp" %>
	<div class="bg">
		<br><br><br><br><br>
		<div class="main">
        	<div class="card">
        	
        		<div class="card-body">
	            	<h1 style="text-decoration:underline; font-family:sans-serif;display:inline-block;">FOR UPDATING INFORMATION FILL THE FOLLOWING FORM</h1>
	            	<i class="fa fa-pen fa-xs edit"></i>
	            	<form action="FlatDetails" method="post">
		                <table>
		                    <tbody>
		                    	<tr>
									<th style="font-size:25px;"><label for="image">Flat Image URL </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="text" name="image" id="image" required></td>
		                        </tr>
		                    	<tr>
									<th style="font-size:25px;"><label for="flatNumber">Flat Number </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="number" min="1" name="flatNumber" id="flatNumber" required ></td>
		                        </tr>		                 
		                        <tr>
									<th style="font-size:25px;"><label for="buildingNumber">Building Number </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="number" min="1" name="buildingNumber" id="buildingNumber" required ></td>
		                        </tr>
		                         <tr>
									<th style="font-size:25px;"><label for="person">Allocated Person Username </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="text" name="person" id="person" required ></td>
		                        </tr>
		                        <tr>
									<th style="font-size:25px;"><label for="bhk">BHK </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="number" min="1" name="bhk" id="bhk" required ></td>
		                        </tr>
		                        <tr>
									<th style="font-size:25px;"><label for="charge">Maintaience Charge </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="number" min="1" name="charge" id="charge" required ></td>
		                        </tr>
		                        <tr>
									<th style="font-size:25px;"><label for="cost">Flat Price </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="number" min="1" name="cost" id="cost" required ></td>
		                        </tr>		                        				
		                    </tbody>
		                </table>
		                <input style="margin-top:1%;" type="Submit" value="Submit" id="submit_btn">
               			
           			<input type="hidden" name="adminUsername" value="<%= (String) session.getAttribute("username") %>">
           			</form>				
           			
           		</div>
        
        	
        	</div>
        </div>
	</div>


</body>
</html>