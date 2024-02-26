package day009;

import java.util.Random;
import java.util.Scanner;

class Person {
	String name;
	int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	void printInfo() {
		System.out.println(this.name + "님 " + this.age + "살");
	}
}

class Student extends Person {
	int score;
	public Student(String name, int age, int score) {
		super(name, age);
		this.score = score;
	}

	void test() {
		Random random = new Random();
		while (true) {
			int newScore = random.nextInt(101);
			if (this.score <= newScore && newScore <= 100) {
				this.score = newScore;
				break;
			}
		}
	}

	@Override
	void printInfo() {
		System.out.printf("%s학생 %d살 %d점\n", this.name, this.age, this.score);
	}

}

class Employee extends Person {
	String dep;
	
	public Employee(String name, int age, String dep) {
		super(name, age);
		this.dep = dep;
	}

	@Override
	void printInfo() {
		System.out.printf("%s팀 %s님 %d살\n", this.dep, this.name, this.age);
	}

}

public class People {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 사람의 배열에 추가할때 가능여부 판단 -> 양호
		int index = 0;
		Person[] ar = new Person[5];
		// 출력
		while (true) {
			System.out.println("==================");
			System.out.println("= 회원 관리 프로그램 =");
			System.out.println("==================");
			System.out.println("1. 사람 추가");
			System.out.println("2. 사람 목록출력");
			System.out.println("3. 학생 목록 출력후 재시험");
			System.out.println("0. 프로그램 종료");
			System.out.println("입력 >> ");
			int action = sc.nextInt();
			if (action == 0) {
				System.out.println("프로그램 종료. . . ");
				break;
			} else if (action == 1) {
				System.out.println("=== 사 람  추 가 ===");
				System.out.println("1. 학생 추가");
				System.out.println("2. 직원 추가");
				System.out.println("입력 >> ");
				int option = sc.nextInt();
				if (option == 1) {
					if (index > ar.length - 1) {
						System.out.printf("해당 프로그램은 최대 %d명까지 추가 가능합니다\n", ar.length);
						continue;
					}
					System.out.println("학생의 이름, 나이, 점수를 입력하세요");
					ar[index++] = new Student(sc.next(), sc.nextInt(), sc.nextInt());
					System.out.println("학생이 추가되었습니다");
				} else if (option == 2) {
					System.out.println("직원의 이름, 나이, 부서를 입력하세요");
					ar[index++] = new Employee(sc.next(), sc.nextInt(), sc.next());
					System.out.println("직원이 추가되었습니다");
				} else {
					System.out.println("잘못 입력하셨습니다");
					continue;
				}
			} else if (action == 2) {
				if(index<=0) {
					System.out.println("데이터 없음!");
					continue;
				}
				System.out.println("사람 전체 목록 출력");
				for (int i = 0; i < index; i++) {
					ar[i].printInfo();
				}
			} else if (action == 3) {
				for (int i = 0; i < index; i++) {
					System.out.println(ar[i] instanceof Student);
					// 부서가 ""로 출력을 통제할 수 있는가? -> 네
					if (ar[i] instanceof Student) { // 타입비교
						ar[i].printInfo();
						((Student)ar[i]).test();
						((Person)ar[i]).printInfo();
					}
				}
			} else {
				System.out.println("아직 지원되지 않는 기능입니다");
			}
		}

	}
}
