package day009;

//점과 색깔점
class Point {
	int x;
	int y;

	Point() {
		this(0, 0);
	}

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void move(int x) {
		this.x += x;
		this.y += y;
	}

	void printInfo() {
		System.out.println("점 (" + this.x + "," + this.y + ")입니다.");
	}
}

class ColorPoint extends Point {
	String color;

	ColorPoint(String color) {
		this(color, 0, 0);
	}

	ColorPoint(int x, int y) {
		this("검정", x, y);
	}

	ColorPoint(String color, int x, int y) {
		super(x,y);
		this.color = color;
	}

	@Override
	void printInfo() {
		System.out.println(color+"("+x+","+y+")");
	}
	
}

public class Test02 {

	public static void main(String[] args) {
		ColorPoint cp = new ColorPoint("분홍", 1, 2);
		cp.printInfo();
		cp.move(10);
		cp.printInfo();

	}

}
