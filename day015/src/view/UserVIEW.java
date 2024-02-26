package view;

import model.MemberDTO;

public class UserVIEW extends VIEW {
	
	public void printMenu() {
		System.out.println("2. 가격검색출력");
		System.out.println("3. 상품선택");
		System.out.println("4. 장바구니에 저장된 상품 구매");
	}
	public void printData(MemberDTO data) {
		if (data.getMid()==null) {
			System.out.println("출력할 데이터가 없습니다!");
		}
		System.out.println(data);
	}
}
