package day005;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test2 {

	public static void funcA() {
		Random random = new Random();
		int[] ar = new int[3];
		int max = 0;
		for (int i = 0; i < ar.length; i++) {
			ar[i] = random.nextInt(6) + 1;
		}
		System.out.println(Arrays.toString(ar));
		Arrays.sort(ar);
		System.out.println(ar[ar.length-1]);
	}
	
//	사용자에게 정수 2개 입력 받기
//	정수의 합을 main으로 반환 -> return
	public static int funcB(int a, int b) {
		return a+b;
	}
	
	// 메인함수에서 사용자에게 입력받은 2개 정수
	//두개의 정수 중 더 작은값을 콘솔에 출력 (syso)후 반환(return)
	public static int funcC(int a, int b) {
		int min = Math.min(a, b);
		System.out.println(min+"함수 안에서 출력");
		return min;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		funcA();
		System.out.println("두 정수를 입력하세요");
		System.out.println(funcB(sc.nextInt(), sc.nextInt()));
		System.out.println("두 정수를 입력하세요");
		System.out.println(funcC(sc.nextInt(), sc.nextInt())+"main으로 반환받은 값 출력");
	}

}
