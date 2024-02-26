package calc;

// Bean이란
// 웹에서 한가지 기능을 담당하는 클래스
// 그 클래스의 객체를 의미함
public class CalcBean {
	private int num1, num2, result;
	private String op;

	public void calc() {
		if (op == null) {
			return;
		}
		if (op.equals("+")) {
			result = num1 + num2;
		} else {
			result = num1 - num2;
		}
	}

	public CalcBean() {
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

}
