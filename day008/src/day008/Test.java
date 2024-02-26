package day008;

class Point {
	int x;
	int y;

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	public Point(int x) {
		this.x = x;
		this.y = x;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void printInfo() {
		System.out.println("점(" + this.x + "." + this.y + ")");
	}
}

public class Test {

	public static void main(String[] args) {
		Point point01 = new Point();
		Point point02 = new Point(10);
		Point point03 = new Point(1, 2);
	}
}
