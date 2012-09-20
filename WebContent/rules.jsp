<%@page import="myBeans.Test"%>
<%@ page language="java" import="myBeans.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rules</title>
</head>
<body onload="start()">
<%User user=(User)session.getAttribute("user");%>

<div id="bodycontent">

	<div id="header">

	<div id="logo"><img src="images/ascilogo.png" height="150"></div><!-- End logo div-->

	<b><%=user.getYr() + " Test"%></b>

	</div><!-- End header div-->
	

<center><font size=10>
 Welcome <%=user.getName()%><br>
</font></center>
 <b style="margin-left:40px;">RULES</b>
<ul style="text-align:left;font-size:20px;padding-left:40px;">
	<li>The Rules are as Follows:
	<li>Maximum time Limit is <%=user.gettime()%> (min:sec)
	<li>There is no limit on maximum limit of Questions
	<li>Every question has 4 options out of which only one is correct
	<ul>
		<li>For Correct answer +2 points and -1 for incorrect answer
	</ul>
	<li>Click Submit button to submit your answer
	<li>Click on the options to select your option. 
	<li>You can Finish the test at any given time by clicking the Finish button in the top right corner 
	<li>Please call any volunteer in case of any problem.
	<li>The Decision of the Jury shall be final and binding on all the participants
</ul>
<form action="question.jsp" method=get>
<div style="width:100px;height:50px;position:relative;left:50%;margin-left:-75px;">
<input type="submit" id="testbutton" value="Take Test" name="next" style="width:150px;height:40px;">
</div>
</form>

<div id="footer">
	     <p>Developed By : <b>Sourabh Ghorpade</b> - <b>Dhruva Pendharkar</b> - <b>Pankaj Kumar</b><br>Designed by : <b>Apurva Pawar</b></p>
	    <!-- About Czar -->
	  
</div><!-- End footer div-->

</div><!-- end body content -->
</body>
</html>