package checkid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
	
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTONE_CKID="SELECT MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_GRADE, MEMBER_AGE FROM MEMBER WHERE MEMBER_ID=?";	// ID 중복체크
	
	public MemberDTO selectOne(MemberDTO memberDTO) {
		MemberDTO data=null;
		
		conn=JDBCUtil.connect();
		try {
				pstmt=conn.prepareStatement(SELECTONE_CKID); // 입력받은 id와 같은 값이 있는지 확인 없으면 null => 성공(중복 아님)
				pstmt.setString(1, memberDTO.getMid());
				
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				data=new MemberDTO();
				data.setMid(rs.getString("MEMBER_ID"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		
		return data;
	}
	
}
