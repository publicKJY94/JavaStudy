package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;
import model.DTO.CartDTO;

public class CartDAO {
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL="SELECT C.CART_ID, C.MEMBER_ID, C.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_PRICE , C.CART_CNT"
			+ " FROM CART C"
			+ "	INNER JOIN MEMBER M ON M.MEMBER_ID = C.MEMBER_ID"
			+ "	INNER JOIN PRODUCT P ON P.PRODUCT_ID = C.PRODUCT_ID"
			+ " WHERE C.MEMBER_ID = ?";
			/*
			 *  INNER JOIN을 사용하여 MEMBER 테이블과 PRODUCT 테이블을 조인하고 입력받은 MEMBER_ID의 값이 같을 때
			 *  장바구니ID, 사용자ID, 상품번호, 상품명, 상품가격, 장바구니에 담은 개수를 조회한다.
			 *  장바구니 목록을 조회할 때 사용
			 */
	private static final String SELECTONE=""; 
	
	private static final String INSERT="INSERT INTO CART(CART_ID, MEMBER_ID, PRODUCT_ID, CART_CNT) VALUES ((SELECT NVL(MAX(CART_ID),1)+1 FROM CART),?,?,?)"; 
	// INSERT 구문을 사용하여 장바구니 ID, 사용자 ID, 상품번호, 장바구니에 담은 개수를 입력받아 CART 테이블에 추가하는데 장바구니 ID는 NULL이면 1로, NULL이 아니면 MAX값을 찾아 +1 한 값으로 추가한다.
	// 사용자가 장바구니에 상품을 담을 때 사용
	
	private static final String UPDATE="UPDATE CART SET CNT=? WHERE PRODUCT_ID=?"; 
	// UPDATE 구문을 사용하여 입력받은 값이 CART 테이블의 PRODUCT_ID와 같은 값이 있을 때 그 해당 항목의 CNT 값을 입력받은 값으로 변경한다. 재고 변경에 사용
	
	private static final String DELETE="DELETE FROM CART WHERE MEMBER_ID=?";
	// DELETE 구문을 사용하여 입력받은 값이 CART 테이블의 MEMBER_ID와 같은 값이 있을 때 그 해당 항목을 삭제한다. 장바구니 비우기에 사용
	
	public ArrayList<CartDTO> selectAll(CartDTO cartDTO){	// 전체 목록 조회
		ArrayList<CartDTO> datas = new ArrayList<CartDTO>();
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(SELECTALL);				// SELECTALL 쿼리 사용
			pstmt.setString(1, cartDTO.getmId());				// 쿼리의 1번째 ?(mID)에 cartDTO.getmId값 넣기
			ResultSet rs = pstmt.executeQuery();				// 쿼리의 결과 rs에 저장
			
			while(rs.next()) {
				CartDTO data = new CartDTO();					
				data.setcId(rs.getInt("CART_ID"));				
				data.setmId(rs.getString("MEMBER_ID"));
				data.setpId(rs.getInt("PRODUCT_ID"));
				data.setcCnt(rs.getInt("CART_CNT"));
				data.setpName(rs.getString("PRODUCT_NAME"));
				data.setpPrice(rs.getInt("PRODUCT_PRICE"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); 	// JDBC 연결해제
		}
		return datas;
	}
	public CartDTO selectOne(CartDTO cartDTO) {
		return null;
	}
	public boolean insert(CartDTO cartDTO) {	// 장바구니 담기
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(INSERT);	// INSERT 쿼리 사용
			pstmt.setString(1, cartDTO.getmId());	// 쿼리의 1번째 ?(mID)에 cartDTO.getmId값 넣기
			pstmt.setInt(2, cartDTO.getpId());		// 쿼리의 2번째 ?(pID)에 cartDTO.getpId값 넣기
			pstmt.setInt(3, cartDTO.getcCnt());		// 쿼리의 3번째 ?(cCnt)에 cartDTO.getcCnt값 넣기
			int result = pstmt.executeUpdate();		// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0 
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return true;
	}
	public boolean update(CartDTO cartDTO) {	// 장바구니 담은 개수 변경
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(UPDATE);	// UPDATE 쿼리 사용
			pstmt.setInt(1, cartDTO.getcCnt());		// 쿼리의 1번째 ?(cCnt)에 cartDTO.getcCnt값 넣기
			pstmt.setInt(2, cartDTO.getpId());		// 쿼리의 1번째 ?(pID)에 cartDTO.getpId값 넣기
			int result = pstmt.executeUpdate();		// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0 
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return true;
	}
	public boolean delete(CartDTO cartDTO) {		// 장바구니 비우기
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE);	// DELETE 쿼리 사용
			pstmt.setString(1, cartDTO.getmId());	// 쿼리의 1번째 ?(mID)에 cartDTO.getmId값 넣기
			int result = pstmt.executeUpdate();		// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);		// JDBC 연결해제
		}
		return true;
	}
}
