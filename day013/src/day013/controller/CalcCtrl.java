package day013.controller;

import day013.model.CalcModel;
import day013.view.CalcView;

public class CalcCtrl {
	private CalcModel model; // 기능을 모아두는 부분
	private CalcView view; // 사용자에게 보여지는 부분 + 간단한 유효성 검사

	public CalcCtrl() {
		this.model = new CalcModel(); // 매개변수(model) 초기화
		this.view = new CalcView(); // 매개변수(view) 초기화
	}
	public double startApp() { // 입력 >> 저장 >> 계산(기능) >> 출력 순으로 진행된다
		view.printInputInteger(1); // 첫번째 값을 입력 받기 전 요구사항 출력(CalcView)
		int num1 = view.inputInteger(); // 첫번째 값을 저장하기 전 유효성 검사 후 저장(CalcView)
		view.printInputOp();// 연산자 값을 입력 받기 전 요구사항 출력(CalcView)
		String op = view.inputStringOp();// 연산자 유효성 검사 후 저장(CalcView)
		view.printInputInteger(2);// 두번째 값을 입력 받기 전 요구사항 출력(CalcView)
		int num2 = view.inputInteger();// 두번째 값을 입력 받기 전 유효성 검사 후 저장(CalcView)

		double result = 0.0; // 결과값을 저장할 공간 초기화
		if (op.equals("+")) { // 저장한 연산자가 "+"인경우 CalcModel에서 더하기 메서드 실행
			result = model.plus(num1, num2); // plus메서드에 num1와 num2의 값을 전달한다
		} else if (op.equals("-")) {// 저장한 연산자가 "-"인경우 CalcModel에서 더하기 메서드 실행
			result = model.minus(num1, num2);// minus메서드에 num1와 num2의 값을 전달한다
		} else if (op.equals("x") || op.equals("X") || op.equals("*")) {// 저장한 연산자가 "*, x, X"인경우 CalcModel에서 곱하기 메서드 실행
			result = model.multiple(num1, num2);// multiple메서드에 num1와 num2의 값을 전달한다
		}else if(op.equals("/")) {
			result = model.divide(num1, num2);
		}
		view.printResult(num1, op, num2, result);
		return result;// 결과값을 반환한다
	}
}
