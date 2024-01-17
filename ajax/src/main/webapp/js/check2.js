$(document).ready(function() {
	var total = $('#photo > div').length;
	var num = 1;
	$('.pageNumber > span:first').text(num);
	$('.pageNumber > span:last').text(total);
	
	$('.prev').click(function(){
		num--;
		if(num < 1){
			num = total;
		}
		$('.pageNumber > span:first').text(num);
		$('#photo  div:last').insertBefore('#photo  div:first');
	});
	$('.next').click(function(){
		num++;
		if(num > total){
			num = 1;
		}
		$('.pageNumber > span:first').text(num);
		$('#photo  div:first').insertAfter('#photo  div:last');
	});
});

