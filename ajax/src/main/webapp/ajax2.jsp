<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br><br>
	<img alt="더미이미지1" src="">	<br><br>
	<img alt="더미이미지2" src="">	<br><br>
	<button id="btn">버튼</button>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous">
</script>
<script type="text/javascript">
	$(document).ready( function(){
		$("#btn").on("click", function(){
			$.ajax({
				type: "POST",
				url : "apple.do", // 자바와 관련되어 있으면 쌍따옴표 js는 작은따옴표
				data: {'banana':'바나나', 'kiwi':123}, // 서버로 전달할 데이터
				dataType : "text",
				success : function(data){
					console.log(data); // 서버에서 보내준 사과가 출력됨
					if(data=='사과'){
						$("body").append("<img alt='더미 이미지 데이터' src='images/test03.jpg'>");
					}
				},
				error : function(){
					console.log('에러의 종류' + error);
				}
			});
		});	
	});
</script>
</html>