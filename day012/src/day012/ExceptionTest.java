package day012;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 사용자가 입력한 정수를 저장하는 공간
		int num;
		while (true) {
			System.out.println("정수 입력 >> ");
			try {
				num = sc.nextInt();
				// 숫자를 입력받았을 때, 1~10사이를 벗어나면 exception을 발생시킨다!
				if (num < 0 || num > 10) {
					throw new Exception(); // 예외상황 발생시 넘겨주어라
				} else {
					System.out.println(num + "입력 성공");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("정수만 입력 가능합니다");
			} catch (Exception e) {
				System.out.println("범위를 벗어났습니다");
				continue;
			}
		}
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(10);
		ar.add(1);
		ar.add(2);
		ar.add(0);
		ar.add(-2);
		while (true) {
			System.out.println("정수 입력 >> ");
			try {
				int num2 = sc.nextInt();
				double result = 100 / ar.get(num2);
				System.out.println("결과 : " + result);
				break;
			} catch (ArithmeticException e) {
				System.out.println("0으로는 나눌 수 없습니다");
				continue;
			} catch (IndexOutOfBoundsException e) {
				System.out.println("인덱스 범위를 벗어났습니다");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("정수만 입력 가능합니다");
				continue;
			}
		}
	}
}
