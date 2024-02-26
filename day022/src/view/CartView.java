package view;

import java.util.ArrayList;

import model.DTO.CartDTO;

public class CartView extends VIEW {
	public void printDatas(ArrayList<CartDTO> cdatas) {
		System.out.println("=======장바구니=======");
		for (int i = 0; i < cdatas.size(); i++) {
				System.out.println("상품명 : "+ cdatas.get(i).getpName());
				System.out.println("상품가격 : "+ cdatas.get(i).getpPrice());
				System.out.println("담은개수 : "+ cdatas.get(i).getcCnt());
				System.out.println();
		}
	}

	public int inputcAdd() {
		System.out.println("장바구니에 담을 수량 입력 >> ");
		return sc.nextInt();
	}
	
	public void printNoCart () {
		System.out.println("장바구니안에 상품이 없습니다.");
	}
	public void printCleanCart() {
		System.out.println("장바구니가 깨끗해졌습니다!");
	}
}
