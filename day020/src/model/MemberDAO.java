package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL = "SELECT * FROM MEMBER";
	private static final String SELECTONE = "SELECT * FROM MEMBER M WHERE M.MID = ? AND M.MPW = ?";
	private static final String INSERT = "INSERT INTO MEMBER VALUES(?,?,?)";
	private static final String UPDATE = "UPDATE MEMBER SET ? WHERE MID = ?";
	private static final String DELETE = "DELETE FROM MEMBER WHERE MID = ?";

	private ArrayList<MemberDTO> selectAll(MemberDTO mDTO) {
			return null;
	}

	public MemberDTO selectOne(MemberDTO mDTO) {
		if (mDTO.getSearchCondition().equals("login")) {
			MemberDTO data = null;
			conn = JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(SELECTONE);
				pstmt.setString(1, mDTO.getMid());
				pstmt.setString(2, mDTO.getMpw());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					data = new MemberDTO();
					data.setMid(rs.getString("MID"));
					data.setName(rs.getString("NAME"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				JDBCUtil.disconnect(pstmt, conn);
			}

			return data;
		}
		return null;
	}

	private boolean insert(MemberDTO mDTO) {
		return false;
	}

	private boolean update(MemberDTO mDTO) {
		return false;
	}

	private boolean delete(MemberDTO mDTO) {
		return false;
	}

}
