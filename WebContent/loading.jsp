
<%@page import="myBeans.Test"%>
<%@page import="myBeans.User"%>
<%@page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loading</title>
</head>
<body>
	Loading Please Wait......
	<%
	
	String S = request.getParameter("button");
	if (S.equals("Register"))
		{
		try 
		{
			String password = request.getParameter("npass");
			String username = request.getParameter("id");
			String name = request.getParameter("fname");
			String contactNumber=request.getParameter("number");
			String year =request.getParameter("radio_year");
			User.register(username, password, name, year, contactNumber);
			%>
			<script>
			window.location.replace("home.jsp?msg=You have successfully registered.");
			</script>
			<% 
		}
		catch (SQLException e) 
			{
			%>
			<script>
			window.location.replace("home.jsp?msg="+"<%out.print(e.getLocalizedMessage());%>");
			</script>
			<%
			}
		catch (Exception e) 
			{
			%>
			<script>
			window.location.replace("home.jsp?msg=System is unable to Register you currently.Please call a volunteer.");
			</script>
			<%
			}
		} 
	else 
		{
		String username = request.getParameter("username");
		String passsword = request.getParameter("password");
		try
		{
			User user=new User(username,passsword);
			session.setAttribute("user",user);
			String subject=request.getParameter("subject");
			int arguments[]={5,1,3,2,3,1,6,2,5,1,3,2};
			Test test=new Test(arguments,subject);
			test.setFlag(1);
			session.setAttribute("test", test);
			 
			%>
			<script>
			window.location="rules.jsp";
			</script>
			<%
		}
		catch(SQLException exception)
			{
				%>
				<script>				
				   window.location.replace("home.jsp?msg=Invalid Username/Password!!!");
				</script>
				<%
			}
		catch(ClassNotFoundException excption1)
			{
				%>				
				<script>				
				   window.location.replace("home.jsp?msg=Invalid Username/Password!!!");
				</script>
				<%
			}
		}
		%>
			
</body>
</html>