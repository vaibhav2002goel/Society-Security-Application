<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png">
  <title>Sign Up</title>
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
	

	<div class="bg">
	
	<div class="signupFrm">
    <form action="signUp" method="post" class="form">
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
        <input name="email" id="email" type="email" class="input" placeholder="a" required>
        <label for="email" class="label">Email</label>
      </div>
	
      <div class="inputContainer">
        <input name="username" id="username" type="text" class="input" placeholder="a" required>
        <label for="username" class="label">Username</label>
      </div>
      
      <div style="margin-bottom:40px;margin-left:-8px;" class="inputContainer">
        <div id="category" class="elements-padding">
            <h4 id="category-heading"> <label for="role">CATEGORY</label></h4>
            <select name="role" id="role" class="data-input" required>
                <option value="Administrator">Administrator</option>
                <option value="Supervisor">Supervisor</option>
                <option value="FlatResident">FlatResident</option>
            </select>       
        </div>    
      </div>

      <div class="inputContainer">
        <input name="password" id="password" type="password" class="input" placeholder="a" required>
        <label for="password" class="label">Password</label>
      </div>

      <div class="inputContainer">
        <input name="confirm_password" id="confirm_password" type="password" class="input" placeholder="a" required>
        <label for="confirm_password" class="label">Confirm Password</label>
      </div>
		
      <input id="submit_btn" type="submit" class="submitBtn" value="Sign up">
    </form>
		<a style="position:absolute;margin-right:-75px;margin-top:585px;text-decoration:none;" href="index.jsp"><input id="submit_btn" type="submit" class="submitBtn" value="Sign In Page"></a>
  </div>
  
  	</div>
  
  
  <script>
  let firstName = document.getElementById('firstName');
  let lastName = document.getElementById('lastName');
  let email = document.getElementById('email');
  let username = document.getElementById('username');
  let password = document.getElementById('password');
  let confirm_password = document.getElementById('confirm_password');
  let submit_btn = document.getElementById('submit_btn');

  submit_btn.addEventListener('click',function(e){
  	
  	
  	let flag = 1;
  	let flag1 = 1;
  	let flag2 = 1;
  	
   	for(let i=0;i<username.value.length;i++){
   		if(username.value.charCodeAt(i)==32){
   			flag=0;
   			break;
   		}
   	}
   	
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
   	
   	if(!flag){
   		alert("Cannot give spaces in username");
   		submit_btn.setAttribute('type','button')
   		return;
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
 	
//   	for(let i=0;i<username.length;i++){
//   		if(username.charCodeAt(i)==""){
//   			flag=0;
//   			break;
//   		}
//   	}
//   	if(!flag){
//   		alert("Cannot give spaces in username");
//   		submit_btn.setAttribute('type','button')
//   	}


	
  	if( (password.value != confirm_password.value)){
  		alert("Password don't Match");
  		submit_btn.setAttribute('type','button')
  	}
  	else{
  		submit_btn.setAttribute('type','submit')
  	}
  })

  
  
  </script>

</body>
</html>