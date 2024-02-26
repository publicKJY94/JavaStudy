package day010;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test02 {
	public static Set inputSet(Scanner sc) {
		Set<Integer> set = new HashSet<Integer>();
		int num = sc.nextInt();
		for (int i = 0; i < num; i++) {
			set.add(sc.nextInt());
			System.out.println(set);
		}
		return set;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Set<Integer> set = inputSet(sc);
		set.containsAll(set);
		// n개를 입력 받았을 때 (n*=n-i)/=i*(++i) - (a*a+1)/2
		// 1가지를 입는 경우
		// 2가지를 입는 경우
		// 3가지를 입는 경우
		// 4가지를 입는 경우
		//		n!-(n-2)!;
		//		a,b,c,.....;
		//		
		//		n = a+b+c+......;
		//		(a+1)(b+1)(c+1).....;
	}
}
// n*n-1  / 1*2