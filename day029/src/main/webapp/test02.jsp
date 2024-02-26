<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP내장객체 : Session</title>
</head>
<body>
	<%
	if (session.isNew()) {
		out.println("<script>alert('세션을 새로 만듦!');</script>");
		session.setAttribute("userName", "귀여운 티모");
	}
	%>
	<h1>
		<%
		session.getAttribute("userName");
		%>님, 안녕하세요 :D
	</h1>
</body>
</html>