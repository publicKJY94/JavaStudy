package day029.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import day029.DTO.ProductDTO;

public class ProductDAO {
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL = "SELECT * FROM MUSIC";
	private static final String SELECTONE = "SELECT * FROM MUSIC WHERE MID=?";
	private static final String UPDATE = "UPDATE MUSIC SET RANK=?,TITLE=? SINGER = ?,ALBUM=? WHERE MID = ?";
	private static final String DELETE = "DELETE FROM MUSIC WHERE MID=?";

	private static final String INSERT = "INSERT INTO PRODUCT (PRODUCT_ID,PRODUCT_NAME,PRODUCT_CATEGORY,PRODUCT_PRICE,PRODUCT_CNT,PRODUCT_INFO) VALUES((SELECT NVL(MAX(PRODUCT_ID),0) + 1 FROM PRODUCT),?,'헤드셋',?,?,'ㅁ')";

	public boolean insert(ProductDTO pDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, pDTO.getProduct());
			pstmt.setInt(2, 1000);
			pstmt.setInt(3, pDTO.getCnt());
			int result = pstmt.executeUpdate();
			if (result <= 0) {
				System.out.println(result);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public void selectOne() {

	}

	public void selectAll() {

	}

	public boolean update() {
		return false;
	}

	public boolean delete() {
		return false;
	}
}
