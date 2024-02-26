package day005;

import java.util.Random;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		funcB(10, 20);
		funcB(11, 3);
		funcB(-1, 0);
		System.out.println(funcC());
		Scanner sc = new Scanner(System.in);
		funcD(sc.nextInt(), sc.nextInt());
	}

//	public static void hello() {
//		System.out.println("안녕하세요 :D");
//	}
//	
//	public static void printInfo(String name) {
//		System.out.println(name+"님의 정보를 출력하는 함수입니다");
//	}
//	
//	public static int plusTen(int num) {
//		num+=10;
//		System.out.println(num);
//		return num;
//	}

	public static void funcB(int a, int b) {
		System.out.println(a + b);
	}

	public static double funcC() {
		Random random = new Random();
		int[] ar = new int[3];
		int total = 0;
		for (int i = 0; i < ar.length; i++) {
			ar[i] = random.nextInt(10) + 1;
			total += ar[i];
		}

//		for(int a:ar) {
//			total+=a;
//		}
		// 실수 값으로 변환 후 평균값을 반환
		return total * 1.0 / ar.length;
	}

	// 변수 2개를 입력값으로 받아온다
	public static void funcD(int a, int b) {
		if (a >= b) { // a가 크거나 같으면 a를
			System.out.println(a);
		} else { // b가 크면 b를 반환한다
			System.out.println(b);
		}
	}
	
	public static void funcE(int a, int b) {
		System.out.println(Math.max(a, b));
	}
	
	public static int funcF(int a, int b) {
		return Math.max(a, b);
	}
	
}
