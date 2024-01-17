<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="border" %>
<%@ attribute name="bgColor" %>

<h1><jsp:doBody /></h1> <%-- jsp파일에서 해당 태그를 사용하면, 해당위치의 값을 가져온다 --%>
<h2>커스텀 태그의 외부에서 설정한 태그바디값을 가져오는 태그</h2>
<table border="${border}" bgColor="${bgColor}">
	<tr>
		<td>번호</td><td>이름</td>
	</tr>
	<tr>
		<td>101</td><td>작은 티모</td>
	</tr>
</table>