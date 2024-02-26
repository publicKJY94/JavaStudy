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
	private MemberDTO loginINFO;

	public CTRL() {
		mDAO = new MemberDAO();
		bDAO = new BoardDAO();
		view = new VIEW();
		loginINFO = null;
	}

	public void start() {
		while (true) {
			view.loginlogout(loginINFO);
			view.printMenu();
			int action = view.inputAction();
			if (action == 0) {
				break;
			} else if (action == 1) {
				if (loginINFO != null) {
					view.printFalse();
					continue;
				}
				MemberDTO mDTO = view.login();
				mDTO = mDAO.selectOne(mDTO);
				if (mDTO == null) {
					view.printFalse();
					continue;
				}
				loginINFO = mDTO;
				view.printTrue();
			} else if (action == 2) {
				if (loginINFO == null) {
					view.printFalse();
					continue;
				}
				loginINFO = null;
				view.printTrue();
			} else if (action == 3) {
				if (loginINFO == null) {
					view.printFalse();
					continue;
				}
				BoardDTO bDTO = view.boardInsert();
				bDTO.setWriter(loginINFO.getMid());
				if (!bDAO.insert(bDTO)) {
					view.printFalse();
					continue;
				}
				view.printTrue();
			} else if (action == 4) {
				ArrayList<BoardDTO> datas = bDAO.selectAll(null);
				view.printDatas(datas);
			} else if (action == 5) {
				int bid = view.inputBid();
				BoardDTO bDTO = new BoardDTO();
				bDTO.setBid(bid);
				bDTO = bDAO.selectOne(bDTO);
				view.printData(bDTO);
				action = view.loginMenu(bDTO, loginINFO);
				if (action == 1) {
					bDTO = view.boardInsert();
					bDTO.setBid(bid);
					// bDTO.setWriter(loginINFO.getMid());
					// 코드가 없어도 되는 이유
					// 1. 이미 bDTO에 writer 정보가 있음
					// 2. update()에서 writer 정보를 필요로하지않음
					if (!bDAO.update(bDTO)) {
						view.printFalse();
						continue;
					}
					view.printTrue();
				} else if (action == 2) {
					if (!bDAO.delete(bDTO)) {
						view.printFalse();
						continue;
					}
					view.printTrue();
				}
			}else if (action == 6) {
				BoardDTO bDTO = null;
				ArrayList<BoardDTO> datas = null;
				// 로그인을 하지 않았을 때도 실행은 되어야 한다고 생각
//				view에서 inputSearchAction메서드로 선택을 받아
				action = view.inputSearchAction();
				if(action == 1 ) { // 전체검색
					// 전체검색이기 때문에 bDTO에 검색조건만 담아서 넘겨준다
					bDTO.setSearchCondition("ALL");
					// DAO에서 검색 진행
					// 검색결과를 datas에 담아준다
					datas = bDAO.selectAll(bDTO);
					// view의 printDatas를 활용하여 출력한다
					if(datas != null) {
						view.printFalse();
						continue;
					}
					view.printDatas(datas);
				}else if(action ==2) { // 작가검색
					// 검색하려는 정보와 검색조건을 담아서 넘겨준다
					bDTO.setSearchCondition("WRITER");
					bDTO.setSearchKeyword(view.inputWriter());
					// DAO에서 검색 진행
					// 검색결과를 datas에 담아준다
					datas = bDAO.selectAll(bDTO);
					// view의 printDatas를 활용하여 출력한다
					if(datas != null) {
						view.printFalse();
						continue;
					}
					view.printDatas(datas);
				}else if(action ==3) { // 제목검색
					// 검색하려는 정보와 검색조건을 담아서 넘겨준다
					bDTO.setSearchCondition("TITLE");
					bDTO.setSearchKeyword(view.inputTitle());
					// DAO에서 검색 진행
					// 검색결과를 datas에 담아준다
					datas = bDAO.selectAll(bDTO);
					// view의 printDatas를 활용하여 출력한다
					if(datas != null) {
						view.printFalse();
						continue;
					}
					view.printDatas(datas);
				}
			}
		}
	}

}
