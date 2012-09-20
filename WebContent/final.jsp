<%@ page language="java" import="java.sql.*,myBeans.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please Wait</title>
</head>
<body>
<%
User user=(User)session.getAttribute("user");
Test test=(Test)session.getAttribute("test");
%>
Calculating your Result...Please Wait

<%
int score=0;
user.setScore(score);
for(int counter=0;counter<test.getTotalNumberOfQuestions();counter++)
{
	if(test.getAnswerSelected(counter)==test.getAnswers(counter))
	{
		int level=test.getLevel(counter);
		if(level==1)
		{
			score=score+1;
		}
		else if(level==2)
		{
			score=score+2;
		}			
	}
}	
user.setScore(score); 
%>
<script type="text/javascript">
window.location="thankyou.jsp"
</script>

</body>
</html>