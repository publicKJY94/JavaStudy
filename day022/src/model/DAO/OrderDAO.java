package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;
import model.DTO.CartDTO;
import model.DTO.OrderDTO;
import model.DTO.ProductDTO;

public class OrderDAO {
	// CRUD
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL=
			"SELECT O.ORDERLIST_ID , OCP.PRODUCT_ID , OCP.PRODUCT_NAME , OCP.PRODUCT_CATEGORY , OCP.PRODUCT_PRICE, OCP.ORDERLIST_CONTENT_CNT, O.MEMBER_ID, O.ORDERLIST_DATE, M.MEMBER_NAME "
			+ " FROM ORDERLIST O "
			+ "	INNER JOIN "
			+ "		(SELECT OC.ORDERLIST_CONTENT_ID , OC.ORDERLIST_CONTENT_CNT , OC.ORDERLIST_ID , OC.PRODUCT_ID , P.PRODUCT_NAME , P.PRODUCT_CATEGORY , P.PRODUCT_PRICE , P.PRODUCT_INFO "
			+ "		FROM ORDERLIST_CONTENT OC INNER JOIN PRODUCT P ON P.PRODUCT_ID = OC.PRODUCT_ID) OCP "
			+ "		ON O.ORDERLIST_ID = OCP.ORDERLIST_ID "
			+ "	INNER JOIN MEMBER M ON M.MEMBER_ID = O.MEMBER_ID "
			+ "	WHERE O.MEMBER_ID = ?";
			/*
			 * ORDERLIST_CONTENT 테이블에 PRODUCT 테이블을 INNER JOIN을 사용하여 상품별 주문번호, 주문개수, 주문번호, 상품번호, 상품명, 상품 카테고리, 상품가격, 상품 정보룰 조회한다.
			 * 조회한 테이블을 OCP라 별칭을 붙인다.
			 * ORDERLIST 테이블에 OCP를 INNER JOIN을 사용하고, MEMBER 테이블도 ORDERLIST 테이블에 INNER JOIN을 사용한다.
			 * 조인하여 주문번호, 상품번호, 상품명, 상품카테고리, 상품 가격, 주문개수, 사용자ID, 주문일시, 사용자이름을 조회한다.
			 * 단, 입력받은 값이 ORDERLIST 테이블의 MEMBER_ID와 같을 때 해당 행을 출력한다.
			 * */ 
	private static final String SELECTONE="SELECT MAX(ORDERLIST_ID) FROM ORDERLIST";
	// SELECT 구문을 사용하여 ORDERLIST 테이블의 ORDERLIST_ID의 MAX 값을 조회한다. 주문번호(PK)를 생성하기 위해 사용
	
	private static final String INSERT="INSERT INTO ORDERLIST(ORDERLIST_ID, MEMBER_ID, ORDERLIST_DATE) VALUES ((SELECT NVL(MAX(ORDERLIST_ID),0)+1 FROM ORDERLIST),?,SYSDATE)";
	// INSERT 구문을 사용하여 주문번호, 사용자ID, 주문 일시를 ORDERLIST 테이블에 추가한다. 주문번호는 NULL이면 0 NULL이 아니면 MAX값 +1로 저장된다. 주문일시는 SYSDATE로 저장된다.
	
	private static final String UPDATE="";		
	
	private static final String DELETE="DELETE FROM ORDERLIST WHERE ORDERLIST_ID=?";
	// DELETE 구문을 사용하여 입력받은 값이 ORDERLIST 테이블의 ORDERLIST_ID와 같으면 해당 행을 삭제한다. 추후 주문취소에 사용할 예정 
	
	public ArrayList<OrderDTO> selectAll(OrderDTO oDTO) {
		ArrayList<OrderDTO> datas = new ArrayList<OrderDTO>();
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(SELECTALL);			// SELECTALL 쿼리 사용
			pstmt.setString(1, oDTO.getmId());				// 쿼리의 1번째 ?(mId)에 oDTO.getmId값 넣기 
			ResultSet rs = pstmt.executeQuery();			// rs에 쿼리 실행결과 저장
			while(rs.next()) {
				OrderDTO data = new OrderDTO();
				data.setoId(rs.getInt("ORDERLIST_ID"));
				data.setmId(rs.getString("MEMBER_ID"));
				data.setoDate(rs.getDate("ORDERLIST_DATE"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결 해제
		}
		return datas;
	}
	public OrderDTO selectOne(OrderDTO oDTO) {
		OrderDTO data=null;
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(SELECTONE);		// SELECTONE 쿼리 사용
			ResultSet rs=pstmt.executeQuery();			// rs에 쿼리결과 저장
			if(rs.next()) {
				data=new OrderDTO();
				data.setoId(rs.getInt("MAX(ORDERLIST_ID)"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return data;
	}
	
	public boolean insert(OrderDTO oDTO) {
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(INSERT);	// INSERT 쿼리 사용
			pstmt.setString(1, oDTO.getmId());		// 쿼리의 1번째 ?(mId)에 oDTO.getmId값 넣기 
			int result=pstmt.executeUpdate();		// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0
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
	public boolean delete(OrderDTO oDTO) {
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(DELETE);	// DELETE 쿼리 사용
			pstmt.setInt(1, oDTO.getoId());			// 쿼리의 1번째 ?(oId)에 oDTO.getoId값 넣기 
			int result=pstmt.executeUpdate();		// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}

		return false;
	}
}
