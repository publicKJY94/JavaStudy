package student;

import student.controller.Ctrl;

public class Main {

	public static void main(String[] args) {
		// 학생 컨트롤러 생성자 만들기
		// 학생컨트롤러의 메서드 사용해서 실행하기 startApp();
		Ctrl ctrl = new Ctrl();
		ctrl.startApp();
	}
}
