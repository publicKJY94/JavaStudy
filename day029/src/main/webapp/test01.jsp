<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error/error.jsp"%>
    <jsp:useBean id="main" class="day029.ctrl.Contrl"></jsp:useBean>
    <jsp:setProperty property="" name=""/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% %> <%--스크립틀릿 --%>
<%@ page %> <%--지시어 --%>
<%= %> <%--표현식 --%>
<%--
	JSP내장 객체
	out, request, response, session, application ...
	.jsp가 컴파일 되면 Servlet파일이 될때 제공되는 객체들을 JSP내장객체라고 한다
	
	내장객체는 크게 2가지로 분류할 수 있는데
	1. 사용자에게 화면을 제공하는 용도로 사용
		: out, response
	2. 사용자가 입력한 값을 저장하는 용도로 사용
		: request, session, application
		>> scope의 유효범위가 다름
		request >> 페이지 단위, 요청 단위
		session >> 브라우저 단위, 시간 단위[은행] - 최초는 null
		application >> 서버 단위
 --%>
	<jsp:getProperty property="" name=""/>;
</body>
</html>