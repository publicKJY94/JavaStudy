package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;
import model.DTO.ProductDTO;

public class ProductDAO {
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL="SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_CNT, PRODUCT_CATEGORY, PRODUCT_INFO FROM PRODUCT";
	private static final String SELECTALL_NAME=
								"SELECT PRODUCT_ID,PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_CNT, PRODUCT_CATEGORY, PRODUCT_INFO"
								+ " FROM PRODUCT WHERE PRODUCT_NAME LIKE '%'||?||'%'";
	private static final String SELECTONE="SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_CATEGORY, PRODUCT_PRICE, PRODUCT_CNT, PRODUCT_INFO FROM PRODUCT WHERE PRODUCT_ID=?";		
	private static final String INSERT="INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT_NAME, PRODUCT_CATEGORY, PRODUCT_PRICE, PRODUCT_CNT, PRODUCT_INFO, PRODUCT_IMGURL) VALUES((SELECT NVL(MAX(PRODUCT_ID),1000)+1 FROM PRODUCT),?,?,?,'5',?,?)"; // 제품추가
	private static final String INSERT_CRAWLING="INSERT INTO PRODUCT "
			+"(PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_CNT, PRODUCT_IMG) "
			+"VALUES((SELECT NVL(MAX(PRODUCT_ID),1000)+1 FROM PRODUCT), ?, 'Bang&Olufsen', ?, ?, ?, ? , ?)";
	private static final String UPDATE_BUY ="UPDATE PRODUCT SET PRODUCT_CNT=PRODUCT_CNT-? WHERE PRODUCT_ID=?";
	private static final String UPDATE_ADDCNT ="UPDATE PRODUCT SET PRODUCT_CNT=PRODUCT_CNT+? WHERE PRODUCT_ID=?";
	private static final String DELETE="DELETE FROM PRODUCT WHERE PRODUCT_ID=?";
	
	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO){ 
		if(pDTO.getSearchCondition().equals("전체목록")) {
			ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
			conn=JDBCUtil.connect();
			try {
				pstmt=conn.prepareStatement(SELECTALL);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					ProductDTO data=new ProductDTO();
					data.setpId(rs.getInt("PRODUCT_ID"));
					data.setpName(rs.getString("PRODUCT_NAME"));
					data.setpPrice(rs.getInt("PRODUCT_PRICE"));
					data.setpCnt(rs.getInt("PRODUCT_CNT"));
					data.setpCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setpInfo(rs.getString("PRODUCT_INFO"));
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
		else if(pDTO.getSearchCondition().equals("이름검색")) {
			ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
			conn=JDBCUtil.connect();
			try {
				pstmt=conn.prepareStatement(SELECTALL_NAME);
				pstmt.setString(1, pDTO.getpName());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					ProductDTO data = new ProductDTO();
					data.setpId(rs.getInt("PRODUCT_ID"));
					data.setpName(rs.getString("PRODUCT_NAME"));
					data.setpPrice(rs.getInt("PRODUCT_PRICE"));
					data.setpCnt(rs.getInt("PRODUCT_CNT"));
					data.setpCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setpInfo(rs.getString("PRODUCT_INFO"));
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
		else {
			ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
			return datas;
		}
	}
	
	public ProductDTO selectOne(ProductDTO pDTO) {
		ProductDTO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE);
			pstmt.setInt(1, pDTO.getpId());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new ProductDTO();
				data.setpId(rs.getInt("PRODUCT_ID"));
				data.setpName(rs.getString("PRODUCT_NAME"));
				data.setpCategory(rs.getString("PRODUCT_CATEGORY"));
				data.setpPrice(rs.getInt("PRODUCT_PRICE"));
				data.setpCnt(rs.getInt("PRODUCT_CNT"));
				data.setpInfo(rs.getString("PRODUCT_INFO"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}
	public boolean insert(ProductDTO pDTO) {
		// 신상품 등록
//		conn=JDBCUtil.connect(); 
//		try {	
//			pstmt=conn.prepareStatement(INSERT);
//			pstmt.setString(1, pDTO.getpName());
//			pstmt.setString(2, pDTO.getpCategory());
//			pstmt.setInt(3, pDTO.getpPrice());
//			pstmt.setString(4, pDTO.getpInfo());
//			pstmt.setString(5, pDTO.getpImgUrl());
//			int result = pstmt.executeUpdate();
//			if(result <= 0) {
//				return false;
//			}
//		} catch (SQLException e) {
////			e.printStackTrace();
//		} finally {
//			JDBCUtil.disconnect(pstmt, conn);
//		}
		
		conn=JDBCUtil.connect(); // 크롤링
		try {
			pstmt = conn.prepareStatement(INSERT_CRAWLING);
			pstmt.setString(1, pDTO.getpName());
	         pstmt.setInt(2, pDTO.getpPrice());
	         pstmt.setString(3, pDTO.getpInfo());
	         pstmt.setString(4, pDTO.getpCategory());
	         pstmt.setInt(5, pDTO.getpCnt());
	         pstmt.setString(6, pDTO.getpImgUrl());
			int result = pstmt.executeUpdate();
			if(result<=0) {
				return false;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public boolean update(ProductDTO pDTO) {
		if(pDTO.getSearchCondition().equals("구매")) {
			conn=JDBCUtil.connect();
			try {
				pstmt=conn.prepareStatement(UPDATE_BUY);
				pstmt.setInt(1, pDTO.getpCnt());
				pstmt.setInt(2, pDTO.getpId());
				int result = pstmt.executeUpdate();
				if(result<=0) {
					return false;
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		else if(pDTO.getSearchCondition().equals("재고추가")) {
			conn=JDBCUtil.connect();
			try {
				pstmt=conn.prepareStatement(UPDATE_ADDCNT);
				pstmt.setInt(1, pDTO.getpCnt());
				pstmt.setInt(2, pDTO.getpId());
				int result = pstmt.executeUpdate();
				if(result<=0) {
					return false;
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		else {
			return false;
		}
		return true;
	}
	public boolean delete(ProductDTO pDTO) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE);
			pstmt.setInt(1, pDTO.getpId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	
}
