<%@page import="entity.Message"%>
<%@page import="database.MsgDataBase"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String ID=(String)session.getAttribute("id");
ArrayList<Message> m1= MsgDataBase.getMsg(true, true, ID);
ArrayList<Message> m2= MsgDataBase.getMsg(false, false, ID);
for(int i=0;i<m1.size();i++)
{%>
	public <%=m1.get(i).getCtx()%> <br>
<%}
%>
<%
for(int j=0;j<m2.size();j++)
{%>
	private <%=m2.get(j).getCtx()%> <br>
<%}
%>
</body>
</html>