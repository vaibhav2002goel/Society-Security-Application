<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png">
  <title>Regular Visitor Registration</title>
<link rel="stylesheet" href="./css/SignUpCSS.css">

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
	
	#category{
	    height: auto;
	    width: 50%;
	}

	#category-heading{
	    margin-block-start: 0;
	    margin-block-end: 0;
	    color: grey;
	    display:inline-block;
	}
	.elements-padding{
    	padding: 10px;
	}

	.data-input{
	    font-size: 20px;
	    border: none;
	    outline: none;
	    margin-top: 5px;
	    padding: 5px;
	    width: 100%;
	    color: gray;
	   width:510px;
	   border-radius:5px;
	}
</style>

</head>
<body>
	
	
	<%@ include file="./header/SupervisorHeader.jsp" %>

	<div class="bg">

	
	<div class="signupFrm">
    <form action="regularVisitorUpload" method="post" class="form">
      <h1 class="title">Sign up</h1>

	  <div class="inputContainer">
        <input name="firstName" id="firstName" type="text" class="input" placeholder="a" required>
        <label for="firstName" class="label">First Name</label>
      </div>
      
      <div class="inputContainer">
        <input name="lastName" id="lastName" type="text" class="input" placeholder="a" required>
        <label for="lastName" class="label">Last Name</label>
      </div>

      <div class="inputContainer">
        <input name="phone" id="phone" type="number" class="input" placeholder="a" required>
        <label for="phone" class="label">Phone No.</label>
      </div>
      
      <div class="inputContainer">
        <input name="flatNumber" id="flatNumber" type="number" class="input" placeholder="a" required>
        <label for="flatNumber" class="label">Flat Number </label>
      </div>
      
      <div class="inputContainer">
        <input name="buildingNumber" id="buildingNumber" type="number" class="input" placeholder="a" required>
        <label for="buildingNumber" class="label">Building Number </label>
      </div>

      <div class="inputContainer">
        <input name="code" id="code" type="text" class="input" placeholder="a" value="<%= (String) request.getAttribute("securityCode") %>" readonly>
        <label for="code" class="label">Security Code</label>
      </div>
      
      <input type="hidden" name="username" value="<%= (String) session.getAttribute("username") %>" >
		
      <input id="submit_btn" type="submit" class="submitBtn" value="Sign up">
    </form>
    

		<a style="position:absolute;margin-right:-30px;margin-top:464px;text-decoration:none;" href="SupervisorRegularVisitor.jsp"><input id="submit_btn" type="submit" class="submitBtn" value="Log in Page"></a>
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