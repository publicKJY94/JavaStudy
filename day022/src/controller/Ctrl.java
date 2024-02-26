package controller;
//ver1.5

// 회원비활성화 기능추가

// 구매기능추가
// 장바구니추가
// 주문목록 열람추가
// 미구현 기능 안내문구 추가
// 기능별 주석 작성완료

import java.util.ArrayList;

import model.WebCrawling;
import model.DAO.CartDAO;
import model.DAO.MemberDAO;
import model.DAO.OrderContentDAO;
import model.DAO.OrderDAO;
import model.DAO.ProductDAO;
import model.DTO.CartDTO;
import model.DTO.MemberDTO;
import model.DTO.OrderContentDTO;
import model.DTO.OrderDTO;
import model.DTO.ProductDTO;
import view.CartView;
import view.CommonView;
import view.MemberView;
import view.OrderView;
import view.ProductView;

public class Ctrl {
	private MemberDAO mDAO;
	private CommonView view;
	private MemberView mview;
	private ProductView pview;
	private ProductDAO pDAO;
	private MemberDTO loginINFO;
	private CartDAO cDAO;
	private CartView cview;
	private OrderDAO oDAO;
	private OrderView oview;
	private OrderContentDAO oContentDAO;

	public Ctrl() {
		mDAO = new MemberDAO();
		pDAO = new ProductDAO();
		view = new CommonView();
		mview = new MemberView();
		pview = new ProductView();
		cview = new CartView();
		oview = new OrderView();
		cDAO = new CartDAO();
		oDAO = new OrderDAO();
		oContentDAO = new OrderContentDAO();
		loginINFO = null;

	}

	// 로그인 메서드
	public void loginAction() {
		// 아이디와 패스워드를 입력한다
		String id = mview.inputID();
		String pw = mview.inputPW();
		// DB에서 조회하기 위해 새로운 멤버객체에 아이디와 패스워드 검색조건을 담아준다
		MemberDTO mDTO = new MemberDTO();
		mDTO.setmId(id);
		mDTO.setmPw(pw);
		mDTO.setSearchCondition("로그인"); // DAO에서 아이디와 패스워드를 검색하기 위한 조건설정
		// DAO를 통해 DB에서 조회한다
		loginINFO = mDAO.selectOne(mDTO);
		// DB에서 아이디 또는 비밀번호가 일치하지 않을 경우
		if (loginINFO == null) {
			view.printLoginFail(); // 로그인 실패메시지 출력
			return;
//		}else if(loginINFO.getmGrade().equals("GHOST")) {
//			mview.printLoginGhost();
		} else if (loginINFO.getmGrade().equals("GHOST")) {
			mview.printLoginGhost();
			return;
		}
		view.printLoginSuccess(loginINFO); // 로그인 성공메시지 출력
	}

