<%@page import="database.*"%>
<%@ page import="entity.*"%>
<%@ page import="java.util.*"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administor</title>
</head>
<body>
<br> 
<a href="sendmessage.jsp?id=null&pub=<%="public"%>">send to public</a>
<table border="1">
<tr>
<th>Users id</th>
<th>deposit</th>
<th>consume record</th>
<th>message</th>
<%!int i,l; ArrayList<UserInfo> u;boolean pub=false;%>
<%
u=UserDataBase.getAllUsers();
l=u.size();
for(i=0;i<l;i++)
{%>		
<tr>
<td><%=u.get(i).getID()%></td>
<td><%=u.get(i).getAmt() %></td>
<td><a href="consumeinfo.jsp?id=<%=u.get(i).getID()%>">consume list</a></td>
<td><a href="sendmessage.jsp?id=<%=u.get(i).getID()%> & pub=<%="nopublic"%>">send to</a>
</tr>

<%}%>
</table>
<br>
<table border="1">
<tr>
<th>send to</th>
<th>message</th>
<th>delete message</th>
</tr> 
<%!int j,len; ArrayList<Message> m;%>

<%
m=MsgDataBase.getAllMsgs();
len=m.size();
for(j=0;j<len;j++)
{%>
	<tr>
	<td><%=m.get(j).getID() %></td>
	<td><%=m.get(j).getCtx() %></td>
	<td><a href="deletemessage?id=<%=m.get(j).getIdx()%>">delete message</a>
<%
}
%>
<%
%>


</body>
</html>