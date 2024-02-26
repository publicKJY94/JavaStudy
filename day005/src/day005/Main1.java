package day005;

public class Main1 {

	public static void main(String[] args) {
		int a = 0;
		int tmp;
		int[] ar = new int[5];
		ar[0] = 3;
		ar[1] = 2;
		ar[2] = 5;
		ar[3] = 1;
		ar[4] = 4;

		// 정렬을 몇번할지 결정하는 반복문
		// 배열의 요소개수만큼 수행하겠다! == 5번 정렬하겠다!
		while (true) {// 언제 종료될지 알 수 없다
			// 앞에서부터 차례대로 대소 비교하면서 자리 바꾸기
			for (int i = 1; i < ar.length; i++) {
				if (ar[a] > ar[i]) {
					tmp = ar[a];
					ar[a] = ar[i];
					ar[i] = tmp;
				}
			}
			
		}
	}
}
