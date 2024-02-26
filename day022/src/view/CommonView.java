package view;

import java.util.ArrayList;

import model.DTO.MemberDTO;

//import model.DTO.MemberDTO;

public class CommonView extends VIEW {
	// 로그아웃 상태일 때 보여지는 메뉴
	public void printLogoutMenu() {
		System.out.println("5. 로그인");
		System.out.println("6. 회원가입");
		System.out.println("0. 종료");
	}
	// 로그인 없이 기능을 이용하려 하는경우 출력 메시지
	public void printLoginAlert() {
		System.out.println("로그인을 먼저 해주세요");
	}
	// 로그인 성공 메시지
	public void printLoginSuccess(MemberDTO mDTO) {
		System.out.println("로그인 성공!");
		System.out.println(mDTO.getmName() + "님 환영합니다");
	}
	// 로그인 실패 메시지
	public void printLoginFail() {
		System.out.println("로그인 실패!");
		System.out.println("아이디나 비밀번호를 확인해주세요");
	}
	// 사용자 로그인 후 추가메뉴
	public void printLoginMenu() {
		System.out.println("7. 개인정보");
		System.out.println("8. 구매목록");
		System.out.println("9. 회원탈퇴");
		System.out.println("10. 로그아웃");
		System.out.println("0. 종료");
	}
	// 사용자 로그인 전 기본 메뉴
	public void printMenu() {
		System.out.println("1. 목록출력");
		System.out.println("2. 검색");
		System.out.println("3. 구매");
		System.out.println("4. 장바구니");
	}

	// 멤버 전체 목록 출력
	public void printDatas(ArrayList<MemberDTO> mdatas) {
		for (MemberDTO data : mdatas) {
			System.out.println(data);
		}
	}

	// 멤버 1명 출력 >> 멤버 1명의 정보 출력, 반환값 X
	public void printData(MemberDTO mDTO) {
		System.out.println(mDTO.getmName());
	}

	// action 입력 >> action값 반환
	public int inputAction() {
		System.out.print("입력 >> ");
		int action = sc.nextInt();
		return action;
	}
	// 관리자 메뉴 로그인 후 추가 메뉴
	public void adminMenu() {
		System.out.println("11. 상품추가");
		System.out.println("12. 재고관리");
		System.out.println("13. 제품삭제");
		System.out.println("10. 로그아웃");
	}

	// 구매방식선택
	public int inputpCart() {
		System.out.println("1. 장바구니 담기");
		System.out.println("2. 구매하기");
		System.out.println("0. 메인메뉴");
		System.out.print("입력 >> ");
		int pCart = sc.nextInt();
		return pCart;
	}
	//미구현 기능 진입하는 경우
	public void nextUpdate() {
		System.out.println("서비스 준비중...");
	}
	public void cartMenu() {
		System.out.println("1. 장바구니 상품 전체 구매하기");
		System.out.println("2. 수량변경하기");
		System.out.println("3. 장바구니 비우기");
		System.out.println("0. 메인메뉴로");
	}

}