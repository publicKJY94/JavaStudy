package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.ProductDTO;

public class VIEW {
	private Scanner sc;
	public VIEW() {
		sc=new Scanner(System.in);
	}
	
	public void printMenu() {
		System.out.println("0. 종료");
		System.out.println("1. 추가");
		System.out.println("2. 목록");
		System.out.println("3. 검색");
		System.out.println("4. 구매");
		System.out.println("5. 삭제");
	}
	public int inputAction() {
		System.out.print("번호입력 >> ");
		return sc.nextInt();
	}
	
	public void printDatas(ArrayList<ProductDTO> datas) {
		for(ProductDTO data:datas) {
			if(data.getCnt()<=0) {
				continue;
			}
			System.out.println(data);
		}
	}
	
	public int inputPid() {
		System.out.print("상품번호입력 >> ");
		return sc.nextInt();
	}
	public void printData(ProductDTO data) {
		if(data==null) {
			System.out.println("해당 상품은 존재하지않습니다...");
			return;
		}
		System.out.println(data);
	}
	
	public String inputName() {
		System.out.print("상품이름입력 >> ");
		return sc.next();
	}
	public int inputPrice() {
		System.out.print("상품가격입력 >> ");
		return sc.nextInt();
	}
	public int inputCnt() {
		System.out.print("상품재고입력 >> ");
		return sc.nextInt();
	}
	public void printTrue() {
		System.out.println("성공!");
	}
	public void printFalse() {
		System.out.println("실패...");
	}
}
