<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- jsp 주석 --%>
	<% 
// 웹 페이지 코드 내에서
// java를 사용하려고 탄생한 서버 프로그래밍 언어
// 1번라인 '@'는 지시어로 @ page는 page에 대한 지시어이다
out.println("<h1>스크립트릿</h1>");
%>
<h1><%=new Date() %></h1>
</body>
</html>