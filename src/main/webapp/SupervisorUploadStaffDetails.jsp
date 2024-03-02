<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Staff Details</title>

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
	
	<%@ include file="./header/SupervisorHeader.jsp" %>

	<div class="bg">
		<br><br><br><br><br>
		
		<div class="main">
        	<div class="card">
        	
        		<div class="card-body">
	            	<h1 style="text-decoration:underline; font-family:sans-serif;display:inline-block;">FOR UPDATING INFORMATION FILL THE FOLLOWING FORM</h1>
	            	<i class="fa fa-pen fa-xs edit"></i>
	            	<form action="uploadStaffDetails" method="post">
		                <table>
		                    <tbody>
		                        <tr>
									<th style="font-size:25px;"><label for="firstName">First Name </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="text" name="firstName" id="firstName" required ></td>
		                        </tr>
		                        <tr>
									<th style="font-size:25px;"><label for="lastName">Last Name </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="text" name="lastName" id="lastName" required ></td>
		                        </tr>
		                         <tr>
									<th style="font-size:25px;"><label for="phone">Phone Number </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="number" min="1" name="phone" id="phone" required ></td>
		                        </tr>		                   		                         
		                        <tr>
									<th style="font-size:25px;"><label for="occupation">Occupation </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input style="width:322px;" type="text" name="occupation" id="occupation" required ></td>
		                        </tr>
		                        <tr>
									<th style="font-size:25px;"><label for="salary">Salary(Monthly) </label></th>
		                            <td style="font-size:25px;">:</td>
		                            <td style="font-size:25px;"><input type="number" min="1" name="salary" id="salary" ></td>
		                        </tr>	                    		                        				
		                    </tbody>
		                </table>
		                <input style="margin-top:1%;" type="Submit" value="Submit" id="submit_btn">
               			
           			<input type="hidden" name="username" value="<%= (String) session.getAttribute("username") %>">
           			</form>				
           		</div>
        
        	
        	</div>
        </div>	
		
	</div>
	
	<script>
  let firstName = document.getElementById('firstName');
  let lastName = document.getElementById('lastName');

  let submit_btn = document.getElementById('submit_btn');

  submit_btn.addEventListener('click',function(e){
  
  	let flag1 = 1;
  	let flag2 = 1;
   	
   	for(let i=0;i<firstName.value.length;i++){
   		if(firstName.value.charCodeAt(i)==32){
   			flag1=0;
   			break;
   		}
   	}
   	
   	for(let i=0;i<lastName.value.length;i++){
   		if(lastName.value.charCodeAt(i)==32){
   			flag2=0;
   			break;
   		}
   	}
   	

   	
   	if(!flag1){
   		alert("Cannot give spaces in first name");
   		submit_btn.setAttribute('type','button')
   		return;
   	}
   	
   	if(!flag2){
   		alert("Cannot give spaces in last name");
   		submit_btn.setAttribute('type','button')
   		return;
   	}

  	
  	submit_btn.setAttribute('type','submit')
  	
  })

  
  
  </script>
	
</body>
</html>