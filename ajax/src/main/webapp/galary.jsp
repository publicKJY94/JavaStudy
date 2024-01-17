<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리</title>
<style type="text/css">
div#photo {
	width : 600px;
	height : 600px;
	bordr : 2px solid black;
	overflow: hidden;
}
</style>
</head>
<body>
	<div id="wrapper">
		<div class="pageInfo">
			<span class="pageNumber"><span></span> / <span></span></span>
			<button class="prev">&lt;</button>
			<button class="next">&gt;</button>
		</div>
	</div>
	<div id="photo">
		<div><img alt="이미지1" src="images/1.png"></div>
		<div><img alt="이미지2" src="images/2.png"></div>
		<div><img alt="이미지3" src="images/3.png"></div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script src="js/check2.js"></script>
</html>