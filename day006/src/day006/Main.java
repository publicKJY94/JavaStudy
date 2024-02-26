package day006;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] stuAr = new int[3];
		int index = 0;
		while (true) {
			System.out.println("=== 학생부 프로그램 ===");
			System.out.println("1. 추가");
			System.out.println("2. 목록출력");
			System.out.println("3. 검색");
			System.out.println("4. 변경");
			System.out.println("5. 삭제");
			System.out.println("6. 1등 출력");
			System.out.println("0. 종료");
			System.out.print("입력 >> ");
			int action = sc.nextInt();
			System.out.println();

			if (action == 0) {
				shutdown();
				break;
			} else if (action == 1) {
				if (!checkInsert(stuAr, index)) {
					continue;
				}
				insertStu(stuAr, index++, sc);
			} else if (action == 2) {
				if (!hasStu(index)) {
					continue;
				}
				printStuArr(stuAr, index);
			} else if (action == 3) {
				if (!hasStu(index)) {
					continue;
				}
				printStu(stuAr, index, sc);
			} else if (action == 4) {
				if (!hasStu(index)) {
					continue;
				}
				updateStu(stuAr, index, sc);
			} else if (action == 5) {
				if (!hasStu(index)) {
					continue;
				}
				deleteStu(index--);
			}
		}

	}

	public static boolean checkStuNum(int stuNum, int index) {
		if (1 <= stuNum && stuNum <= index) {
			return true;
		}
		System.out.println("해당 번호의 학생은 없습니다");
		return false;
	}

	public static boolean checkScore(int score) {
		if (0 <= score && score <= 100) {
			return true;
		}
		System.out.println("점수는 0~100까지만 가능합니다");
		return false;
	}

	public static boolean checkInsert(int[] stuAr, int index) {
		if (index >= stuAr.length) {
			System.out.println("학생부가 가득찼습니다");
			return false;
		}
		return true;
	}

	public static boolean hasStu(int index) {
		if (index <= 0) {
			System.out.println("학생 데이터가 전혀 없습니다");
			return false;
		}
		return true;
	}

	public static void deleteStu(int index) {
		System.out.printf("%d번 학생 삭제 완료", index);
	}

	public static void updateStu(int[] stuArr, int index, Scanner sc) {
		System.out.println();
		while (true) {
			System.out.print("변경할 학생의 번호 입력 >> ");
			int stuNum = sc.nextInt();
			if (checkStuNum(stuNum, index)) {
				while (true) {
					System.out.print("변경할 점수 입력 >> ");
					int score = sc.nextInt();
					if (checkScore(score)) {
						stuArr[stuNum - 1] = score;
						System.out.println(stuNum + "번 학생의 점수 " + score + "점으로 변경완료!");
						break;
					}
				}
				break;
			}
		}
		System.out.println();
	}

	public static void printStu(int[] stuAr, int index, Scanner sc) {
		// 학번으로 학생 1명의 정보 검색
		while (true) {
			System.out.println("검색할 학생 번호 입력 >> ");
			int stuNum = sc.nextInt();
			if (checkStuNum(stuNum, index)) {
				System.out.printf("%d번 학생 점수 : %d\n", stuNum - 1, stuAr[stuNum - 1]);
				break;
			}
		}
	}

	public static void printStuArr(int[] stuAr, int index) {
		// 반복문으로 학생 정보를 출력
		System.out.println("=== 목록 출력 ===");
		for (int i = 0; i < index; i++) {
			System.out.printf("%d번 학생 점수 : %d\n", i + 1, stuAr[i]);
		}
	}

	public static void insertStu(int[] stuAr, int index, Scanner sc) {
		while (true) {
			System.out.printf("%d번 학생의 점수 입력 >> ", index + 1);
			int score = sc.nextInt();
			if (checkScore(score)) {
				stuAr[index++] = score;
			}
			System.out.printf("%d번 학생 추가 완료\n", index);
		}
	}

	public static void shutdown() {
		System.out.println();
		System.out.println("프로그램을 종료합니다 . . .");
		System.out.println();
	}
}
