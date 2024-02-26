package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.directory.SearchControls;

public class BoardDAO {

	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL = "SELECT * FROM BOARD";
	private static final String SELECTBYWRITER = "SELECT * FROM BOARD WHERE WRITER LIKE '%'||?||'%' ORDER BY REGDATE DECS";
	private static final String SELECTBYTITLE = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY REGDATE DECS";
	private static final String SELECTONE = "SELECT * FROM BOARD WHERE BID = ?";
//	private static final String INSERT = "INSERT INTO BOARD VALUES((SELECT NVL(MAX(BID),10) + 1 FROM BOARD),?,?,?,SYSDATE)";
	private static final String INSERT="INSERT INTO BOARD(BID, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(BID),10) + 1 FROM BOARD),?,?,?)";
	private static final String UPDATE = "UPDATE BOARD SET ? WHERE BID = ?";
	private static final String DELETE = "DELETE FROM BOARD WHERE BID = ?";

	public ArrayList<BoardDTO> selectAll(BoardDTO bDTO) {
		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>();
		conn = JDBCUtil.connect();
		try {
			if(bDTO.getSearchCondition().equals("ALL")) {
				pstmt = conn.prepareStatement(SELECTALL);
			}else if(bDTO.getSearchCondition().equals("WRITER")) {
				pstmt = conn.prepareStatement(SELECTBYWRITER);
				pstmt.setString(1, bDTO.getWriter());
			}else if(bDTO.getSearchCondition().equals("TITLE")) {
				pstmt = conn.prepareStatement(SELECTBYTITLE);
				pstmt.setString(1, bDTO.getTitle());
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardDTO data = new BoardDTO();
				data.setBid(rs.getInt("BID"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				data.setContent(rs.getString("CONTENT"));
				data.setRegdate(rs.getDate("REGDATE"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return datas;
	}

	public BoardDTO selectOne(BoardDTO bDTO) {
		BoardDTO data = new BoardDTO();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				data.setBid(rs.getInt("BID"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				data.setContent(rs.getString("CONTENT"));
				data.setRegdate(rs.getDate("REGDATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	public boolean insert(BoardDTO bDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, bDTO.getTitle()); // 제목 작성자 컨텐츠
			pstmt.setString(2, bDTO.getWriter()); // 제목 작성자 컨텐츠
			pstmt.setString(3, bDTO.getContent()); // 제목 작성자 컨텐츠
			int result = pstmt.executeUpdate();
			if(result <= 0) {
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

	public boolean update(BoardDTO bDTO) {
		return false;
	}

	public boolean delete(BoardDTO bDTO) {
		return false;
	}

}
