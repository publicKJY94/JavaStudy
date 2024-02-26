package class01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {

	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO){
		ArrayList<ProductDTO> datas=new ArrayList<ProductDTO>();
		
		conn=JDBCUtil.connect();
		try {
			// 3. pstmt 로 CRUD 수행
			pstmt=conn.prepareStatement("SELECT * FROM PRODUCT");
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO data=new ProductDTO();
				data.setPid(rs.getInt("PID"));
				data.setName(rs.getString("NAME"));
				data.setPrice(rs.getInt("PRICE"));
				data.setCnt(rs.getInt("CNT"));
				datas.add(data);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		
		return datas;
	}
	public ProductDTO selectOne(ProductDTO pDTO){
		// conn 연결
		// pstmt 연결
		// pstmt 해제
		// conn 해제
		return null;
	}
	public boolean insert(ProductDTO pDTO) {
		// conn 연결
		// pstmt 연결
		// pstmt 해제
		// conn 해제
		return false;
	}
	public boolean update(ProductDTO pDTO) {
		// conn 연결
		// pstmt 연결
		// pstmt 해제
		// conn 해제
		return false;
	}
	public boolean delete(ProductDTO pDTO) {
		// conn 연결
		// pstmt 연결
		// pstmt 해제
		// conn 해제
		return false;
	}

}
