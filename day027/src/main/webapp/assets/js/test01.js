document.write('외부스크립트 적용');
// 태그를 작성하지 않는다
var result = prompt('나이를 입력하세요');
var msg = '노인 : 0원';
while (true) {
	if (result >= 1 && result <= 120) {
		if (result <= 8) {
			msg = '미취학 아동 : 0원';
			break;
		} else if (result <= 19) {
			msg = '학생 : 1200원';
			break;
		} else if (result <= 65) {
			msg = '성인 : 2000원';
			break;
		}
		break;
	} else {
		result = prompt('나이가 이상합니다 1~120사이의 값으로 입력해주세요');
	}
}
document.write(msg);
