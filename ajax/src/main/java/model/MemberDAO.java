package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="SELECT * FROM MEMBER";
	private static final String SELECTONE="SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PW=? ";
	private static final String SELECTCKID="SELECT * FROM MEMBER WHERE MEMBER_ID=?";
	private static final String INSERT="INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,user)";
	private static final String UPDATE="UPDATE MEMBER SET NAME=? WHERE MEMBER_ID=? ";
	private static final String DELETE="DELETE FROM MEMBER WHERE MID=?";
	
	
	
	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) {
		ArrayList<MemberDTO> datas=new ArrayList<MemberDTO>();

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL);

			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				MemberDTO data=new MemberDTO();
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setMemberName(rs.getString("MEMBER_NAME"));
				data.setMemberPhNum(rs.getString("MEMBER_PHNUM"));
				data.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				data.setMemberGrade(rs.getString("MEMBER_GRADE"));
				data.setMemberGender(rs.getString("MEMBER_GENDER"));
				data.setMemberBirth(rs.getDate("MEMBER_BIRTH"));
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
	
	
	public MemberDTO selectOne(MemberDTO mDTO) {
		MemberDTO data=null;
		
		conn=JDBCUtil.connect();
		try {
			if(mDTO.getSearchCondition().equals("중복검사")) {
				pstmt=conn.prepareStatement(SELECTCKID);
				pstmt.setString(1,mDTO.getMemberID());
			}
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				data=new MemberDTO();
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setMemberName(rs.getString("MEMBER_NAME"));
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		
		return data;
	}
	public boolean insert(MemberDTO mDTO) {
		return true;
	}
	
	public void update() {
		
	}
	public void delete() {
		
	}
}
