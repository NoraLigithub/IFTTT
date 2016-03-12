<%@ page import="database.*"%>
<%@ page import="entity.*"%>
<%@ page import="java.util.*"%> 
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
String id=request.getParameter("id");
String pub=request.getParameter("pub");
System.out.println("in sendmessage.jsp  id is"+id+"   pub is"+pub);
%>
<form action="send">
<textarea name="cxt" rows="5" cols="30">message</textarea><br>
<input type="submit" name="OK"> 
<input type="hidden" name="id" value=<%=id %>>
<input type="hidden" name="pub" value=<%=pub %>>
</form>

</body>
</html>