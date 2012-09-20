<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>

<style type="text/css">


table 
{
    margin: 0;
	margin-top: 10px;
    padding: 0;
    text-align: left;
}

div#log {    

}

div#reg {
    border-right: 1px solid #999;

    
}

p,td {
    font-family: Georgia, serif;
    font-size: .9em;
    line-height: 1.4em;
    margin-left: 0;
}

h1,h2,h3,h4,input {
    font-family: "Trebuchet MS", sans-serif;
}

input.invalid {
	background-color: #FF9;
	border: 2px red inset;
}

#maintable
{
position:relative;
width:500px;

left:50%;
margin-left:-300px;
margin-bottom: 20px;
}
</style>
</head>
<body>
   	<script type="text/javascript" src="validations.js">	
	</script>
	<div id="bodycontent">
	
	
	
	<div id="header">

	<div id="logo"><img src="images/ascilogo.png" height="150"></div><!-- End logo div-->

	<!--<b>Exam_Title</b>-->

	</div><!-- End header div-->
	
	<!--  <marquee scrolldelay="10" direction = "right"><img src="images/tiger.gif"></marquee>   -->
	
	
	<table id="maintable">
	    
	  
	  <tr>
		  
	  
	  <td id="regCzar" >
	   <h2 align="right" style="margin-right:15px;">Registration</h2>
	   <div id="reg" align="right"> 
	    
	    <form name="myform" action="loading.jsp" method="post">
		<table cellpadding="5">
		 <tr><td align="right">
		 Name : </td><td><input type="text" name="fname" class="" id="name" onkeypress="return check_nan(event)"></td>
		 <tr><td align="right" >
		 User Name : </td><td><input type="text" name="id" class="" id="ID" ></td></tr>
		 <tr><td align="right">
		 New Password : </td><td>
		 <input type="password" name="npass" class="" id="npass" >
		 </td></tr>
		 <tr><td align="right">
		 Confirm Password : </td><td>
		 <input type="password" name="c_pass" class="" id="c_pass" >
		 </td></tr>
		 <tr><td align="right">
		 Year : </td>
		 <td>
		<select id="year" class="reqd" name="radio_year" disabled="disabled">
		  	<option value="FE" >First Year</option>
			<option value="SE" >Second Year</option>
			<option value="TE" >Third Year</option>
			<option value="BE">Final Year</option>
		</select>  
	    </td>
	    </tr>		 
		<tr><td align="right">
		 Contact Number : </td><td><input type="text" maxlength="10" name="number" class="" id="cno" onkeypress="return check(event)">
		</td>
		</tr>
		<tr><td colspan="2" align="right">
		<input type="submit" value="Register" name="button" onclick="return std_reg()">
		</td></tr>
	    
	    <tr><td colspan="2" align="center">
	    
	     <%
       try
       {
          String s=request.getParameter("msg");
          if(s!=null && !s.equals("Invalid Username/Password!!!"))
          	out.println("<font color=red>"+s+"</font>");
       }
       catch(Exception e)
       {}
       %>
	    </td></tr>
	    </table>
	    </form>
	
	   </div>
      </td>
	   
	  <td id="logCzar" >
	   <h2 style="margin-left:15px;">Log In</h2> 
	   <div id="log">
	    <form name="login" onsubmit="return valid()" action="loading.jsp" method="post">
		<table cellpadding="5">
		<tr valign="middle">
		 <td align="right"> Username : </td>
		 <td align="right"><input type="text" class="" id="user" name="username" size="15" >
		    
		</td></tr>
		<tr valign="middle"><td align="right">
		    Password : <td><input class="" type="password" id="pass" name="password" size="15">		
	     	
		</td></tr>		
		<tr valign="middle">
		 <td align="center"> Select Exam: </td>
		 <td align="right">
		 
		 <select id="subject" class="reqd" name="subject">
		  	<option value="Phy" selected="selected">Physics</option>
			<option value="EnTC" >Electronics</option>
			<option value="M1">Maths1</option>
			<option value="Civil">Civil</option>
		</select>  
		    
		</td></tr>
		
		<tr><td colspan="2" align="right">
		<input type="submit" value="Login" id="login_btn" name="button" onclick="return std_log()"></td></tr>
	    <tr><td colspan="2" align="center">
	    
	    <%
       try
       {
          String s=request.getParameter("msg");
          if(s.equals("Invalid Username/Password!!!"))
          {
              out.println("<font color=red>Invalid Username/Password!!!</font>");
          }	
       }
       catch(Exception e)
       {}
       %>
	    </td></tr>
	    </table>
	    </form>
       </div>
       </td></tr>
   </table>

<div id="footer">
	     <p>Developed By : <b>Sourabh Ghorpade</b> - <b>Dhruva Pendharkar</b> - <b>Pankaj Kumar</b><br>Designed by : <b>Apurva Pawar</b></p>
	    <!-- About Czar -->
	  
</div><!-- End footer div-->

   </div><!-- End bodycontent div-->
</body>
</html>
