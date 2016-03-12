<%@page import="database.*"%>
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
String id=(String)session.getAttribute("id");
UserDataBase.getUser(id).printUserInfo();
%>
id:<%=id%> <br> 
birth date:<%=UserDataBase.getUser(id).getBirthdate() %><br>
money:<%=UserDataBase.getUser(id).getAmt() %><br>
level:<%=UserDataBase.getUser(id).getLevel() %><br> 
points:<%=UserDataBase.getUser(id).getPoints()%> <br>
discounts:<%=UserDataBase.getUser(id).getDiscount() %> <br>
Consume record<br>
<%ArrayList<Consume> c=CsmDataBase.getCsm(id);
int l=c.size();
System.out.println("consume length "+l);
for(int i=0;i<l;i++)
{
	out.println(c.get(i).getID()+"   "+c.get(i).getDate()+"  "+c.get(i).getAmt()+ "<br>\n");
}
%>
<a href=modifyinfo.jsp>Modify Info</a><br>
<a href=main.jsp>return</a>
</body>
</html>