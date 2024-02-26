package day014.controller;

import java.util.ArrayList;

import day014.view.View;
import day014.model.MemberDAO;
import day014.model.MemberDTO;
import day014.model.ProductDAO;
import day014.model.ProductDTO;
import day014.view.MemberView;

public class Ctrl {

	private ProductDAO productDAO; // MODEL
	private MemberDAO memberDAO;
	private View view;
	private MemberView memberView;
	private int PK;
	private MemberDTO user; // 현재 로그인한 사람의 정보
	// 로그인 세션,쿠키,로컬 스토리지 정보

	public Ctrl() {
		this.productDAO = new ProductDAO();
		this.memberDAO = new MemberDAO();
		this.view = new View();
		this.memberView = new MemberView();
		this.PK = 1003;
		this.user = null; // 아무도 로그인하지않은 상태
	}

	public void start() {
		while (true) {
			if (user == null) { // 로그아웃 상태
				view.printMenu();
			} else {
				view.printLoginMenu();
			}
			int action = view.inputInteger();
			if (action == 0) {
				break;
			} else if (action == 1234) {
				if (user == null) {
					continue;
				}
				while (true) {
					view.printAdminMenu();
					action = view.inputInteger();
					if (action == 0) {
						break;
					} else if (action == 1) {
						String name = view.inputName();
						int price = view.inputPrice();
						int cnt = view.inputCnt();

						ProductDTO productDTO = new ProductDTO();
						productDTO.setNum(PK++);
						productDTO.setName(name);
						productDTO.setPrice(price);
						productDTO.setCnt(cnt);

						productDAO.insert(productDTO);
					} else if (action == 2) {
						int num = view.inputNum();

						ProductDTO productDTO = new ProductDTO();
						productDTO.setNum(num);
						productDTO = productDAO.selectOne(productDTO);
						if (productDTO == null) {
							view.printNoData();
							continue;
						}

						int cnt = view.inputCnt();
						productDTO.setCnt(cnt);
						productDTO.setSearchConditon("재고변경");
						boolean flag = productDAO.update(productDTO);
						if (flag) {
							view.printDeleteData01(); // update 성공 !
						} else {
							view.printDeleteData02(); // update 실패 ...
						}
					} else if (action == 3) {
						int num = view.inputNum();

						ProductDTO productDTO = new ProductDTO();
						productDTO.setNum(num);

						productDTO = productDAO.selectOne(productDTO);
						if (productDTO == null) {
							view.printNoData();
							continue;
						}

						boolean flag = productDAO.delete(productDTO);
						if (flag) {
							view.printDeleteData01();
						} else {
							view.printDeleteData02();
						}
					} else if (action == 4) {
						int num = view.inputNum();

						ProductDTO productDTO = new ProductDTO();
						productDTO.setNum(num);
						productDTO = productDAO.selectOne(productDTO);
						if (productDTO == null) {
							view.printNoData();
							continue;
						}

						int addCnt = view.inputCnt();
						productDTO.setAddCnt(addCnt);
						productDTO.setSearchConditon("재고추가");
						boolean flag = productDAO.update(productDTO);
						if (flag) {
							view.printDeleteData01();
						} else {
							view.printDeleteData02();
						}
					} else if (action == 5) {
						int num = view.inputNum();

						ProductDTO productDTO = new ProductDTO();
						productDTO.setNum(num);
						productDTO = productDAO.selectOne(productDTO);
						if (productDTO == null) {
							view.printNoData();
							continue;
						}

						int price = view.inputPrice();
						productDTO.setPrice(price);
						productDTO.setSearchConditon("가격변경");
						productDAO.update(productDTO);
					}
				}
			} else if (action == 1) {
				ProductDTO productDTO = new ProductDTO();
				ArrayList<ProductDTO> datas = productDAO.selectAll(productDTO);
				view.printDatas(datas);
			} else if (action == 2) {
				int num = view.inputInteger();
				ProductDTO productDTO = new ProductDTO();
				productDTO.setNum(num);
				ProductDTO data = productDAO.selectOne(productDTO);
				view.printData(data);
				if (data != null && data.getCnt() > 0) { // 구매성공이라면
					data.setSearchConditon("구매");
					productDAO.update(data);
				}
			} else if (action == 3) {
				String name = view.inputName();

				ProductDTO productDTO = new ProductDTO();
				productDTO.setName(name);
				productDTO.setSearchConditon("이름검색");
				ArrayList<ProductDTO> datas = productDAO.selectAll(productDTO);

				view.printDatas(datas);
			} else if (action == 4) {
				if (user != null) {
					continue;
				}
				// 로그아웃 상태일때만 수행할 예정
				// 로그인 상태야? 그럼 못해. 되돌아가!
				// 유효성 검사 ☆

				MemberDTO memberDTO = new MemberDTO();
				String mid;
				while (true) {
					mid = memberView.inputMemberID();

					memberDTO.setMid(mid);
					memberDTO.setSearchCondition("ID중복검사");
					memberDTO = memberDAO.selectOne(memberDTO);
					if (memberDTO == null) {
						break;
					}

					memberView.signUpFalse();
				}
				memberDTO = new MemberDTO();
				memberDTO.setMid(mid);
				String mpw = memberView.inputMemberPW();
				memberDTO.setMpw(mpw);
				String name = memberView.inputMemberName();
				memberDTO.setName(name);
				memberDAO.insert(memberDTO);

				memberView.signUpTrue();
			} else if (action == 5) {
				if (user != null) {
					continue;
				}
				// 로그아웃 상태일때만 수행할 예정
				// 로그인 상태야? 그럼 못해. 되돌아가!
				// 유효성 검사 ☆

				String mid = memberView.inputMemberID();
				String mpw = memberView.inputMemberPW();
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMid(mid);
				memberDTO.setMpw(mpw);
				memberDTO.setSearchCondition("로그인");
				memberDTO = memberDAO.selectOne(memberDTO);
				if (memberDTO == null) {
					memberView.LoginFalse();
					continue;
				}
				user = memberDTO;
				memberView.LoginTrue();
			} else if (action == 6) {
				user = null;
				memberView.logout();
			} else if (action == 7) {
				if (user == null) {
					continue;
				}
				// 비밀번호를 입력받아서 현재 유저의 비밀번호와 동일한 경우
				// 유효성검사 재실시?
				MemberDTO memberDTO = new MemberDTO();
				String checkPw = memberView.inputMemberPW();
				memberDTO.setMid(user.getMid());
				memberDTO.setMpw(checkPw);
				memberDTO.setSearchCondition("로그인");
				memberDTO = memberDAO.selectOne(memberDTO);
				if(memberDTO==null) {
					continue;
				}
				// 조건을 비밀번호변경
				String newPw = memberView.inputMemberPW();
				memberDTO = new MemberDTO();
				memberDTO.setMid(user.getMid());
				memberDTO.setNewPw(newPw);
				checkPw = memberView.inputMemberPW();
				if(memberDTO.getNewPw().equals(checkPw)) {
					memberDTO.setSearchCondition("비밀번호변경");
					memberDAO.update(memberDTO);
				}
			} else if (action == 8) {
				if (user == null) {
					continue;
				}
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMid(user.getMid());
				memberDAO.delete(memberDTO);
				user = null;
				memberView.printTrue();
			}
		}
	}

}
