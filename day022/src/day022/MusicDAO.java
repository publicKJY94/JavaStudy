package day022;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MusicDAO {
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL = "SELECT * FROM MUSIC";
	private static final String SELECTONE = "SELECT * FROM MUSIC WHERE MID=?";
	private static final String INSERT = "INSERT INTO MUSIC (MID,RANK,TITLE,SINGER,ALBUM) VALUES((SELECT NVL(MAX(MID),0) + 1 FROM MUSIC),?,?,?,?)";
	private static final String UPDATE = "UPDATE MUSIC SET RANK=?,TITLE=? SINGER = ?,ALBUM=? WHERE MID = ?";
	private static final String DELETE = "DELETE FROM MUSIC WHERE MID=?";

	public boolean insert(MusicDTO mDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, mDTO.getRank());
			pstmt.setString(2, mDTO.getTitle());
			pstmt.setString(3, mDTO.getSinger());
			pstmt.setString(4, mDTO.getAlbum());
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
