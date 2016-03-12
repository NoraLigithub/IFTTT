<%@ page import="database.*"%>
<%@ page import="entity.*"%>
<%@ page import="java.util.*"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>consume info</title>
</head>
<body>
<table border="1">
<tr>
<th>Users id</th>
<th>consume date</th>
<th>consume</th>
</tr>
<%
String id=request.getParameter("id");
int l=CsmDataBase.getCsm(id).size();
for(int i=0;i<l;i++)
{%>
	<tr>
	<td><%=CsmDataBase.getCsm(id).get(i).getID()%></td>   
	<td><%=CsmDataBase.getCsm(id).get(i).getDate()%></td>
	<td><%=CsmDataBase.getCsm(id).get(i).getAmt()%></td>
<%}
%>
</table>
</body>
</html>