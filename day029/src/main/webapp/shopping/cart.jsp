<%@page import="java.util.ArrayList"%>
<%@page import="day029.DTO.ProductDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니에 상품을 추가</title>
</head>
<body>
	<%
	ArrayList<ProductDTO> cart = (ArrayList<ProductDTO>) session.getAttribute("cart");
	if (cart == null) {
		cart = new ArrayList<ProductDTO>();
		session.setAttribute("cart", cart);
	}
	ProductDTO data = new ProductDTO();
	String product = request.getParameter("product");
	int cnt = Integer.parseInt(request.getParameter("cnt"));
	data.setProduct(product);
	data.setCnt(cnt);
	cart.add(data);
	// session.setAttribute("cart", cart);
	%>
	<script>
	alert('<%=product%>가 추가되었습니다');
	history.go(-1); 
	</script>
</body>
</html>