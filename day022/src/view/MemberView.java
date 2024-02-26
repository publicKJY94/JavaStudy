// 멤버view
package view;

import model.DTO.MemberDTO;

public class MemberView extends VIEW {
	// 아이디 입력
	public String inputID() {
		System.out.print("아이디 입력 >> ");
		String id = sc.next();
		return id;
	}
	// 비밀번호 입력
	public String inputPW() {
		System.out.print("비밀번호 입력 >> ");
		String pw = sc.next();
		return pw;
	}
	// 이름 입력
	public String inputName() {
		System.out.print("이름 입력 >> ");
		String name = sc.next();
		return name;
	}
	// 나이 입력
	public int inputAge() {
		System.out.print("나이 입력 >> ");
		int age = sc.nextInt();
		return age;
	}
	// 아이디 중복검사 후 중복되는 아이디일 때 출력할 메시지
	public void printDuplicateID() {
		System.out.println("이미 존재하는 아이디입니다");
		System.out.println("다른아이디를 입력해주세요");
	}
	// 회원가입 성공 메시지
	public void printAccountSuccess(MemberDTO mDTO) {
		System.out.println("회원가입 성공!");
		System.out.println(mDTO.getmName() + "님 회원가입이 완료되었습니다!");
	}

	// 회원가입 실패 메시지
	public void printAccountFail() {
		System.out.println("회원가입 실패!");
	}

	// 회원탈퇴 2차 확인
	public boolean printChangeUserStatus() {
		System.out.println("정말로 탈퇴하시겠습니까? Y / N");
		System.out.println("입력 >> ");
		String result = sc.next().toUpperCase(); // 입력받은 값을 대문자로 변환하여 y, Y, n, N 을 대소구분없이 동작할 수 있게 하였다
		if (result.equals("Y")) {
			return true;
		}
		return false;
	}
	// 로그아웃 성공 메시지
	public void printLogoutSuccess() {
		System.out.println("로그아웃 성공!");
	}
	// 비활성계정 로그인시 안내메시지
	public void printLoginGhost() {
        System.out.println("비활성 계정입니다 관리자에게 문의해주세요");
    }
}