<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#d0d0d0">
<%@ page import="database.*"%>
<%@ page import="entity.*"%>
<%@ page import="java.util.*"%>  
welcome <br>
<%String id;String previlege;ArrayList<Task> t;int l; int i;String moneyS;float money;%>
<%id= (String)request.getSession().getAttribute("id");%>
<%request.setCharacterEncoding("UTF-8");
previlege=(String)request.getSession().getAttribute("Previlege");

%>
<%
System.out.println(previlege+" "+(previlege=="root"));
if(previlege.equals("root")) response.sendRedirect("administor.jsp");
%>
<%money=(int)UserDataBase.getUser(id).getAmt(); %>
id=<%=id %> <br>
previlege=<%=previlege %> <br>
money=<%=money %>><br >
<a href="displayinfo.jsp">User Info</a><br>
<a href="messagebox.jsp">Message Box</a><br>
<%
	t=TaskDataBase.getUserTasks(id);
	l=t.size();
%>	
<big><b> All Tasks </b></big> <br />
	<% for(i=0;i<l;i++)
	{
		%>
		<a href="starttask?tasknumber=<%=t.get(i).getNumber()%>">start</a>
		<a href="stoptask?tasknumber=<%=t.get(i).getNumber()%>">stop</a>
		<%=i+1 %>
		<br>
		<%= t.get(i).getNumber() %>
		<%=t.get(i).taskInfo()%>
		
		<a href="deletetask?tasknumber=<%=t.get(i).getNumber()%>">delete</a>
		<a href="modifytask?tasknumber=<%=t.get(i).getNumber()%>">modify</a>
		<br> 
<% 
 }%>
 <br />

 <a href=newTask.jsp>New a task now!</a> 

</body>
</html>