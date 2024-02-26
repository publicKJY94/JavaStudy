package view;

import java.util.ArrayList;

import model.DTO.ProductDTO;

public class ProductView extends VIEW {
	// 상품번호 입력
	public int inputpId() {
		System.out.print("상품번호 입력 >> ");
		int pid = sc.nextInt();
		return pid;
	}

	// 상품이름 입력
	public String inputpName() {
		System.out.print("상품이름 입력 >> ");
		String pName = sc.next();
		return pName;
	}

	// 카테고리 입력
	public String inputpCategory() {
		System.out.print("카테고리 입력 >> ");
		String pCategory = sc.next();
		return pCategory;
	}

	// 상품가격 입력
	public int inputpPrice() {
		System.out.print("상품가격 입력 >> ");
		int pPrice = sc.nextInt();
		return pPrice;
	}

	// 상품재고 입력
	public int inputpCnt() {
		System.out.print("수량 입력 >> ");
		int pCnt = sc.nextInt();
		return pCnt;
	}

	// 상품설명 입력
	public String inputpInfo() {
		System.out.print("상품설명 입력 >> ");
		String pInfo = sc.next();
		return pInfo;
	}
	// 구매할 상품 수량 입력
	public int inputCnt() {
		System.out.println("구매할 수량 입력 >> ");
		return sc.nextInt();
	}

	// 상품설명 입력
	public String printNotEnoughCnt() {
		String pInfo = sc.next();
		return pInfo;
	}

	public boolean printbuySuccess() {
		System.out.println("구매해주셔서 감사합니다");
		return true; // 구매에 성공했을 때에만 실행되는 출력문이므로 boolean타입으로 설정
					// 추후(웹으로 넘어갔을 때) 구매 과정에서 막히는 경우 (로그인 세션만료 등)
	}
	public boolean printbuyFail() {
		System.out.println("구매에 실패하였습니다");
		System.out.println("지속적으로 구매에 실패하는 경우 관리자에게 문의해주세요");
		return false; // 구매에 실패했을 때에만 실행되는 출력문이므로 boolean타입으로 설정
		// 추후(웹으로 넘어갔을 때) 구매 과정에서 막히는 경우 (로그인 세션만료 등)
	}

	public void printData(ProductDTO pdata) {
		// 화면에 재고가 표시되었을 때, 다른사람에 의해 구매가 이루어 진다면
		// 재고에 차이가 발생할 수 있는 경우를 고려하여 재고 부족시 배송을 지연시키는 방향으로 진행
		if (pdata.getpCnt() <= 0) { // 해당부분이 현재 하드코딩 상태이므로, 추후 웹에서는 해당 상품의 총재고에서 몇퍼센트 이하로 떨어지는 경우
									// 출력용 메시지로 사용할 예정
			System.out.println("현재 주문량이 많아 배송이 지연될 수 있음을 알려드립니다");
		} else {
			System.out.println("상품명 : "+ pdata.getpName());
			System.out.println("상품카테고리 : "+ pdata.getpCategory());
			System.out.println("상품가격 : "+ pdata.getpPrice());
			System.out.println("상품정보 : "+ pdata.getpInfo());
		}
	}

	public void printDatas(ArrayList<ProductDTO> pdatas) {
		System.out.println("=======목록=======");
		for (ProductDTO pdata : pdatas) {
			if (pdata.getpCnt() <= 0) {
				System.out.println("현재 주문량이 많아 배송이 지연될 수 있음을 알려드립니다");
			} else {
				System.out.println("상품번호 : "+ pdata.getpId());
				System.out.println("상품명 : " + pdata.getpName());
				System.out.println("상품카테고리 : " + pdata.getpCategory());
				System.out.println("상품가격 : "+ pdata.getpPrice());
				System.out.println("상품정보 : "+ pdata.getpInfo());
				System.out.println("");
			}
		}
	}
}