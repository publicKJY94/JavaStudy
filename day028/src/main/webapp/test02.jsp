<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean class="calc.CalcBean" id="cb" />
<%-- 
	new를 대신하는 jsp문법
	calc.CalcBean cb = new calc.CalcBean();
	그래서 웹에서는 기본생성자를 사용한다
 --%>
<jsp:setProperty property="*" name="cb" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>두번째 실습</title>
</head>
<body>
	<%
	cb.calc();
	%>
	<form method="POST">
		<input type="text" name="num1"> <select name="op">
			<option>+</option>
			<option>-</option>
		</select> <input type="text" name="num2"> <input type="submit"
			value="계산">
	</form>
	<hr>
	<h3>
		계산결과 :
		<jsp:getProperty property="result" name="cb" />
	</h3>
</body>
</html>