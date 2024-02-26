package day013;

import day013.controller.CalcCtrl;

public class Test {

	public static void main(String[] args) {
		// 계산기 프로그램

		// 기능 : 더하기, 빼기, 곱하기
		CalcCtrl app = new CalcCtrl();
		while (true) {
			app.startApp();
		}
	}

}
