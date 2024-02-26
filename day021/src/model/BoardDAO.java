package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {

	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	// 만약 전체검색이라면 SELECTALL
	private static final String SELECTALL = "SELECT * FROM BOARD ORDER BY BID DESC";
	// 만약 작가검색이라면 SELECTBYWRITER
	private static final String SELECTBYWRITER = "SELECT * FROM BOARD WHERE WRITER LIKE '%'||?||'%' ORDER BY BID";
	// 만약 제목검색이라면 SELECTBYTITLE
	private static final String SELECTBYTITLE = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY BID";
	private static final String SELECTONE = "SELECT * FROM BOARD WHERE BID=?";
	private static final String INSERT = "INSERT INTO BOARD (BID,TITLE,WRITER,CONTENT) VALUES((SELECT NVL(MAX(BID),10) + 1 FROM BOARD),?,?,?)";
	private static final String UPDATE = "UPDATE BOARD SET TITLE=?,CONTENT=? WHERE BID=?";
	private static final String DELETE = "DELETE FROM BOARD WHERE BID=?";

	public ArrayList<BoardDTO> selectAll(BoardDTO bDTO) {
		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>();

		conn = JDBCUtil.connect();
		try {
			// 사용되는 쿼리를 통제한다 bDTO에 searchConditin을 추가하여 통제하겠다
			if( bDTO.getSearchCondition().equals("ALL") ) {
				pstmt = conn.prepareStatement(SELECTALL); // << 해당위치에 조건식으로 작성예정
			}else if(bDTO.getSearchCondition().equals("WRITER")) {
				pstmt = conn.prepareStatement(SELECTBYWRITER); // << 해당위치에 조건식으로 작성예정
				// pstmt의 첫번째 공백에 전달받은 검색키워드를 전달해준다
				pstmt.setString(1, bDTO.getSearchKeyword());
			}else if(bDTO.getSearchCondition().equals("TITLE")){
				pstmt = conn.prepareStatement(SELECTBYTITLE); // << 해당위치에 조건식으로 작성예정
				// pstmt의 첫번째 공백에 전달받은 검색키워드를 전달해준다
				pstmt.setString(1, bDTO.getSearchKeyword());
			}
			
			// 결과값을 담아줄 예정이므로 나중에 사용한다
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO data = new BoardDTO();
				data.setBid(rs.getInt("BID"));
				data.setContent(rs.getString("CONTENT"));
				data.setRegdate(rs.getDate("REGDATE"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
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

	public BoardDTO selectOne(BoardDTO bDTO) {
		BoardDTO data = null;

		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setInt(1, bDTO.getBid());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				data = new BoardDTO();
				data.setBid(rs.getInt("BID"));
				data.setContent(rs.getString("CONTENT"));
				data.setRegdate(rs.getDate("REGDATE"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return data;
	}

	public boolean insert(BoardDTO bDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, bDTO.getTitle());
			pstmt.setString(2, bDTO.getWriter());
			pstmt.setString(3, bDTO.getContent());
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

	public boolean update(BoardDTO bDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, bDTO.getTitle());
			pstmt.setString(2, bDTO.getContent());
			pstmt.setInt(3, bDTO.getBid());
			int result = pstmt.executeUpdate();
			if (result <= 0) {
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

	public boolean delete(BoardDTO bDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, bDTO.getBid());
			int result = pstmt.executeUpdate();
			System.out.println(result);
			if (result <= 0) {
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

}
