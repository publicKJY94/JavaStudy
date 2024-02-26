package day008;

class ColorPoint {
	String color;
	int x;
	int y;

	public ColorPoint() {
		this(0, 0);
	} // n번의 과정을 거쳐 최종 함수에 도착

	public ColorPoint(int x, int y) {
		this("검정", x, y);
	}

	public ColorPoint(String color) {
		this(color, 0, 0);
	}

	public ColorPoint(String color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
	}

	void printColorPoint() {
		System.out.printf("%s색 점은 (%d,%d)에 있습니다\n", this.color, this.x, this.y);
	}
}

class Circle {
	String type;
	double r;
	static final double PI = 3.14;

	public Circle(String type) {
		this(type, 1);
	}

	public Circle(int r) {
		this("원", r);
	}

	public Circle(String type, double r) {
		this.type = type;
		this.r = r;
	}
	
	void printExtent() {
		System.out.printf("%s는(은) 넓이가 %.2f입니다\n",this.type,calExtent());
	}
	
	double calExtent() {
		return Math.pow(this.r, 2)*Circle.PI;
	}

}

public class Task {
	public static void main(String[] args) {
		ColorPoint p1 = new ColorPoint(); // 검정(0,0)
		ColorPoint p2 = new ColorPoint(1, 2); // 검정(1,2)
		ColorPoint p3 = new ColorPoint("분홍"); // 분홍(0,0)
		ColorPoint p4 = new ColorPoint("빨강", 3, 5); // 빨강(3,5)
		p1.printColorPoint();
		p2.printColorPoint();
		p3.printColorPoint();
		p4.printColorPoint();

		Circle c1 = new Circle(10); // 원 : 반지름 10 넓이 314.0
		Circle c2 = new Circle("도넛"); // 도넛 : 반지름 1 넓이 3.14
		Circle c3 = new Circle("피자", 12); // 피자 : 반지름 12 넓이 452.16
		c1.printExtent();
		c2.printExtent();
		c3.printExtent();
	}
}
