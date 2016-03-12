<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#d0d0d0">
<h>Task Info </h> <br />

<%!String ID;int thisKind; String time; String rcvMail;
String ltnWeibo; String ltnCxt; String thisPwd; int thatKind; String sndWeibo; 
String sndMail; String sndCxt; String thatPwd;String thisType; String thatType; %>
<%thisType=request.getParameter("thisKind");
thatType=request.getParameter("thatKind");
session.setAttribute ("thisType",thisType);
session.setAttribute("thatType", thatType);
%><br />
<strong> IF </strong> <br />
<form name="input" action="addTask" method="post">
<%
switch(thisType){
case "time":
	out.println("Time is (yyyy-MM-dd HH:mm:ss)<input type=\"text\" name=\"time\" id=\"time\"><br />");
	break;
case "mail":
	out.println("receive  an email <br /> address<input type=\"text\" name=\"rcvMail\" id=\"rcvMail\"><br />\n");
	out.println("password <input type=\"password\" name=\"thisPwd\" id=\"thisPwd\"><br />\n");
	break;
case "weibo":
	out.println("receive a specific weibo <br /> weibo name <input type=\"text\" name=\"ltnWeibo\" id=\"ltnWeibo\"><br />");
	out.println("password <input type=\"password\" name=\"thisPwd\" id=\"thisPwd\"><br />\n");
	out.println("weibo text <input type=\"text\" name=\"ltnCxt\" id=\"ltnCxt\" ><br />\n");
	break;	
}
%>
<strong> THEN </strong> <br />
<%
switch(thatType){
case "mail":
	out.println("send an email <br />receiver address <input type=\"text\" name=\"sndMail\" id=\"rcvMail\"><br />\n");
	out.println("text <input type=\"text\" name=\"sndCxt\" id=\"sndCxt\" ><br />\n");
	break;
case "weibo":
	out.println("send a weibo <br />weibo name <input type=\"text\" name=\"sndWeibo\" id=\"sndWeibo\" ><br />");
	out.println("password <input type=\"password\" name=\"thatPwd\" id=\"thatPwd\"><br />\n");
	out.println("weibo text <input type=\"text\" name=\"sndCxt\" id=\"sndCxt\"><br />\n");
	break;		
}
%>
<input type="submit" value="Submit" > %>


<%@ page import="database.*"%>
<%@ page import="entity.*"%>
<%@ page import="java.util.*"%>
<script type="text/javascript">
 function newTask()
{
	time=document.getElementById("time").value;
	sndMail=document.getElementById("sndMail").value;
	sndCxt=document.getElementById("sndCxt").value;
	Task t=TaskDataBase.createTask(ID, thisKind,  time, rcvMail, 
		ltnWeibo, ltnCxt, thisPwd, thatKind, sndWeibo, 
			 sndMail, sndCxt, "");
	request.sendRedirect("main.jsp");
}
</script>

</form>
</body>
</html>