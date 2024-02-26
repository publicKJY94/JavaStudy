package day007;

import java.util.Scanner;

public class Test02 {
	// 입력된 정수값이 범위에 해당하나요?
	public static boolean funcA(int a, int b) {
		Scanner sc = new Scanner(System.in);
		int input;
		while (true) {
			System.out.println("정수입력 >> ");
			input = sc.nextInt();
			if (a <= input && input <= b) {
				return true;
			}
			return false;
		}
	}

	public static int funcB(int a, int b) {
		Scanner sc = new Scanner(System.in); // 사용할 때마다 스캐너를 호출해야 하는데
		int input;
		while (true) {
			System.out.println("정수입력 >> ");
			input = sc.nextInt();
			if (a <= input && input <= b) {
				break;
			}
		}
		return input;
	}

	public static boolean func(int a, int b, int input) {
		if (a <= input && input <= b) {
			return true;
		}
		return false;
	}

	// 특정 배열의 특정값이 존재하나요?
	public static boolean funcC(int[] datas, int key) {
		boolean flag = false;
		for (int data : datas) {
			if (data == key) {
				flag = true;
				break;
			}
		}
		return false;
	}

	public static int funcD(int[] datas, int key) {
		int keyIndex = -1; // 값이 없을 시 오류를 반환하게끔 설정
		for (int i = 0; i < datas.length; i++) {
			if (datas[i] == key) {
				keyIndex = i;
			}
		}
		return keyIndex;
	}

	public static boolean funcE(int[] datas) {
		boolean flag = false;

		int i;
		for (i = 0; i < datas.length - 1; i++) {
			if (datas[i] > datas[i + 1]) {
				break;
			}
		}
		if (i >= datas.length - 1) {
			flag = true;
		}
		return flag;
	}

	// 이 배열이 정렬되었나요?
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = 0;
		boolean flag = true;
		while (flag) {
			System.out.println("정수입력 >> ");
			input = sc.nextInt();
			flag = func(1, 10, input);
			if (!flag) {
				System.out.println(input);
			}
		}
		int a = 1, b = 5, c = 10;
		int[] datas = new int[5];
		// [ 10 20 30 40 50 ]
		for (int i = 0; i < datas.length; i++) {
			datas[i] = (i + 1) * 10;
		}

	}
}
