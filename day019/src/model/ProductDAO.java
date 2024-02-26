package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {

	private Connection connect;
	private PreparedStatement preparedStatement;

	private static final String SELECTALL = "SELECT * FROM PRODUCT";
	private static final String SELECTONE = "SELECT * FROM PRODUCT WHERE PID=?";
	private static final String INSERT = "INSERT INTO PRODUCT VALUES((SELECT NVL(MAX(PID),1000) + 1 FROM PRODUCT),?,?,?)";
	private static final String UPDATE = "UPDATE PRODUCT P SET CNT=CNT-? WHERE P.PID = ? AND P.CNT>=?";
	private static final String DELETE = "DELETE FROM PRODUCT P WHERE P.PID=?";

	public ArrayList<ProductDTO> selectAll(ProductDTO productDTO) {
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
		connect = JDBCUtil.connect();
		try {
			preparedStatement = connect.prepareStatement(SELECTALL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductDTO data = new ProductDTO();
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
			JDBCUtil.disconnect(preparedStatement, connect);
		}
		return datas;
	}

	public ProductDTO selectOne(ProductDTO productDTO) {
		ProductDTO data = new ProductDTO();
		connect = JDBCUtil.connect();
		try {
			preparedStatement = connect.prepareStatement(SELECTONE);
			preparedStatement.setInt(1, productDTO.getPid());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				data.setPid(rs.getInt("PID"));
				data.setName(rs.getString("NAME"));
				data.setPrice(rs.getInt("PRICE"));
				data.setCnt(rs.getInt("CNT"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(preparedStatement, connect);
		}

		return data;
	}

	public boolean insert(ProductDTO productDTO) {
		connect = JDBCUtil.connect();
		try {
			preparedStatement = connect.prepareStatement(INSERT);
			preparedStatement.setString(1, productDTO.getName());
			preparedStatement.setInt(2, productDTO.getPrice());
			preparedStatement.setInt(3, productDTO.getCnt());
			int result = preparedStatement.executeUpdate();
			if (result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(preparedStatement, connect);
		}
		return true;
	}

	public boolean update(ProductDTO productDTO) {
//		1. 상품번호를 입력받아서 해당 상품 삭제하기
//		2. 상품번호를 입력받아서 해당 상품 1개 구매하기
//		3. 구매할때에 재고가없다면 구매실패!
		connect = JDBCUtil.connect();
		try {
			preparedStatement = connect.prepareStatement(UPDATE);
			preparedStatement.setInt(1, productDTO.getPid());
			preparedStatement.setInt(3, productDTO.getPid());
			int result = preparedStatement.executeUpdate();
			if (result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			return false;
		} finally {
			JDBCUtil.disconnect(preparedStatement, connect);
		}
		return true;
	}

	public boolean delete(ProductDTO productDTO) {
		connect = JDBCUtil.connect();
		try {
			preparedStatement = connect.prepareStatement(DELETE);
			preparedStatement.setInt(1, productDTO.getPid());
			int result = preparedStatement.executeUpdate();
			if (result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			return false;
		} finally {
			JDBCUtil.disconnect(preparedStatement, connect);
		}
		return true;
	}

}
