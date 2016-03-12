<%@page import="database.UserDataBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!String id;String birthdate;int money; int level,points;float discounts; %>
<%
id=(String)session.getAttribute("id");
birthdate=UserDataBase.getUser(id).getBirthdate();
money=UserDataBase.getUser(id).getAmt();
//level=UserDataBase.getUser(id).getLevel();
//points=UserDataBase.getUser(id).getPoints(); 
//discounts=UserDataBase.getUser(id).getDiscount();
%> <br>
<form  action="modifyIinfo" method="POST">
birthdate:<input type="text" name="birthdate" > <br>
charge money<input type="text" name="chargemoney" >元
<input type="submit" name="OK">                  
</body>
</form>
</html>