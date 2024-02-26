<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String mid = request.getParameter("mid");
		session.setAttribute("mid", mid);
	%>
	<h1>상품등록</h1>
	<hr><%= session.getAttribute("mid") %>님, 안녕하세요 :D<hr>
	
	<form action="cart.jsp" method="POST">
	<select name="product" required="required">
		<option>탕후루</option>
		<option>붕어빵</option>
		<option>귤</option>
		<option>딸기</option>
	</select>
	<input type="number" name="cnt" required="required">
	<input type="submit" value="상품등록">
	</form>
	<a href="pay.jsp">등록하기</a>
</body>
</html>