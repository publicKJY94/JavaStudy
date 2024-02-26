package day013.view;

import java.util.Scanner;

public class CalcView {
	private Scanner sc; // 스캐너를 공유자원으로 활용하기 때문에 클래스에서 매개변수로 선언

	public CalcView() {
		sc = new Scanner(System.in); // 생성자에서 스캐너 초기화
	}
	// 사용자와의 input, output만 고려
	// 사용자가 제공받아야하는 문구를 제작

	public void printInputInteger(int num) { // 몇 번째 정수를 입력받는 것인지 차례를 알기위해 외부로부터 순서 num을 입력받는다
		System.out.print("정수 " + num + " 입력 >> ");
	}

	public void printInputOp() {
		System.out.print("연산자 입력 >> ");
	}

	public void printResult(int num1, String op, int num2, double result) {
		if (op.equals("/")) {
			System.out.println(num1 + op + num2 + "의 결과는 " + result + "입니다");
		}else {
			System.out.println(num1 + op + num2 + "의 결과는 " + (int)result + "입니다");
		}
	}

	// 정수를 입력 받을때 마다 검사하는 유효성 검사
	public int inputInteger() {
		int num;// scope를 맞춰주기 위해 while문 밖에서 선언! >> return에서 사용하기 위함
		while (true) { // 사용자가 언제 정상적으로 입력할지 모르기 때문에 무한루프를 걸어준다
			try { // 유효성 검사간 정술를 입력하지 않았을 경우를 걸러주기위한 try-catch문(검사를 실행할 문장)
				num = sc.nextInt(); // 검사를 실행할 문장
				break; // 검사결과가 양호하면 반복문을 멈춘다
			} catch (Exception e) { // 예외상황 발생시 실행될 문장
				sc.nextLine(); // 스캐너에 남아있는 데이터(버퍼)를 초기화 해준다
				System.out.println("정수만 입력 가능합니다"); // 안내문구 출력
				continue; // 재입력 받기위해서 반복문을 재실행한다
			}
		}
		return num; // 유효성 검사를 통과한 num값을 반환해 준다
	}

	// 연산자 입력 받았을 때 유효성 검사
	public String inputStringOp() {
		String op;
		while (true) {
			op = sc.next();
			if (op.equals("+") || op.equals("-") || op.equals("x") || op.equals("X") || op.equals("*")
					|| op.equals("/")) {
				break;
			}
			System.out.println("[로그] 없는 연산자이거나 아직 개발되지않은 연산자입니다...");
		}
		return op;
	}

}
