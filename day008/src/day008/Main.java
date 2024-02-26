package day008;

import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		sc.close();
		int count = 0;
		while (true) {
			count++;
			if (v >= a) {
				v = v - a + b;
			} else {
				break;
			}
		}
		System.out.println(count);
	}
}
