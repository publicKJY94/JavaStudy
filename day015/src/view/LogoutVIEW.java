package view;

public class LogoutVIEW extends VIEW {
	
	public void printMenu() {
		System.out.println("6. 회원가입");
		System.out.println("7. 로그인");
	}
	public void printSignUpMenu() {
		System.out.println("1. 일반 회원가입");
		System.out.println("2. 관리자 회원가입");
	}
	public String inputId() {
		System.out.println("id를 입력하세요 >> ");
		String id = sc.next();
		return id;
	}
	public String inputPw() {
		System.out.println("pw를 입력하세요 >> ");
		String pw = sc.next();
		return pw;
	}
	public String inputName() {
		System.out.println("이름을 입력하세요 >> ");
		String name = sc.next();
		return name;
	}
	public String inputAdmin() {
		System.out.println("관리자pw를 입력하세요 >> ");
		String adminPw = sc.next();
		return adminPw;
	}
}
