package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;
import model.DTO.OrderContentDTO;
import model.DTO.OrderDTO;

public class OrderContentDAO {
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL="SELECT ORDERLIST_CONTENT_ID, ORDERLIST_ID, PRODUCT_ID, ORDERLIST_CONTENT_CNT FROM ORDERLIST_CONTENT WHERE ORDERLIST_ID=?";
	// SELECT 구문을 사용하여 입력받은 값이 ORDERLIST_CONTENT 테이블의 ORDERLIST_ID와 같으면 해당 행 전체 출력한다. 추후 주문내역 상세보기에 사용할 예정
	
	private static final String SELECTALL_SALES_BY_PRODUCT = 
			"SELECT P.PRODUCT_ID ,P.PRODUCT_NAME , NVL(SUM(OC.ORDERLIST_CONTENT_CNT),0) AS SALES_CNT, P.PRODUCT_PRICE  ,NVL(SUM(P.PRODUCT_PRICE*OC.ORDERLIST_CONTENT_CNT),0) AS TOTALSALES  FROM ORDERLIST_CONTENT OC "
			+ "	FULL OUTER JOIN PRODUCT P ON OC.PRODUCT_ID = P.PRODUCT_ID "
			+ "	GROUP BY P.PRODUCT_NAME, P.PRODUCT_ID, P.PRODUCT_PRICE  "
			+ "	ORDER BY PRODUCT_ID ASC";
	// 상품별 가격, 판매개수, 총 매출액
	
	private static final String SELECTALL_BY_MID="SELECT ORDERLIST_CONTENT_ID, OC.ORDERLIST_ID, P.PRODUCT_NAME, ORDERLIST_CONTENT_CNT, O.MEMBER_ID, P.PRODUCT_CATEGORY, P.PRODUCT_PRICE,ORDERLIST_DATE "
            + " FROM ORDERLIST_CONTENT OC, ORDERLIST O, PRODUCT P "
            + " WHERE OC.ORDERLIST_ID = O.ORDERLIST_ID "
            + " AND OC.PRODUCT_ID = P.PRODUCT_ID "
            + " AND O.MEMBER_ID = ?";
			/*
			 * SELECT 구문을 사용하여 MULTIPLE JOIN을 사용한다
			 * MULTIPLE JOIN은 3개 테이블을 조인하는 경우 사용한다.
			 * 기본 형태는
			 * SELECT	X. 컬럼이름 A,
			 * 			Y. 컬럼이름 B,
			 * 			Z. 컬럼이름 C,...
			 * FROM 테이블이름x X, 테이블이름y Y, 테이블이름z Z, ..
			 * WHERE X.컬럼이름 A = Y.컬럼이름 B
			 * AND   Y.컬럼이름 C = Z.컬럼이름 D
			 * 
			 * 위 내용을 참고하여 해당 쿼리를 해석하면
			 * ORDERLIST_CONTENT, ORDERLIST, PRODUCT 테이블을 조인하여
			 * 입력받은 값이 ORDERLIST의 MEMBER_ID와 같은 값이 있다면 해당 행의 모든 행을 조회한다.
			 */
	private static final String SELECTONE="SELECT SUM(P.PRODUCT_PRICE*OC.ORDERLIST_CONTENT_CNT) FROM ORDERLIST_CONTENT OC "
			+ "	INNER JOIN PRODUCT P ON OC.PRODUCT_ID = P.PRODUCT_ID"; 
	// 총 매출
	
	private static final String INSERT="INSERT INTO ORDERLIST_CONTENT(ORDERLIST_CONTENT_ID, ORDERLIST_ID ,PRODUCT_ID, ORDERLIST_CONTENT_CNT) VALUES ((SELECT NVL(MAX(ORDERLIST_CONTENT_ID),0)+1 FROM ORDERLIST_CONTENT),?,?,?)"; 
	// INSERT 구문을 사용하여 상품별 주문번호, 주문번호, 상품번호, 주문개수를 ORDERLIST_CONTENT 테이블에 추가한다.
	// 이때 상품별 주문번호는 NULL이라면 0, NULL이 아니라면 MAX값 +1을 저장한다.
	// 주문번호, 상품번호, 주문개수는 입력받은 값으로 저장한다
	// 주문내역이 추가될 때 사용
	
	private static final String UPDATE=""; 
	private static final String DELETE="";
	
	public ArrayList<OrderContentDTO> selectAll(OrderContentDTO oContentDTO){
		ArrayList<OrderContentDTO> datas = new ArrayList<OrderContentDTO>();
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(SELECTALL_SALES_BY_PRODUCT);		// SELECTALL_SALES_BY_PRODUCT 쿼리 사용
			ResultSet rs = pstmt.executeQuery();		// rs에 쿼리 결과 저장
			while(rs.next()) {
				OrderContentDTO data = new OrderContentDTO();
				data.setpId(rs.getInt("PRODUCT_ID"));
				data.setoContentCnt(rs.getInt("SALES_CNT"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				data.setTotalPrice(rs.getInt("TOTALSALES"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return datas;
	}
	public ArrayList<OrderContentDTO> selectAllByMid(OrderDTO oDTO){
        ArrayList<OrderContentDTO> datas = new ArrayList<OrderContentDTO>();
        conn=JDBCUtil.connect();	// JDBC 연결
        try {
            pstmt=conn.prepareStatement(SELECTALL_BY_MID);	// SELECTALL_BY_MID 쿼리 사용
            pstmt.setString(1, oDTO.getmId());				// 쿼리의 1번째 ?(mId)에 oContentDTO.getmId값 넣기
            ResultSet rs = pstmt.executeQuery();			// rs에 쿼리 결과 저장
            while(rs.next()) {
                OrderContentDTO data = new OrderContentDTO();
                data.setoContentId(rs.getInt("ORDERLIST_CONTENT_ID"));
                data.setoId(rs.getInt("ORDERLIST_ID"));
                data.setProductName(rs.getString("PRODUCT_NAME"));
                data.setoContentCnt(rs.getInt("ORDERLIST_CONTENT_CNT"));
                data.setMemberName(rs.getString("MEMBER_ID"));
                data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
                data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
                data.setOrderDate(rs.getDate("ORDERLIST_DATE"));
                datas.add(data);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
        }
        return datas;
    }
	
	public OrderContentDTO selectOne(OrderContentDTO oContentDTO) {
		OrderContentDTO data=null;
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(SELECTONE);		// SELECTONE 쿼리 사용
			ResultSet rs=pstmt.executeQuery();			// rs에 쿼리결과 저장
			if(rs.next()) {
				data=new OrderContentDTO();
				data.setTotalPrice(rs.getInt("SUM(P.PRODUCT_PRICE*OC.ORDERLIST_CONTENT_CNT)"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return data;
	}
	
	public boolean insert(OrderContentDTO oContentDTO) {
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(INSERT);				// INSERT 쿼리 사용
			pstmt.setInt(1, oContentDTO.getoId());				// 쿼리의 1번째 ?(oId)에 oContentDTO.getoId값 넣기
			pstmt.setInt(2, oContentDTO.getpId());				// 쿼리의 2번째 ?(pId)에 oContentDTO.getpId값 넣기
			pstmt.setInt(3, oContentDTO.getoContentCnt());		// 쿼리의 3번째 ?(oContentCnt)에 oContentDTO.getoContentCnt값 넣기
			int result=pstmt.executeUpdate();					// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0
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
	public boolean update(OrderContentDTO oContentDTO) {
		return true;
	}
	public boolean delete(OrderContentDTO oContentDTO) {
		return true;
	}
	
}
