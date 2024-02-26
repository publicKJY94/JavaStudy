package ctrl;

import java.util.ArrayList;

import model.BoardDAO;
import model.BoardDTO;
import model.MemberDAO;
import model.MemberDTO;
import view.VIEW;

public class CTRL {

	private MemberDAO mDAO;
	private BoardDAO bDAO;
	
	private VIEW view;
	private MemberDTO login;

	public CTRL() {
		mDAO = new MemberDAO();
		bDAO = new BoardDAO();
		view = new VIEW();
		login = null;
	}

	public void start() {
		while (true) {
			view.printMenu();
			int action = view.inputAction();
			if (action == 0) {
				break;
			} else if (action == 1) {
				if (login != null) {
					view.printFalse();
					continue;
				}
				MemberDTO mDTO = view.login();
				mDTO.setSearchCondition("login");
				mDTO = mDAO.selectOne(mDTO);
				if (mDTO == null) {
					view.printFalse();
					continue;
				}
				login = mDTO;
				view.printTrue();
			} else if (action == 2) {
				if (login == null) {
					continue;
				}
				login = null;
			} else if (action == 3) {
				MemberDTO mDTO = null;
				BoardDTO bDTO = null;
				if (login == null) {
					continue;
				}
				mDTO = new MemberDTO();
				bDTO = new BoardDTO();
				mDTO.setMid(login.getMid());
				mDTO.setName(login.getName());
				bDTO = view.inputBoard(mDTO);
				boolean flag = bDAO.insert(bDTO);
				if (!flag) {
					view.printFalse();
					continue;
				}
				view.printTrue();

			} else if (action == 4) {
				BoardDTO bDTO = new BoardDTO();
				bDTO.setSearchCondition("ALL");
				ArrayList<BoardDTO> datas = bDAO.selectAll(bDTO);
				view.printDatas(datas);
			} else if (action == 5) {
				action = view.inputAction();
				BoardDTO bDTO = new BoardDTO();
				bDTO.setBid(action);
				bDTO = bDAO.selectOne(bDTO);
				if (bDTO != null) {
					view.printData(bDTO);
					continue;
				}
				view.printFalse();
			} else if (action == 6) {
				BoardDTO bDTO = null;
				ArrayList<BoardDTO> datas = null;
				action = view.inputAction();
				if (action == 1) {
					bDTO = new BoardDTO();
					bDTO.setSearchCondition("WRITER");
					datas = bDAO.selectAll(bDTO);
					view.printDatas(datas);
				} else if (action == 2) {
					bDTO = new BoardDTO();
					bDTO.setSearchCondition("TITLE");
					datas = bDAO.selectAll(bDTO);
					view.printDatas(datas);
				}
			}
		}
	}

}
