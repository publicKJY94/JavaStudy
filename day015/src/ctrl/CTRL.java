package ctrl;

import java.util.ArrayList;

import model.MemberDAO;
import model.MemberDTO;
import model.ProductDAO;
import model.ProductDTO;
import view.AdminVIEW;
import view.CommonVIEW;
import view.LoginVIEW;
import view.LogoutVIEW;
import view.UserVIEW;

public class CTRL {
	private MemberDAO mDAO;
	private ProductDAO pDAO;
	private AdminVIEW adminVIEW;
	private UserVIEW userVIEW;
	private CommonVIEW commonVIEW;
	private LoginVIEW loginVIEW;
	private LogoutVIEW logoutVIEW;
	private MemberDTO loginInfo;
	private ArrayList<ProductDTO> cart;

	public CTRL() {
		mDAO = new MemberDAO();
		pDAO = new ProductDAO();
		adminVIEW = new AdminVIEW();
		userVIEW = new UserVIEW();
		commonVIEW = new CommonVIEW();
		loginVIEW = new LoginVIEW();
		logoutVIEW = new LogoutVIEW();
		cart = null;
	}

	public void start() {
		while (true) {
			commonVIEW.printMenu();
			userVIEW.printMenu();
			if (loginInfo == null) {
				logoutVIEW.printMenu();
			} else {
				if (loginInfo.getGrade().equals("ADMIN")) {
					adminVIEW.printMenu();
				}
				loginVIEW.printMenu();
			}
			int action = commonVIEW.inputAction();
			if (action == 0) {
				commonVIEW.printSyso();
				break;
			} else if (action == 1) {
				ProductDTO productDTO = new ProductDTO();
				commonVIEW.printDatas(pDAO.selectAll(productDTO));
			} else if (action == 2) {
				ProductDTO productDTO = new ProductDTO();
				int num = commonVIEW.inputPrice();
				int num2 = commonVIEW.inputPrice();
				productDTO.setStartRange(num);
				productDTO.setEndRange(num2);
				productDTO.setSearchCondition("가격검색");
				commonVIEW.printDatas(pDAO.selectAll(productDTO));
			} else if (action == 3) {
				int pid = commonVIEW.inputPid();
				ProductDTO pDTO = new ProductDTO();
				pDTO.setPid(pid);
				pDTO = pDAO.selectOne(pDTO);
				if (pDTO == null) {
					commonVIEW.printNoData();
					continue;
				}

				commonVIEW.printData(pDTO);
				int cnt = commonVIEW.inputCnt();

				boolean flag = false;
				int i = 0;
				if (cart != null) {
					for (i = 0; i < cart.size(); i++) {
						if (pDTO.getPid() == cart.get(i).getPid()) {
							flag = true;
							break;
						}
					}
				}

				// 원래는 그냥 지금 구매하려는 재고가 기존 재고보다 크면 안된다.
				// 현재는 지금 구매하려는 재고가 기존 재고-장바구니 재고보다 크면 안된다.
				if (!flag) {
					if (pDTO.getCnt() < cnt) {
						commonVIEW.printFalse();
						continue;
					}
					commonVIEW.printTrue();
					if (cart == null) {
						cart = new ArrayList<ProductDTO>();
					}
					ProductDTO data = new ProductDTO();
					data.setPid(pDTO.getPid());
					data.setName(pDTO.getName());
					data.setCnt(cnt);
					data.setPrice(pDTO.getPrice());
					cart.add(data);
				} else { // 장바구니에 있던 상품이라면
					if (pDTO.getCnt() - cart.get(i).getCnt() < cnt) {
						commonVIEW.printFalse();
						continue;
					}
					commonVIEW.printTrue();
					cart.get(i).setCnt(cart.get(i).getCnt() + cnt);
				}

			} else if (action == 4) {

			} else if (action == 5) {
				if (loginInfo.getGrade().equals("ADMIN")) {
					ProductDTO productDTO = new ProductDTO();
					commonVIEW.printDatas(pDAO.selectAll(productDTO));
					productDTO.setPid(commonVIEW.inputPid());
					productDTO.setSearchCondition("재고추가");
					productDTO = pDAO.selectOne(productDTO);
					productDTO.setAddCnt(commonVIEW.inputCnt());
					productDTO.setSearchCondition("재고추가");
					if (pDAO.update(productDTO)) {
						commonVIEW.printTrue();
					} else {
						commonVIEW.printFalse();
					}
				}
			} else if (action == 6) {
				logoutVIEW.printSignUpMenu();
				MemberDTO memberDTO = new MemberDTO();
				action = commonVIEW.inputAction();
				if (action == 1) {
					while (true) {
						String id = logoutVIEW.inputId();
						memberDTO.setMid(id);
						memberDTO.setSearchCondition("회원가입");
						if (mDAO.selectOne(memberDTO) == null) {
							memberDTO.setMid(id);
							memberDTO.setMpw(logoutVIEW.inputPw());
							memberDTO.setName(logoutVIEW.inputName());
							memberDTO.setGrade("USER");
							memberDTO.setSearchCondition("회원가입");
							mDAO.insert(memberDTO);
							commonVIEW.printTrue();
							continue;
						} else {
							commonVIEW.printFalse();
						}
					}
				} else if (action == 2) {
					if (logoutVIEW.inputAdmin().equals("admin")) { // 하드코딩인데 나중에 바꿀수 있음
						String id = logoutVIEW.inputId();
						memberDTO.setMid(id);
						memberDTO.setSearchCondition("회원가입");
						if (mDAO.selectOne(memberDTO) == null) {
							memberDTO.setMid(id);
							memberDTO.setMpw(logoutVIEW.inputPw());
							memberDTO.setName(logoutVIEW.inputName());
							memberDTO.setGrade("ADMIN");
							memberDTO.setSearchCondition("회원가입");
							mDAO.insert(memberDTO);
							commonVIEW.printTrue();
						} else {
							commonVIEW.printFalse();
						}
					}
				}
			} else if (action == 7) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMid(logoutVIEW.inputId());
				memberDTO.setMpw(logoutVIEW.inputPw());
				memberDTO.setSearchCondition("로그인");
				loginInfo = mDAO.selectOne(memberDTO);
				if (loginInfo != null) {
					commonVIEW.printTrue();
				} else {
					commonVIEW.printFalse();
				}
			} else if (action == 8) {
				if (loginInfo == null) {
					continue;
				}
				loginInfo = null;
			} else if (action == 9) {
				// 비밀번호를 확인한후에 비번변경을 진행
				String mpw = logoutVIEW.inputPw();
				MemberDTO mDTO = new MemberDTO();
				mDTO.setMid(loginInfo.getMid());
				mDTO.setMpw(mpw);
				mDTO.setSearchCondition("로그인");
				mDTO = mDAO.selectOne(mDTO);
				if (mDTO == null) {
					commonVIEW.printFalse();
					continue;
				}

				// 변경할 비번입력
				mpw = logoutVIEW.inputPw();
				mDTO.setMid(loginInfo.getMid());
				mDTO.setMpw(mpw);
				if (!mDAO.update(mDTO)) {
					commonVIEW.printFalse();
					continue;
				}
				loginInfo = null;
				commonVIEW.printTrue();
			} else if (action == 10) {
				if (loginInfo != null) {
					mDAO.delete(loginInfo);
					loginInfo = null;
				}
			}
		}
	}
}
