<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP내장객체 : application</title>
</head>
<body>
<%application.setAttribute("count", 0); %>
	<%-- application으로 가져오는 값은 Object타입이므로
		 형변환을 진행해야 한다 --%>
	<a href="test04.jsp">방문자 수 확인하기</a>
</body>
</html>