	public void start() {
//		웹 크롤링으로 샘플데이터를 가져오기 위한 코드
		ArrayList<ProductDTO> samples = WebCrawling.webCrawling();
		for (ProductDTO data : samples) {
			data.setpCnt(5);
			data.setpCategory("헤드셋");
			System.out.println(data);
			pDAO.insert(data);

		}

		while (true) { // 프로그램 실행

			if (loginINFO == null || loginINFO.getmGrade().equals("GHOST")) { // 로그인 정보가 없다면 (로그아웃 상태라면)
				view.printMenu();
				view.printLogoutMenu(); // 로그아웃 상태 메뉴 출력

			} else if (loginINFO.getmGrade().equals("ADMIN")) { // 로그인한 계정이 관리자라면
				view.adminMenu(); // 관리자 전용메뉴 출력 (추후 기능추가)
			} else if (loginINFO.getmGrade().equals("USER")) { // 로그인한 계정이 유저라면
				view.printMenu();
				view.printLoginMenu();
			}

			int action = view.inputAction(); // 메뉴 번호선택

			if (action == 0) { // 프로그램 종료
				break;
			} else if (action == 1) { // 목록출력
				ProductDTO pDTO = new ProductDTO();
				pDTO.setSearchCondition("전체목록");
				ArrayList<ProductDTO> datas = pDAO.selectAll(pDTO); // 전체목록을 담을 배열리스트 생성
				pview.printDatas(datas);

			} else if (action == 2) { // 이름으로 검색 후 장바구니담기 혹은 구매로 전환
				String name = pview.inputpName(); // 상품 이름 입력
				ProductDTO pDTO = new ProductDTO();
				pDTO.setpName(name); // pDTO 안에 사용자가 입력한 이름을 저장
				pDTO.setSearchCondition("이름검색");
				ArrayList<ProductDTO> datas = pDAO.selectAll(pDTO); // 전체목록에서 사용자가 검색에 입력한 이름이 포함된 상품을 검색
				pview.printDatas(datas); // 해당상품을 데이터로 출력

				int pid = pview.inputpId(); // 114번라인에서 출력된 데이터를 보여주고 상품 번호 입력받기

				pDTO.setpId(pid); // 입력받은 번호 저장
				pDTO = pDAO.selectOne(pDTO);
				pview.printData(pDTO); // 해당상품 상세 설명표기

				action = view.inputpCart(); // 장바구니에 담을지 , 구매로 넘어갈지 메뉴 출력
				if (action == 1) { // 장바구니에 담는 경우
					if (pDTO != null && loginINFO != null) { // 상품정보가 존재하고, 로그인 상태라면
						CartDTO cartDTO = new CartDTO(); // 장바구니 생성
						cartDTO.setmId(loginINFO.getmId()); // 생성된 장바구니에 로그인된 사용자 아이디 저장
						cartDTO.setpId(pDTO.getpId()); // 입력받은 상품번호 저장

						int cCnt = cview.inputcAdd(); // 장바구니에 담을 수량 입력
						cartDTO.setcCnt(cCnt); // 입력받은 수량 저장
						if (cCnt > pDTO.getpCnt()) { // 입력받은 수량이 기존 상품 수량보다 많다면 재고부족 출력후 상위메뉴로 돌아감
							System.out.println("재고부족");
							continue;
						}
						cDAO.insert(cartDTO); // 해당 장바구니에 로그인정보, 상품정보 , 입력받은 수량 저장

					} else { // 로그인 정보가 없다면 로그인 후 이용하라는 메시지 출력
						view.printLoginAlert();
					}
				} else if (action == 2) { // 검색한 상품을 바로 구매하는 경우
					if (loginINFO == null) {
						view.printLoginAlert();
					} else if (pDTO != null) { // 142번 라인에서 재고 검사를 하지 않은 이유
						// 재고가 부족하더라도(숫자가 음수로 내려가도) "배송지연"
						// 같은 안내 문구를 추가해서 재고 확보 후 판매하도록 진행할 예정
						int cnt = pview.inputCnt(); // 구매할 수량 입력
						pDTO.setpCnt(cnt);
						pDTO.setSearchCondition("구매");
						pDAO.update(pDTO); // 상품 수량 업데이트
						OrderDTO oDTO = new OrderDTO(); // 주문번호 데이터 생성
						OrderContentDTO oContentDTO = new OrderContentDTO();
						int num1 = oDAO.selectOne(oDTO).getoId() + 1;
						oDTO.setmId(loginINFO.getmId());
						oDTO.setoId(num1);
						oDAO.insert(oDTO); // 주문번호 기능에서 번호추가
						oContentDTO.setpId(pDTO.getpId()); // 주문번호 데이터안에 구매한 상품 PID 저장
						oContentDTO.setoId(num1);
						oContentDTO.setoContentCnt(cnt);
						oContentDAO.insert(oContentDTO);
//						oDTO.setmId(loginINFO.getmId()); // 로그인 정보에 포함된 MID 저장
						boolean flag = pview.printbuySuccess(); // 구매성공 출력
						// 구매가 완료되었을 때에만 실행되는 출력문이므로 boolean타입으로 설정
						// 추후(웹으로 넘어갔을 때) 구매 과정에서 막히는 경우 (로그인 세션만료 등)
					}
				}
			} else if (action == 3) { // 상품구매
				if (loginINFO == null) { // 로그아웃 상태라면
					view.printLoginAlert(); // 로그인 정보가 없다면 로그인 후 이용하라는 메시지 출력
					continue;
				}
				ProductDTO pDTO = new ProductDTO(); // 상품 데이터 생성
				OrderDTO oDTO = new OrderDTO(); // 주문번호 데이터 생성
				OrderContentDTO oContentDTO = new OrderContentDTO(); // 상세주문 데이터 공간 생성
				pDTO.setSearchCondition("전체목록");
				ArrayList<ProductDTO> datas = pDAO.selectAll(pDTO); // 구매전에 상품 전체목록 출력
				pview.printDatas(datas);

				int pid = pview.inputpId(); // 상품번호 입력
				pDTO.setpId(pid); // 입력받은 상품번호 저장
				pDTO = pDAO.selectOne(pDTO); // 해당상품과 일치하는지 조회
				pview.printData(pDTO); // 상품 데이터 출력
				int num1 = oDAO.selectOne(oDTO).getoId() + 1;
				boolean flag = false;
				if (pDTO != null) { // 재고가 부족하더라도(숫자가 음수로 내려가도) "배송지연" 같은 안내 문구를 추가해서 재고 확보 후 판매하도록 진행할 예정
					int cnt = pview.inputpCnt();
					pDTO.setpCnt(cnt); // 상품 재고 저장
					pDTO.setSearchCondition("구매");
					pDAO.update(pDTO); // 구매한 수량만큼 업데이트로 재고변경
					oDTO.setmId(loginINFO.getmId());
					oDTO.setoId(num1);
					oDAO.insert(oDTO); // 주문번호 기능에서 번호추가
					oContentDTO.setoId(oDTO.getoId()); // 주문번호 데이터안에 구매한 상품 PID 저장
					oContentDTO.setpId(pDTO.getpId());
					oContentDTO.setoContentCnt(cnt);
					oContentDAO.insert(oContentDTO);
//					oDTO.setmId(loginINFO.getmId()); // 로그인 정보에 포함된 MID 저장
					flag = pview.printbuySuccess(); // 구매성공 출력
					// 구매가 완료되었을 때에만 실행되는 출력문이므로 boolean타입으로 설정
					// 추후(웹으로 넘어갔을 때) 구매 과정에서 막히는 경우 (로그인 세션만료 등)
					if (flag) {
						continue;
					}
				}

			} else if (action == 4) {// 장바구니
				if (loginINFO == null) {
					view.printLoginAlert(); // 로그인 정보가 없다면 로그인 후 이용하라는 메시지 출력
				}

				else {
					CartDTO cartDTO = new CartDTO(); // 장바구니 데이터 생성
					ProductDTO pDTO = new ProductDTO(); // 상품 데이터 생성
					OrderDTO oDTO = new OrderDTO(); // 주문번호 데이터 생성
					ArrayList<CartDTO> datas = new ArrayList<CartDTO>(); // 장바구니 배열리스트 생성
					cartDTO.setmId(loginINFO.getmId()); // 장바구니 데이터에 로그인정보에 들어간 MID를 가져오고

					datas = cDAO.selectAll(cartDTO); // 데이터에 장바구니 정보 저장

					if (datas.size() > 0) { // 데이터가 존재한다면
						cview.printDatas(datas); // 저장된 데이터를 호출

						view.cartMenu();
						action = view.inputAction();

						if (action == 1) {
							int num1 = oDAO.selectOne(oDTO).getoId() + 1;
							for (CartDTO cart : datas) {
								pDTO.setpId(cart.getpId());
								pDTO.setpCnt(cart.getcCnt());
								pDTO.setSearchCondition("구매");
								pDAO.update(pDTO);
								OrderContentDTO oContentDTO = new OrderContentDTO();
								oDTO.setmId(loginINFO.getmId());
								oDTO.setoId(num1);
								oDAO.insert(oDTO); // 주문번호 기능에서 번호추가
								oContentDTO.setpId(pDTO.getpId()); // 주문번호 데이터안에 구매한 상품 PID 저장
								oContentDTO.setoId(oDTO.getoId());
								oContentDTO.setoContentCnt(pDTO.getpCnt());
								oContentDAO.insert(oContentDTO);
								cDAO.delete(cartDTO);
							}
							pview.printbuySuccess();
						} else if (action == 2) { // 장바구니 수량변경 (추후 구현예정)
							view.nextUpdate();
						} else if (action == 3) { // 비우기
							cDAO.delete(cartDTO);
							cview.printCleanCart();
						}
					} else {
						cview.printNoCart(); // 데이터가 존재하지 않는다면, 상품이 없습니다 메시지 출력
					}

				}

			} else if (action == 5) { // 로그인
				loginAction();

			} else if (action == 6) { // 회원가입
				String mid;
				MemberDTO mDTO = new MemberDTO(); // 회원의 정보를 저장할 데이터 공간 생성
				while (true) {
					mid = mview.inputID(); // 사용자에게 아이디 입력받기

					mDTO.setmId(mid); // 사용자 아이디 저장
					mDTO.setSearchCondition("ID중복검사"); // 중복검사
					if (mDAO.selectOne(mDTO) == null) { // 중복된 아이디가 없다면, 반복문 탈출
						break;
					}
					mview.printDuplicateID(); // 중복되는 아이디가 존재하는 경우 다른 아이디 입력하라고 유도

				}
				String pw = mview.inputPW(); // pw입력
				String name = mview.inputName(); // 사용자 이름 입력
				int age = mview.inputAge(); // 나이 입력 추후에 웹으로 넘어갈 때 성별선택 항목 추가하여 젠더 정보까지 저장
				mDTO.setmId(mid);
				mDTO.setmPw(pw);
				mDTO.setmName(name);
				mDTO.setmAge(age);
				mDTO.setmGrade("USER"); // 현재는 관리자 회원가입 기능을 따로 만들지 않고 샘플데이터를 이용하기 때문에
										// 회원의 등급을 유저로 받아줌
				boolean flag = mDAO.insert(mDTO);
				if (flag) {
					mview.printAccountSuccess(mDTO);
				} else { // 현재는 계정생성이 실패하는 경우가 없겠지만(ID중복검사를 통과한 경우에만 내려오기 때문에),
							// 추후 웹에서 서버 문제 등으로 회원가입이 중단되는 경우 계정생성실패 문구를 출력할 예정
					mview.printAccountFail();
				}
			} else if (action == 7) { // 개인정보변경
				// 추후 업데이트 예정입니다.
				view.nextUpdate();
				continue;

			} else if (action == 8) { // 구매목록 조회
                ArrayList<OrderContentDTO> ocdatas = new ArrayList<OrderContentDTO>();
                OrderDTO oDTO = new OrderDTO();
                oDTO.setmId(loginINFO.getmId());
                ocdatas = oContentDAO.selectAllByMid(oDTO);
                oview.printDatas(ocdatas);
			} else if (action == 9) { // 회원탈퇴 (회원 비활성화 전환)
				mview.printChangeUserStatus(); // 회원탈퇴 2차확인
				MemberDTO mDTO = new MemberDTO();
				mDTO.setmId(loginINFO.getmId());
				mDTO.setSearchCondition("회원탈퇴");
				boolean flag = mDAO.update(mDTO);

				if (flag) {
					mview.printLoginGhost();
				} else {
					// 추후 회원탈퇴 과정에서 문제가 생기는 경우(서버 문제 등)추가예정
				}
				loginINFO = null; // 비활성화 전환 후 자동 로그아웃

			} else if (action == 10) { // 로그아웃
				loginINFO = null;
				mview.printLogoutSuccess();

			} else if (action == 11) { // 상품추가(관리자)
				view.nextUpdate();
				continue;
			} else if (action == 12) { // 상품 재고 관리 (관리자)
				ArrayList<OrderContentDTO> ocdatas = new ArrayList<OrderContentDTO>();
                OrderContentDTO ocDTO = new OrderContentDTO();
                ocdatas = oContentDAO.selectAll(ocDTO);
                ocDTO = oContentDAO.selectOne(ocDTO);
                oview.printTotalSales(ocdatas, ocDTO);
//				view.nextUpdate();
//				continue;
			} else if (action == 13) { // 제품삭제 (관리자)
				view.nextUpdate();
				continue;
			} else if (action == 14) {	// 상품별 매출액
				
			} else if(action == 15) {	// 총 매출액
				
			}
		}

	}
}
