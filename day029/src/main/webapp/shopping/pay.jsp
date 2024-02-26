<%@page import="java.util.ArrayList"%>
<%@page import="day029.DTO.ProductDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="ctrl" class="day029.ctrl.Contrl"></jsp:useBean>
<jsp:setProperty property="*" name="ctrl"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 페이지</title>
</head>
<body>
	<%
	ArrayList<ProductDTO> cart = (ArrayList<ProductDTO>) session.getAttribute("cart");
	if(cart==null){
		out.println("상품이 없습니다");
	}else{
		ctrl.setCart(cart);
		for(ProductDTO data: cart){
			out.print(data.getProduct()+" : "+data.getCnt()+"개<br>");
		}
		ctrl.start();
		cart.clear();
	}
%>
</body>
</html>