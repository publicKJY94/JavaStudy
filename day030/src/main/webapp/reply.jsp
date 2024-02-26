<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 상세 페이지</title>
</head>
<body>
	<%
	MemberDTO member = (MemberDTO) session.getAttribute("member");
	ReplyDTO rDTO = (ReplyDTO) request.getAttribute("rDTO");
	%>
	<form action="controller.jsp?action=replyUpdate" method="POST">

		<input type="hidden" name="rid" value="<%=rDTO.getRid()%>"readonly="readonly"> 
		작성자 :
		<%
			if(rDTO.getName()==null){
				out.print("탈되한 계정");
			}else{
				out.print(rDTO.getName());
			}
		%>
		<input type="hidden" name="writer" value="<%=rDTO.getWriter()%>"readonly="readonly"> <br> 
		내용 : <input type="text"
			name="content" value="<%=rDTO.getContent()%>"><br>
		<%
		if (member.getMid().equals(rDTO.getWriter())) {
		%>
		<input type="submit" value="내용 변경">
		<%
		}
		%>
	</form>
	<%
	if (member.getMid().equals(rDTO.getWriter())) {
	%>
	<a href="controller.jsp?action=replyDelete&rid=<%=rDTO.getRid()%>">댓글
		삭제</a>
	<%
	}
	%>
	<hr>

	<a href="controller.jsp?action=main">메인으로 돌아가기</a>

</body>
</html>