<%@page import="java.sql.*"%>
<%@page import="myBeans.DatabaseConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration Configuration</title>
</head>
<body>
<%

int arguments[]=new int[12];
int NUMBER_OF_UNITS=6;
String LEVEL1="1",LEVEL2="2";

String testName=request.getParameter("testName");
DatabaseConnection databaseConnection=new DatabaseConnection("FE_DB");
if(testName!=null)
	{
		try
			{
			databaseConnection.executeUpdate("insert into test_configuration values('"+ testName + "');");
			}
		catch(SQLException duplicateTestName)
			{
				
			}
	}

for(int unitNumber=1;unitNumber<=NUMBER_OF_UNITS;unitNumber++)
	{
		try
			{
				arguments[unitNumber]=Integer.parseInt(request.getParameter("Unit"+ unitNumber + LEVEL1));
				arguments[unitNumber+1]=Integer.parseInt(request.getParameter("Unit"+ unitNumber + LEVEL2));
			}
		catch(Exception e)
			{
				continue;
			}
	try
		{
			
	databaseConnection.executeUpdate("insert into config_details values('"+testName+"',"+unitNumber+","+LEVEL1+
			","+ arguments[unitNumber]+ ");");     
	databaseConnection.executeUpdate("insert into config_details values('"+testName+"',"+unitNumber+","+LEVEL2+
			","+ arguments[unitNumber+1]+ ");");     
	
		}
	catch(SQLException duplicateRecord)
		{
			
		}
	}
databaseConnection.disconnect();

%>
<form action="admin.jsp" method="get">
Enter Name of Test <input type="text" name="testName"><br>
Enter Number of Level 1 Questions in Unit 1<input type="text" name="Unit11"></input><br>
Enter Number of Level 2 Questions in Unit 1<input type="text" name="Unit12"></input><br>
Enter Number of Level 1 Questions in Unit 2<input type="text" name="Unit21"></input><br>
Enter Number of Level 2 Questions in Unit 2<input type="text" name="Unit22"></input><br>
Enter Number of Level 1 Questions in Unit 3<input type="text" name="Unit31"></input><br>
Enter Number of Level 2 Questions in Unit 3<input type="text" name="Unit32"></input><br>
Enter Number of Level 1 Questions in Unit 4<input type="text" name="Unit41"></input><br>
Enter Number of Level 2 Questions in Unit 4<input type="text" name="Unit42"></input><br>
Enter Number of Level 1 Questions in Unit 5<input type="text" name="Unit51"></input><br>
Enter Number of Level 2 Questions in Unit 5<input type="text" name="Unit52"></input><br>
Enter Number of Level 1 Questions in Unit 6<input type="text" name="Unit61"></input><br>
Enter Number of Level 2 Questions in Unit 6<input type="text" name="Unit62"></input><br>

<input type="submit" id="sub"></input>
</form>

</body>
</html>