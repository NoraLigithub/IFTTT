<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<body bgcolor="#d0d0d0">
<head> New A Task </head> <br />
<strong> IF </strong> <br />
<%-- 
<select name="thisKind" id="this" onchange="showthis()">
<option value="time" > Time is  </option>  
<option  value="mail"> Receive new QQ mail </option>  
<option value="weibo"> Receive new Weibo </option>  
</select>
<select name="thatKind" id="that" onchange="showthat()">
<option value="mail" > Send new QQ mail</option>    
<option value="weibo"> Send new Weibo </option>  
</select>
--%>
<form action="newTaskInfo.jsp" method="post">

<input type="radio" name="thisKind" value="time" /> Time is
<br />
<input type="radio" name="thisKind" value="mail" /> Receive new QQ mail
<br />
<input type="radio" name="thisKind" value="weibo" /> Receive new Weibo
<br />
<strong> THEN </strong> <br />
<input type="radio" name="thatKind" value="mail" /> Send new QQ mail
<br />
<input type="radio" name="thatKind" value="weibo" /> Send new Weibo
<br /> <br />
<input type="submit" value="New">
</form>




</body>
</html>