package day013.model;

public class CalcModel {
	// "+" 연산자를 입력받았을 때 실행할 메서드
	public double plus(int num1, int num2) {
		return num1+num2;
	}      
	// "-" 연산자를 입력받았을 때 실행할 메서드
	public double minus(int num1, int num2) {
		return num1-num2;
	}     
	// "x || X || *" 연산자를 입력받았을 때 실행할 메서드
	public double multiple(int num1, int num2) {
		return num1*num2;
	}
	
	public double divide(int num1, int num2) {
		return num1*1.0/num2;
	}
}
