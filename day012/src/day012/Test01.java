package day012;

import java.util.*;

public class Test01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		((n+1)^2 - (a^2 + b^2)-1)/2
		int repeat = sc.nextInt();
		int num1 = 0, num2 = 1, num = 1;
		for (int i = 0; i < repeat; i++) {
			num= num1+num2;
			num1 = num2;
			// 검색할 성적을 입력 받는다
			// 학생 전체 목록에서 60점이 넘는 인원만 출력한다
			
		}
		System.out.println(num2);
	}
}

// 종류의 개수를 입력 받는다
// 종류의 개수를 입력받으면서 같은종류 값을 더해준다
// 총갯수+1의 제곱에 각 종류의 개수별로 제곱해서 빼준다
// 2로 나누어 준다

//	StringBuffer reader > inputStream > outputStream
//		long beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기
//		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
//		long secDiffTime = (afterTime - beforeTime); // 두 시간에 차 계산long afterTime = System.currentTimeMillis(); //
//		// 코드 실행 후에 시간 받아오기
//		System.out.println(secDiffTime);
