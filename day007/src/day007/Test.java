package day007;

import java.util.Random;
import java.util.Scanner;

public class Test {
	public static void funcA(Random random, Scanner sc) {
		System.out.println("정수 입력 >> ");
		int input = sc.nextInt();
		
		System.out.println("랜덤정수를 생성합니다");
		int randNum = random.nextInt(10)+1; 
		System.out.printf("생성된 정수는 [ %d ]입니다.\n",randNum);
		
		int result = input + randNum;
		System.out.printf("합은 %d입니다\n",result);
	}
	
	public static void funcB(int input, Random random) {
		System.out.println("랜덤정수를 생성합니다");
		int randNum = random.nextInt(10)+1; 
		System.out.printf("생성된 정수는 [ %d ]입니다.\n",randNum);
		
		int result = input + randNum;
		System.out.printf("합은 %d입니다\n",result);
	}
	
	public static void funcC(int input, int randNum) {
		int result = input + randNum;
		System.out.printf("합은 %d입니다\n",result);
	}

	public static void main(String[] args) {
		// 숫자와 숫자를 더하는 코드
		Scanner sc =new Scanner(System.in);
		Random random = new Random();
		funcA(random, sc);
		
		System.out.println("정수 입력 >> ");
		int input = sc.nextInt();
		funcB(input, random);
		
		System.out.println("랜덤정수를 생성합니다");
		int randNum = random.nextInt(10)+1; 
		System.out.printf("생성된 정수는 [ %d ]입니다.\n",randNum);
		funcC(input, randNum);
		
		int result = input + randNum;
		System.out.printf("합은 %d입니다\n",result);
	}
}
