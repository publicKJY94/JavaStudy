package day007;

// 자료형에 대하여
// 학생 3명의 중간고사 점수를 저장하고 싶어요
// 상품 5개의 상품명을 저장하고 싶어요
// 수학문제 x, y변수를 선언하고 싶어요
// 학생 1명의 이름, 나이 점수를 저장하고 싶어요(복합적 자료형)
// -> 학생이라는 자료형이 필요한 상태이다

class Student {
	// 학생이라는 자료형은
	// 이름
	String name;
	// 나이
	int age;
	// 점수
	int score;

	public Student() {
		;
	}

	// 를 가지고 있음 ==> 멤버변수, 필드, 속성, Attribute, property
	void printInfo() {
		System.out.println("func에서 출력중입니다");
		System.out.println(name);
		System.out.println(age);
		System.out.println(score);
	}

	public Student(String name, int age, int score) {
		this.name = name;
		this.age = age;
		this.score = score;
	}
}

class Pokemon {
	int level;
	int exp;
	String name;

//	public Pokemon() {
//		;
//	}

	public Pokemon(int level, int exp, String name) {
		this.level = level;
		this.exp = exp;
		this.name = name;
	}

	void hello() {
		System.out.printf("%s Lv.%d [%d/100]\n", name, level, exp);
	}
}

class Dog {
	String name;
	String dogType;

	public Dog() {
		;
	}

	public Dog(String name, String dogType) {
		this.name = name;
		this.dogType = dogType;
	}

	void printInfo() {
		System.out.printf("%s(이)는 %s입니다\n", name, dogType);
	}

}

public class Test01 {

	public static void main(String[] args) {

		Student student = new Student();
		// "."은 멤버변수접근 / 멤버접근 연산자
		student.name = "홍길동";
		student.age = 20;
		student.score = 100;

		System.out.println(student);
		System.out.println(student.name);

		student.printInfo();
		Student proto = new Student("김진영", 29, 88);
		proto.printInfo();

		Pokemon pocketmon = new Pokemon(99, 58, "피카츄");
		Pokemon pocketmon2 = new Pokemon(50, 97, "꼬부기");
		pocketmon.exp = 87;
		pocketmon.hello();
		pocketmon2.hello();

		Dog dog = new Dog("나비", "잉글랜드쉽독");
		Dog dog2 = new Dog("초코", "진돗개");
		dog.printInfo();
		dog2.printInfo();
	}

}
