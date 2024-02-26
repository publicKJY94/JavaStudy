<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 실습</title>
</head>
<body>
	<%
	// jsp는 데이터를 주고 받을 때 대부분 form태그를 작성한다
	//	전송한 데이터는 request에 저장되어 있고, name으로 가져온다
	int result = 0;
	if (request.getMethod().equals("POST")) {
		int num1 = Integer.parseInt(request.getParameter("num1"));
		String op = request.getParameter("op");
		int num2 = Integer.parseInt(request.getParameter("num2"));

		if (op.equals("+")) {
			result = num1 + num2;
		} else {
			result = num1 - num2;
		}
	}
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
		<%=result%></h3>
</body>
</html>