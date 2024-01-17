function testAjax(){
var check = document.querySelector("table tbody");
$.ajax({
	type : "GET", // 요청 방식 get, post
	url : "json/data1.json", //store.do
		// 요청할 서버의 주소 (컨트롤러의 주소)
		// 데이터를 요청할 수도 있다
	dataType : "json",
	success : function(data){ // 성공했을때 실행할 함수
						// 함수의 인자로 data는 json으로 받은 dataType이다!
		var elem = "";
		$.each(data, function(index, data){
			elem += "<tr>";
            elem += "<td>"+(index+1)+"</td>";
            elem += "<td>"+data.name+"</td>";
            elem += "<td>"+data.content+"</td>";
            elem += "</tr>";
		});
		$("table tbody").append(elem);
		
		
		var text =""
		$.each(data, function(i, data){
		text += `
			<tr>
			<td>+(${i+1})+</td>
			<td>+${data.name}+</td>
			<td>+${data.content}+</td>
			</tr>
		`;
		});
		check.innerHTML;
	},
	error : function(){
         console.log("에러발생!");
	}
});
}