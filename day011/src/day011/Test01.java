package day011;

import java.util.ArrayList;
import java.util.Random;

public class Test01 {

	public static void main(String[] args) {
		Random random = new Random();
		ArrayList<Integer> ar = new ArrayList<Integer>();
		int total = 0;
		int num = random.nextInt(21)+10;
		System.out.println(num);
		for(int i = 0; i<(num+1)/2; i++) {
			ar.add(i*2+1);
			total+=(i*2+1);
		}
		System.out.println(ar);
		System.out.println(total);
		System.out.printf("%.2f",total*1.0/ar.size());
		
		//재고와 관리자 모드가 존재하는 프로그램
	}
}
