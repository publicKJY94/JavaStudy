package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.BoardDTO;
import model.MemberDTO;

public class VIEW {
	private Scanner sc;

	public VIEW() {
		sc = new Scanner(System.in);
	}

	public void printMenu() {
		System.out.println("0. 종료");
		System.out.println("1. 로그인");
		System.out.println("2. 로그아웃");
		System.out.println("3. 글 작성");
		System.out.println("4. 글 목록 보기");
		System.out.println("5. 글 내용 보기");
	}

	public int inputAction() {
		System.out.print("번호입력 >> ");
		return sc.nextInt();
	}

	public void printTrue() {
		System.out.println("성공!");
	}

	public void printFalse() {
		System.out.println("실패...");
	}

	public MemberDTO login() {
		MemberDTO mDTO = new MemberDTO();
		System.out.print("아이디 입력 >> ");
		String mid = sc.next();
		System.out.print("비밀번호 입력 >> ");
		String mpw = sc.next();
		mDTO.setMid(mid);
		mDTO.setMpw(mpw);
		return mDTO;
	}

	public void loginlogout(MemberDTO mDTO) {
		if (mDTO != null) {
			System.out.println(mDTO.getName() + "님 안녕하세요 :D");
		} else {
			System.out.println("로그인 후 이용해주세요");
		}
	}

	public BoardDTO inputBoard(MemberDTO mDTO) {
		BoardDTO bDTO = null;
		if (mDTO != null) {
			bDTO = new BoardDTO();
		}
		// 사용자의 입력이 끝날 때 까지;
		System.out.println("제목을 입력하세요");
		String content = null;
		String title = sc.nextLine();
		bDTO.setTitle(title);
		bDTO.setWriter(mDTO.getName());
		// 작성자가 작성을 완료할 때 까지 입력받는다
		if(title!=null) {
			System.out.println("내용작성");
		}
		while (!sc.hasNext("YES")) {
			// 종료조건은 입력이 없거나 마지막으로 입력받은 값으로 판단한다
			String text = sc.next();
			content+=text;
		}
		bDTO.setContent(content);
		return bDTO;
	}

	public void printData(BoardDTO data) {
		if (data != null) {
			System.out.println("[" + data.getBid() + "번글 " + data.getTitle() + "작성자 : " + data.getWriter() + "]");
			System.out.println(data.getContent());
		}
	}

	public void printDatas(ArrayList<BoardDTO> datas) {
		if (datas != null) {
			for (BoardDTO data : datas) {
				System.out.println("[" + data.getBid() + "번글 " + data.getTitle() + "작성자 : " + data.getWriter() + "]");
			}
		}
	}

}
