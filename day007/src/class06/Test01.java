package class06;

import java.util.Scanner;

class Person {
	String name;
	int age;

	public Person(String name) {
		this.name = name;
		age = 20;
	}

	void hello() {
		System.out.println(name + "은 " + age + "살 입니다");
	}

	int checkAge(Scanner sc) {
		int age;
		while (true) {
			System.out.println("나이를 입력하세요");
			age = sc.nextInt();
			if (1 <= age && age <= 100) {
				break;
			}
			System.out.println("나이는 1~100살까지만 입력할 수 있습니다.");
		}
		return age;
	}

}

public class Test01 {
	public static String inputName(Scanner sc) {
		System.out.println("이름을 입력해주세요");
		String name = sc.next();
		return name;
	}

	public static String checkName(Scanner sc) {
		while (true) {
			System.out.println("이름을 입력해주세요");
			String name = sc.next();
			System.out.println("정말 이 이름으로 하시겠습니까?");
			String checkName = sc.next();
			if (checkName.equals("네")) {
				return name;
			} else {
				System.out.println("이름을 다시 입력해 주세요");
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Person person = new Person("김진영");
		Person person2 = new Person("정현진");
		// hello사용
		person.hello();
		person2.hello();
		// 나이 유효성 검사
		person.age = person.checkAge(sc);
		person.hello();
		person2.age = person2.checkAge(sc);
		person2.hello();
		// 사용자에게 이름 입력받기
		System.out.println("이름을 입력해주세요");
		Person person3 = new Person(sc.next());
		person3.hello();

		System.out.println("이름을 입력해주세요");
		Person person300 = new Person(inputName(sc));
		person300.hello();

		Person person4 = new Person(checkName(sc));
		person4.hello();

		Person[] ar = new Person[5];
		ar[0] = person;
		ar[1] = person2;
		ar[2] = person3;
		ar[3] = person4;
		ar[4] = new Person(inputName(sc));
		for (Person a : ar) {
			a.hello();
		}
	}
}
