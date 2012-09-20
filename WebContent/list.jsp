<%@ page language="java" import="java.util.Random,java.sql.*,myBeans.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>

<style type="text/css">

.s_info_bar
{
width:300px;
height:30px;
border:solid;
border-width:thin;
border-color:#999;
border-radius:5px;
position:relative;
float:right;
margin-left:15px;
border-top:none;
text-align:center;
color:black;
}

#session_info
{
width:960px;
height:40px;
margin-top:-20px;
position:relative;
left:50%;
margin-left:-490px;
}

#q_box
{
position:relative;

left:50%;margin-left:-480px;width:960px;border-style:solid;margin-top:20px;border-width:thin;border-color:#999;
border-left:none;
border-right:none;
}

input{
  background-color:white;
  color: black;
}
#a_box
{
	width:960px;
	position:relative;
	margin-top:20px;	
	padding: 15px;	
	margin-bottom:70px;
}

#submit_ans
{
width:200px;
height:50px;
float:right;
}

#submit_ans input
{
width:120px;
height:30px;
left:50%;
position:relative;
margin-left:-60px;
top:50%;
margin-top:-15px;
background-color: #29761F;
border-radius:5px;
color:white;

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

#submit_ans input:hover
{
cursor:pointer;
background-color: #3FB42E;
}

#header input
{
background-color:maroon;
border-radius:5px;
color:white;

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

#header input:hover
{
cursor:pointer;
background-color:red;
}
</style>


</head>

<body onload="start()">
<%User user=(User)session.getAttribute("user");
Test test=(Test)session.getAttribute("test");
%>
<div id="bodycontent">

	<div id="header">

	<div id="logo"><img src="images/ascilogo.png" height="150"></div><!-- End logo div-->

	<b><%=user.getYr() + " Test"%></b>
	</div><!-- End header div-->
<script>
function start()
	{
		document.getElementById("timer1").setAttribute("value","<%=user.gettime()%>");
		document.getElementById("timer1").setAttribute("disabled","disabled");
		timer();
		<%
		out.print("document"+"."+"getElementById"+ "(\"prev\")" +"."+"setAttribute"+"(\"disabled\""+","+"\"disabled\");");
		out.print("document"+"."+"getElementById"+ "(\"next\")" +"."+"setAttribute"+"(\"disabled\""+","+"\"disabled\");");
		out.print("document"+"."+"getElementById"+ "(\"clear\")" +"."+"setAttribute"+"(\"disabled\""+","+"\"disabled\");");
		out.print("document"+"."+"getElementById"+ "(\"mark\")" +"."+"setAttribute"+"(\"disabled\""+","+"\"disabled\");");
		%>
	}
function abcd(str)
{
	document.getElementById("ans").innerHTML="Answer is : " + str;	
}
function ty()
{
	window.location="final.jsp";	
}

function butn()
{
	 document.getElementById("haha").innerHTML="submit";	 
}
function end()
{
	document.form1.timer1.disabled=false; 
}
function timer()
	{
	var s=document.getElementById("timer1").value;
	var arr=s.split(":",s.length);
	var sec=parseInt(arr[1]);
	var min=parseInt(arr[0]);
	if(sec!=0)
	{
		sec--;
	}
	else if(min!=0)
		{
		sec=59;
		min--;
		}
	else
		{
		window.location="final.jsp";
		}
	s=min+":"+sec;
	
    document.getElementById("timer1").setAttribute("value", s);
	setTimeout("timer()",1000);
	}

</script>

<br>
<form method="post" name="form1" id="q" action="inter.jsp">  

<div id="session_info">
<div class="s_info_bar"><b>Time</b><br><input type="text" style="text-align:center;" name="timer1" id="timer1" value="red" ></div>
<!--  <div class="s_info_bar"><b>Score</b><br><input type="text" style="text-align:center;" value="NAN" disabled="disabled"></div>-->
<div class="s_info_bar"><b>Name</b><br><input type="text" style="text-align:center;" value="<%out.write(user.getName());%>" disabled="disabled"></div>
</div>

<div>
<input type="submit" name="qno" id="qno1" value="1" onclick="return end()"> 
<input type="submit" name="qno" id="qno2" value="2" onclick="return end()"> 
<input type="submit" name="qno" id="qno3" value="3" onclick="return end()"> 
<input type="submit" name="qno" id="qno4" value="4" onclick="return end()"> 
<input type="submit" name="qno" id="qno5" value="5" onclick="return end()"> 
<input type="submit" name="qno" id="qno6" value="6" onclick="return end()"> 
<input type="submit" name="qno" id="qno7" value="7" onclick="return end()"> 
<input type="submit" name="qno" id="qno8" value="8" onclick="return end()">  
<input type="submit" name="qno" id="qno9" value="9" onclick="return end()">
<input type="submit" name="qno" id="qno10" value="10" onclick="return end()"> 
<input type="submit" name="qno" id="qno11" value="11" onclick="return end()"> 
<input type="submit" name="qno" id="qno12" value="12" onclick="return end()">   
<input type="submit" name="qno" id="qno13" value="13" onclick="return end()"> 
<input type="submit" name="qno" id="qno14" value="14" onclick="return end()"> 
<input type="submit" name="qno" id="qno15" value="15" onclick="return end()"> 
<input type="submit" name="qno" id="qno16" value="16" onclick="return end()"> 
<input type="submit" name="qno" id="qno17" value="17" onclick="return end()"> 
<input type="submit" name="qno" id="qno18" value="18" onclick="return end()"> 
<input type="submit" name="qno" id="qno19" value="19" onclick="return end()">
<input type="submit" name="qno" id="qno20" value="20" onclick="return end()"> 
<input type="submit" name="qno" id="qno21" value="21" onclick="return end()"> 
<input type="submit" name="qno" id="qno22" value="22" onclick="return end()"> 
<input type="submit" name="qno" id="qno23" value="23" onclick="return end()"> 
<input type="submit" name="qno" id="qno24" value="24" onclick="return end()"> 
<input type="submit" name="qno" id="qno25" value="25" onclick="return end()"> 
</div>

<div id='options'> 
<input type="submit" name="qno" id="mark" value="Mark" onclick="return end()">
<input type="submit" name="qno" id="next" value="Next" onclick="return end()"> 
<input type="submit" name="qno" id="prev" value="Previous" onclick="return end()"> 
<input type="submit" name="qno" id="clear" value="Clear Selection" onclick="return end()"> 
<input type="submit" name="qno" id="finish test" value="Finish Test" onclick="return ty()">
</div>
<br>
</form>

<div id="footer">
	     <p>Developed By : <b>Sourabh Ghorpade</b> - <b>Dhruva Pendharkar</b> - <b>Pankaj Kumar</b><br>Designed by : <b>Apurva Pawar</b></p>
	    <!-- About Czar -->
	  
</div><!-- End footer div-->

</div><!-- end body content -->
</body>
</html>