<%@ page import="java.sql.*,myBeans.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thank You</title>

<style type="text/css">
#ty_note
{
width:500px;height:300px;position:relative;left:50%;margin-left:-250px;text-align:center;
font-size:30px;
margin-top:20px;
margin-bottom:20px;
}

#ty_note input
{
background-color:#0000A0;
border-radius:5px;
color:white;
width: 100px;
height:40px;
margin-top:20px;

	-webkit-box-shadow: 0px 0px 10px black;
	-ms-box-shadow: 0px 0px 10px black;
	-o-box-shadow: 0px 0px 10px black;
	-moz-box-shadow: 0px 0px 10px black;
	box-shadow: 0px 0px 10px black;

	-webkit-transition: all 0.6s ease;
	-ms-transition: all 0.6s ease;
	-moz-transition: all 0.6s ease;
	-o-transition: all 0.6s ease;
}

#ty_note input:hover
{
cursor:pointer;
background-color:#0000FC;
}

</style>


</head>

<body>
<%
	User user=(User)session.getAttribute("user");
	user.setMin(20);
	user.setSec(0);
	user.update_info();
%>
<div id="bodycontent">

	<div id="header">

	<div id="logo"><img src="images/ascilogo.png" height="150"></div><!-- End logo div-->

	<b><%=user.getYr() + " Test"%></b>

	</div><!-- End header div-->

<div id="ty_note">

Thank You <%=user.getName() %> 
<br>
Your Final Score is :  <%out.print(user.getScore());%> 
<form action="home.jsp">
<input type="submit" value="Done">
</form>
</div>
<%user.clear();%>



<div id="footer">
	     <p>Developed By : <b>Sourabh Ghorpade</b> - <b>Dhruva Pendharkar</b> - <b>Pankaj Kumar</b><br>Designed by : <b>Apurva Pawar</b></p>
	    <!-- About Czar -->
	  
</div><!-- End footer div-->
</div>
</body>
</html>