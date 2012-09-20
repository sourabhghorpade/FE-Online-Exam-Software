<%@ page language="java" import="myBeans.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please Wait</title>
</head>
<body>
<%User user=(User)session.getAttribute("user");
Test test=(Test)session.getAttribute("test");
%>
Loading next Question...Please Wait
<%
String qno=request.getParameter("qno");
String option="";
if(!qno.equals("Finish Test"))
{
	String time=request.getParameter("timer1");
	if(time!=null)
	user.settime(time);
	option=request.getParameter("ops");
	if(option!=null)
	{
		test.setAnswerSelected(test.getCurrentQuestionNumber(), Integer.parseInt(option));
	}	
	if(!qno.equals("Question List"))
	{
		if(qno.equals("Clear Selection"))
		test.setAnswerSelected(test.getCurrentQuestionNumber(), 0);
		else if(qno.equals("Next"))
		test.setCurrentQuestionNumber(test.getCurrentQuestionNumber()+1);
		else if(qno.equals("Previous"))
		test.setCurrentQuestionNumber(test.getCurrentQuestionNumber()-1);
		else if(qno.equals("Mark"))
		test.setMarked(test.getCurrentQuestionNumber(),true);
		else if(qno.equals("Unmark"))
		test.setMarked(test.getCurrentQuestionNumber(),false);
		else
		{
			test.setCurrentQuestionNumber(Integer.parseInt(qno)-1); 
		}
	}
}
%>
<script>
<%
String location;
if(qno.equals("Question List"))
location = "window.location=\"list.jsp\"";
else if(qno.equals("Finish Test"))
location = "window.location=\"final.jsp\"";
else
location = "window.location=\"question.jsp?ops="+option+"\"";
out.print(location);
%>

</script>
</body>
</html>