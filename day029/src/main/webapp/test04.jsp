<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과화면</title>
</head>
<body>
	<%
	Integer count = (Integer) application.getAttribute("count");
	count++;
	application.setAttribute("count", count);
	%>
	<h1>
		방문자수 :
		<%=application.getAttribute("count")%></h1>
</body>
</html>