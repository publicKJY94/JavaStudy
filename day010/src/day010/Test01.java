package day010;

abstract class Shape{
	String name;
	double  area;
	public Shape(String name) {
		this.name = name;
		this.area = 0.0;
	}
	// 추상 객체의 메소드는 사용할 수 없다
//	abstract void printInfo();
	void printInfo(){
		System.out.printf("%s은(는) 넓이가 %f입니다\n", name, area);
		System.out.println("2");
	}
}

class Circle extends Shape{
	int radious;
	static final double PI = 3.14;
	public Circle() {
		this("원");
	}
	
	public Circle(String name) {
		this(name,1);
	}
	
	public Circle(String name, int radious) {
		super(name);
		this.radious = radious;
		this.area = this.radious*this.radious*Circle.PI;
	}

	@Override
	void printInfo() {
		System.out.println("1");
		super.printInfo();
//		System.out.println("반지름" + this.radious);
	}
}

class Rect extends Shape{
	int x;
	int y;
	public Rect() {
		this("사각형",1,1);
	}
	
	public Rect(String name) {
		this(name,1,1);
	}

	public Rect(String name, int x, int y) {
		super(name);
		this.x = x;
		this.y = y;
		this.area = this.x*this.y;
	}

//	@Override
//	void printInfo() {
//	}
}



public class Test01 {

	public static void main(String[] args) {
		Shape shape = new Circle("원", 10);
		shape.printInfo();
	}

}
