<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,model.dto.*"%>
<%
request.setCharacterEncoding("UTF-8");
// getParameter() 해서 setXxx() 를 진행할때에 인코딩깨짐
// 얘 전(10번 라인 전)에 수행해야함!!!!!
%>
<jsp:useBean class="model.dao.ReplyDAO" id="rDAO" />
<jsp:useBean class="model.dto.ReplyDTO" id="rDTO" />
<jsp:useBean class="model.dao.MemberDAO" id="mDAO" />
<jsp:useBean class="model.dto.MemberDTO" id="mDTO" />
<jsp:setProperty name="mDTO" property="*" />
<jsp:setProperty name="rDTO" property="*" />
<%
String action = request.getParameter("action");
if (action.equals("main")) {
	ArrayList<ReplyDTO> rdatas = rDAO.selectAll(rDTO);
	request.setAttribute("rdatas", rdatas);

	ArrayList<MemberDTO> mdatas = mDAO.selectAll(mDTO);
	request.setAttribute("mdatas", mdatas);

	pageContext.forward("main.jsp");
	// 새로운 요청 : GET
	// 기존 요청 정보를 모두 삭제시킴
	// 리다이렉트 방식
	// ↓
	// 기존 요청 정보를 유지하는 요청 방식을 사용해야한다!!!!!
	// 포워드 방식
} else if (action.equals("join")) {
	boolean flag = mDAO.insert(mDTO);
	if (flag) {
		response.sendRedirect("controller.jsp?action=main");
	} else {
		out.println("<script>alert('ID 중복 등으로 인해 회원가입에 실패하셨습니다!');history.go(-1);</script>");
	}
} else if (action.equals("joinPage")) {
	response.sendRedirect("join.jsp"); // V -> C -> V
} else if (action.equals("loginPage")) {
	response.sendRedirect("login.jsp");
	// 로그인 페이지로 이동
} else if (action.equals("login")) {
	mDTO = mDAO.selectOne(mDTO);
	if (mDTO != null) {
		session.setAttribute("member", mDTO);
		response.sendRedirect("controller.jsp?action=main");
	} else {
		out.println("<script>alert('로그인에 실패했습니다...');history.go(-1);</script>");
	}
} else if (action.equals("logout")) {
	session.removeAttribute("member");
	out.println("<script>alert('로그아웃');location.href='controller.jsp?action=main';</script>");
	// 로그아웃
} else if (action.equals("testPage")) {
	response.sendRedirect("test.jsp");
	// 마이페이지로 이동
} else if (action.equals("mypage")) {
	mDTO= (MemberDTO) session.getAttribute("member");
	mDTO = mDAO.selectOne(mDTO);
	request.setAttribute("mDTO", mDTO);
	pageContext.forward("mypage.jsp");
} else if (action.equals("memberUpdate")) {
	String mid = (String) session.getAttribute("member");
	mDTO.setMid(mid);
	boolean flag = mDAO.update(mDTO);
	if (flag) {
		response.sendRedirect("controller.jsp?action=logout");
	} else {
		out.println("<script>alert('이름변경에 실패했습니다...');location.href='controller.jsp?action=main';</script>");
	}
	// 마이페이지로 이동
} else if (action.equals("resign")) {
	boolean flag = mDAO.delete(mDTO);
	if (flag) {
		rDAO.updateByMid(mDTO);
		response.sendRedirect("controller.jsp?action=logout");
	} else {
		out.println("<script>alert('회원탈퇴에 실패했습니다...');location.href='controller.jsp?action=main';</script>");
	}
	// 회원탈퇴 이동
} else if (action.equals("replySelectOne")) {
	//	Integer rid = Integer.parseInt(request.getParameter("rid"));
	//	rDTO.setRid(rDTO.getRid());
	rDTO = rDAO.selectOne(rDTO);
	if (rDTO != null) {
		request.setAttribute("rDTO", rDTO);
		pageContext.forward("reply.jsp");
	} else {
		out.println("<script>alert('없거나 볼 수 없는 댓글입니다!');location.href='controller.jsp?action=main';</script>");
	}
	// 댓글 상세 페이지로 이동
} else if (action.equals("replyInsert")) {
	// 댓글추가
	boolean flag = rDAO.insert(rDTO);
	if (flag) {
		response.sendRedirect("controller.jsp?action=main");
	} else {
		out.println("<script>alert('댓글 추가에 실패했습니다...');location.href='controller.jsp?action=main';</script>");
	}
} else if (action.equals("replyUpdate")) {
	// 댓글수정
	boolean flag = rDAO.update(rDTO);
	if (flag) {
		response.sendRedirect("controller.jsp?action=main");
	} else {
		out.println("<script>alert('댓글 변경에 실패했습니다...');location.href='controller.jsp?action=main';</script>");
	}
} else if (action.equals("replyDelete")) {
	// 댓글수정
	boolean flag=rDAO.delete(rDTO);
	if(flag){
		response.sendRedirect("controller.jsp?action=main");
	}
	else{
		out.println("<script>alert('댓글 삭제에 실패했습니다...');location.href='controller.jsp?action=main';</script>");
	}
} else {
	// 비정상적인 접근시
	response.sendRedirect("error.jsp");
}
%>