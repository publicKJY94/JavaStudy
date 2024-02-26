<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.MemberDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<%
	MemberDTO mDTO = (MemberDTO)session.getAttribute("member");
%>
	<form action="controller.jsp?action=memberUpdate" method="POST">
		<input type="hidden" name="mid" value="<%=mDTO.getMid() %>"> <br> 
		이름 : <input type="text" name="name" value = "<%=mDTO.getName() %>">
         <input type="submit" value="이름 변경">
	</form>

	<a href="controller.jsp?action=resign&mid=<%=mDTO.getMid()%>">회원탈퇴</a>

	<hr>

	<a href="controller.jsp?action=main">메인으로 돌아가기</a>

</body>
</html>