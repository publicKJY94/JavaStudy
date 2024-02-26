package day004;

import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random rand = new Random();
		int[] ar = new int[10];
		while (true) {
			for (int i = 0; i < ar.length; i++) {
				ar[i] = rand.nextInt(20); // 범위는 원하시는 만큼 설정하세요
				System.out.println(ar[i]); // ar[i]값의 변화 참고용
			}
			int[] arr = Arrays.stream(ar).distinct().toArray(); // ar배열의 중복제거(distinct)후 배열로 바꾸어 주겠다
			if (arr.length >= ar.length) { // 새로 생성된 배열이 10칸짜리 배열이 완성되었을 경우 생성을 중지한다
				break;
			}
		}
		Arrays.sort(ar);
		System.out.println(Arrays.toString(ar));
//		String[] ar = {"apple", "banana", "kiwi", "cherry", "cron"};
//		Arrays.sort(ar);
//		System.out.println(Arrays.toString(ar));
	}

	

}
