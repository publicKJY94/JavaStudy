package view;

import java.util.ArrayList;

import model.ProductDTO;

public class CommonVIEW extends VIEW {

	public void printMenu() {
		System.out.println("0. 프로그램종료");
		System.out.println("1. 상품목록전체출력");
	}

	public void printSyso() {
		System.out.println("프로그램을 종료합니다. . .");
	}

	public int inputPid() {
		int pid;
		while (true) {
			System.out.print("상품번호입력 >> ");
			pid = sc.nextInt();
			if (1001 <= pid && pid <= 2000) {
				break;
			}
			System.out.println("제대로 입력해주세요!");
		}
		return pid;
	}

	public void printNoData() {
		System.out.println("해당 정보는 없습니다...");
	}

	public int inputCnt() {
		int cnt;
		while (true) {
			System.out.print("재고입력 >> ");
			cnt = sc.nextInt();
			if (1 <= cnt && cnt <= 100) {
				break;
			}
			System.out.println("제대로 입력해주세요!");
		}
		return cnt;
	}

	public void printTrue() {
		System.out.println("성공!");
	}

	public void printFalse() {
		System.out.println("실패...");
	}

	public int inputAction() {
		int action;
		while (true) {
			System.out.print("번호입력 >> ");
			action = sc.nextInt();
			if (1 <= action && action <= 10) {
				break;
			}
			System.out.println("제대로 입력해주세요!");
		}
		return action;
	}

	public int inputPrice() {
		int price;
		while (true) {
			System.out.print("가격입력 >> ");
			price = sc.nextInt();
			if (1 <= price && price <= 1000000) {
				break;
			}
			System.out.println("제대로 입력해주세요!");
		}
		return price;
	}

	public void printDatas(ArrayList<ProductDTO> datas) {
		if (datas == null || datas.size() <= 0) {
			System.out.println("출력할 데이터가 없습니다!");
		}
		for (ProductDTO data : datas) {
			System.out.println(data);
		}
	}

	public void printData(ProductDTO data) {
		if (data == null || data.getPid() == 0) {
			System.out.println("출력할 데이터가 없습니다!");
		}
		System.out.println(data);
	}

}
