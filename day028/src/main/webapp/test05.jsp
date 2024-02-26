<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class="checkid.CheckBean" id="cb" />
<jsp:setProperty name="cb" property="*" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문제풀이</title>
</head>
<body>
<%
   cb.check();
%>

<form method="POST">
   ID <input type="text" name="mid" placeholder="아이디를 입력하세요." required>
   <input type="submit" value="ID중복확인">
</form>

<hr>

<h2><%=cb.getMsg()%></h2>

</body>
</html>