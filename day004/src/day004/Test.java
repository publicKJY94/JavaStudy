package day004;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		int[] student = new int[5];
		int index = 0;
//		student[index++] = 67;
//		student[index++] = 37;
		Scanner sc = new Scanner(System.in);
		while (true) {
			int option; // 옵션을 저장
			System.out.println("===================");
			System.out.println("=학생부 프로그램 v1.0=");
			System.out.println("===================");
			System.out.println("1. 학생 추가");
			System.out.println("2. 전체 출력");
			System.out.println("3. 학생 검색");
			System.out.println("4. 1등 출력");
			System.out.println("5. 정보 변경");
			System.out.println("6. 정보 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.println("===================");
			System.out.print("번호입력 >> ");
			int action = sc.nextInt();
			if (action == 0) {
				System.out.println("학생부 프로그램을 종료합니다....");
				break;
			} else if (action == 1) {

				if (student.length <= index) {
					System.out.println("해당 프로그램은 " + student.length + "명까지 지원하고 있습니다");
					System.out.println("관리자에게 문의해주세요");
					continue;
				}
				while (true) { // 무한루프
					System.out.printf("%d번 학생을 추가합니다\n", index + 1);
					System.out.print("학생 점수 입력 >>");
					int score = sc.nextInt();
					// 유효성 검사
					if (score >= 0 && score <= 100) {
						student[index++] = score;
						System.out.println("추가되었습니다");
						break;
					} else {
						System.out.println("점수는 0~100점까지 입력 가능합니다");
						System.out.println("확인 후 다시 입력해주세요");
					}
				}
			} else if (action == 2) {
				if (index <= 0) {
					System.out.println("출력할 학생 데이터가 없습니다");
					continue;
				}
				System.out.println("전체출력");
				// 존재하는 학생만큼
				
				for (int i = 0; i < index; i++) {
					System.out.printf("%d번 학생의 점수는 %d점 입니다\n", i + 1, student[i]);
				}
				while (true) {
					System.out.println("계속하려면 1을 누르세요");
					option = sc.nextInt();
					if (option == 1) {
						break;
					}
				}
			} else if (action == 3) {
				if (index <= 0) {
					System.out.println("검색할 학생 데이터가 없습니다");
					continue;
				}
				System.out.println("검색할 학생번호 입력");
				option = sc.nextInt() - 1;
				if (option < index) {
					System.out.printf("%d번 학생은 %d점 입니다\n", option + 1, student[option]);
				} else {
					System.out.println("없는 번호의 학생입니다");
					System.out.println("확인 후 다시 이용해주세요");
				}
			} else if (action == 4) {
				if (index <= 0) {
					System.out.println("학생 데이터가 없습니다");
					continue;
				}
				int rank = 0;
				for (int i = 1; i < index; i++) {
					if (student[i] > student[rank]) {
						rank = i;
					}
				}
				System.out.printf("1등은 %d점으로 %d번 학생입니다\n", student[rank], rank + 1);

				// 사용자한테 입력 받아서 값을 저장하고
				// 값이 1인경우에만 뒤(어디로가고싶은지?)로 넘어간다
				// 1외에 다른값이 입력되었을 때 다시 값을 입력받는다
				// "계속하려면 1을 누르세요."
				while (true) {
					System.out.println("계속하려면 1을 누르세요");
					option = sc.nextInt();
					if (option == 1) {
						break;
					}
				}
			} else if (action == 5) {
				// 학생 데이터의 존재 유무 검사
				if (index <= 0) {
					System.out.println("변경 가능한 데이터가 없습니다");
					continue;
				}
				// 변경할 학생의 정보를 조회한다 학번의 유효성 검사
				while (true) {
					System.out.println("변경할 학생의 번호를 입력하세요");
					System.out.print("학생번호 입력 >> ");
					option = sc.nextInt() - 1;
					if (option < index && option >= 0) {
						System.out.printf("%d번 학생의 현재 점수는 %d입니다\n", option + 1, student[option]);
						break;
					} else {
						System.out.println("없는 번호의 학생입니다");
						System.out.println("확인 후 다시 입력해주세요");
					}
				}
				
				//코드 깊이 줄이기
				// 사용자로부터 변경할 학생의 점수를 입력받아 유효성 검사
				while (true) {
					System.out.println("변경할 점수 입력 >>");
					int score = sc.nextInt();
					if (score == student[option]) {
						System.out.println("기존 점수와 동일하여 변경되지 않았습니다");
					} else if (score >= 0 && score <= 100) {
						student[option] = score;
						System.out.printf("변경된 %d번 학생의 현재 점수는 %d입니다\n", option + 1, student[option]);
						// 변경이후 학생의 정보를 표시
						break;
					} else {
						System.out.println("점수는 0~100점까지 입력 가능합니다");
						System.out.println("확인 후 다시 입력해주세요");
					}
				}
			} else if(action==6) {
				if(index<=0) {
					System.out.println("제거할 학생 정보가 없습니다");
					continue;
				}
				System.out.printf("%d번째 학생정보를 삭제합니다\n",index);
				index--;
				System.out.println("제거 완료");
			}
		}
	}
}
