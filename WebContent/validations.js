function std_log()
	{
		var flag=true;
		if(document.getElementById("pass").value=="")	
		{	
			document.getElementById("pass").setAttribute("class", "invalid");
		    flag=false;
		}
		else
			document.getElementById("pass").setAttribute("class", "");
		if(document.getElementById("user").value=="")	
		{
			document.getElementById("user").setAttribute("class", "invalid");
		    flag=false;
		}
		else
			document.getElementById("user").setAttribute("class", "");
		
		return flag;	
	}	
	function std_reg()
	{
		var flag=true;
		
		if(document.getElementById("ID").value=="")	
		{
			document.getElementById("ID").setAttribute("class", "invalid");
		    flag=false;
		}
		
		else
		{
			document.getElementById("ID").setAttribute("class", "");
		}
		if(document.getElementById("npass").value=="")	
		{
			document.getElementById("npass").setAttribute("class", "invalid");
		    flag=false;
		}
		else
		{
			document.getElementById("npass").setAttribute("class", "");
		}
		if(document.getElementById("c_pass").value=="") 	
		{
			document.getElementById("c_pass").setAttribute("class", "invalid");
		    flag=false;
		}
		else
		{
			document.getElementById("npass").setAttribute("class", "");
		}
		var p1=document.getElementById("npass").value;
		var p2=document.getElementById("c_pass").value;
		if(p1==p2 && p1!="")
			{
			document.getElementById("c_pass").setAttribute("class","");
			document.getElementById("npass").setAttribute("class","");
			}
		else
			{
			document.getElementById("c_pass").setAttribute("class","invalid");
			document.getElementById("npass").setAttribute("class","invalid");
			flag=false;
			}
		if(document.getElementById("name").value=="")	
		{
			document.getElementById("name").setAttribute("class", "invalid");
		    flag=false;
		}
		else
		{
			document.getElementById("name").setAttribute("class", "");
		}
		if(document.getElementById("cno").value=="" || document.getElementById("cno").value.length!=10)	
		{
			document.getElementById("cno").setAttribute("class", "invalid");
		    flag=false;
		}
		else
		{
			document.getElementById("cno").setAttribute("class", "");
		}
		
		if(flag==true)
		{
			document.myform.year.disabled=false;
		}
		return flag;
	}
		
function check(event)
{
	if(event.keyCode<48 || event.keyCode>57)
	{
		event.keyCode=0;
    	return false;
	}
	return true;
}

function check_nan(event)
{
	if(!(event.keyCode<48 || event.keyCode>57))
	{
		
		event.keyCode=0;
    	return false;
	}
	return true;
}