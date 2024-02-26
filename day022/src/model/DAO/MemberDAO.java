package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JDBCUtil;
import model.DTO.MemberDTO;

public class MemberDAO {
	
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL="SELECT MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_GRADE, MEMBER_AGE FROM MEMBER"; // Member테이블 전체 목록
	// SELECT 구문을 이용하여 MEMBER 테이블의 아이디, 비밀번호, 이름, 상태, 나이를 조회
	
	private static final String SELECTONE="SELECT MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_GRADE, MEMBER_AGE FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PW=?"; // 로그인
	// SELECT 구문을 이용하여 MEMBER 테이블의 아이디, 비밀번호, 이름, 상태, 나이를 조회하는데 ID와 PW를 입력받아 같은 값이 있는 것만 조회하여 로그인 기능에 사용
	
	private static final String SELECTONE_CKID="SELECT MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_GRADE, MEMBER_AGE FROM MEMBER WHERE MEMBER_ID=?";	// ID 중복체크
	// SELECT 구문을 이용하여 입력받은 ID와 MEMBER 테이블의 MEMBER_ID가 같은 값인 것을 조회하여 ID 중복체크에 사용
	
	private static final String INSERT="INSERT INTO MEMBER(MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_GRADE, MEMBER_AGE) VALUES(?,?,?,'USER',?)"; // 회원가입
	// INSERT 구문을 이용하여 아이디, 비밀번호, 이름, 나이를 입력받고 GRADE는 기본 'USER'로 고정하여 MEMBER 테이블에 추가한다. 회원가입 기능에 사용
	
	private static final String UPDATE="UPDATE MEMBER SET MEMBER_PW=?, MEMBER_NAME=?, MEMBER_AGE=? WHERE MEMBER_ID=?"; // 정보 수정
	// UPDATE 구문을 이용하여 ID값을 비교한 후 같은 ID일 때 비밀번호, 이름, 나이를 입력받아 변경한다. 정보 수정 기능에 사용
	
	private static final String UPDATE_GRADE="UPDATE MEMBER SET MEMBER_GRADE='GHOST' WHERE MEMBER_ID=?"; // 회원탈퇴(비활성화)
	// UPDATE 구문을 이용하여 ID값을 비교한 후 같은 ID일 때 상태를 'GHOST'로 변경한다. 회원탈퇴(비활성화) 기능에 사용
	
	private static final String DELETE="DELETE FROM MEMBER WHERE MEMBER_ID=?";
	// DELETE 구문을 이용하여 ID값을 비교한 후 같은 ID일 때 그 데이터를 삭제한다. // 회원삭제
	
	public ArrayList<MemberDTO> selectAll(MemberDTO memberDTO){
		ArrayList<MemberDTO> datas = new ArrayList<MemberDTO>();
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(SELECTALL);	
			ResultSet rs = pstmt.executeQuery();	// SELECTALL 쿼리 실행
			
			while(rs.next()) {	// 쿼리의 실행 결과를 data에 담아서 datas에 추가
				MemberDTO data = new MemberDTO();	
				data.setmId(rs.getString("MEMBER_ID"));			// MEMBER_ID값, mId에 추가
				data.setmPw(rs.getString("MEMBER_PW"));			// MEMBER_PW값, mPw에 추가
				data.setmName(rs.getString("MEMBER_NAME"));		// MEMBER_NAME값, mName에 추가
				data.setmGrade(rs.getString("MEMBER_GRADE"));	// MEMBER_GRADE값, mGrade에 추가
				data.setmAge(rs.getInt("MEMBER_AGE"));			// MEMBER_AGE값, mAge에 추가
				datas.add(data);								// data의 값 datas에 추가
			}
			rs.close();	// rs 연결해제
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// try catch문 이후 실행 / JDBC 연결해제
		}
		return datas;	// datas 반환
	}
	
	public MemberDTO selectOne(MemberDTO memberDTO) {
		MemberDTO data=null;
		
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			if(memberDTO.getSearchCondition().equals("ID중복검사")) {	// ID중복검사
				pstmt=conn.prepareStatement(SELECTONE_CKID); 	// 입력받은 id와 같은 값이 있는지 확인 없으면 null => 성공(중복 아님)
				pstmt.setString(1, memberDTO.getmId());			// 쿼리의 1번째 ?(mID)에 memberDTO.getmId값 넣기	
			}
			else if(memberDTO.getSearchCondition().equals("로그인")) {	// 로그인
				pstmt=conn.prepareStatement(SELECTONE);	// 입력받은 id와 pw가 같은 값이 있는지 확인 있으면 로그인 성공, 없으면 실패
				pstmt.setString(1, memberDTO.getmId());	// 쿼리의 1번째 ?(mId)에 memberDTO.getmId값 넣기 
				pstmt.setString(2, memberDTO.getmPw());	// 쿼리의 2번째 ?(mPw)에 memberDTO.getmPw값 넣기
				
			}
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				data=new MemberDTO();
				data.setmId(rs.getString("MEMBER_ID"));
				data.setmPw(rs.getString("MEMBER_PW"));
				data.setmName(rs.getString("MEMBER_NAME"));
				data.setmGrade(rs.getString("MEMBER_GRADE"));
				data.setmAge(rs.getInt("MEMBER_AGE"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); 	// JDBC 연결해제
		}
		
		return data;
	}
	
	public boolean insert(MemberDTO memberDTO) {	// 회원가입
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT);		// INSERT 쿼리 사용
			pstmt.setString(1, memberDTO.getmId());		// 쿼리의 1번째 ?(mId)에 memberDTO.getmId값 넣기
			pstmt.setString(2, memberDTO.getmPw());		// 쿼리의 2번째 ?(mPw)에 memberDTO.getmPw값 넣기
			pstmt.setString(3, memberDTO.getmName());	// 쿼리의 3번째 ?(mName)에 memberDTO.getmName값 넣기
			pstmt.setInt(4, memberDTO.getmAge());		// 쿼리의 4번째 ?(mAge)에 memberDTO.getmAge값 넣기
			int result = pstmt.executeUpdate();			// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return true;
	}
	public boolean update(MemberDTO memberDTO) {
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			if(memberDTO.getSearchCondition().equals("정보변경")) {	// 정보변경
				pstmt=conn.prepareStatement(UPDATE);			// UPDATE 쿼리 사용
				pstmt.setString(1, memberDTO.getmPw());			// 쿼리의 1번째 ?(mPw)에 memberDTO.getmPw값 넣기
				pstmt.setString(2, memberDTO.getmName());		// 쿼리의 2번째 ?(mName)에 memberDTO.getmName값 넣기
				pstmt.setInt(3, memberDTO.getmAge());			// 쿼리의 3번째 ?(mAge)에 memberDTO.getmAge값 넣기
				pstmt.setString(4, memberDTO.getmId());			// 쿼리의 4번째 ?(mId)에 memberDTO.getmId값 넣기
			}
			else if(memberDTO.getSearchCondition().equals("회원탈퇴")) {	// 회원탈퇴
				pstmt=conn.prepareStatement(UPDATE_GRADE);		// UPDATE_GRADE 쿼리 사용
				pstmt.setString(1, memberDTO.getmId());			// 쿼리의 1번째 ?(mId)에 memberDTO.getmId값 넣기
			}
			int result = pstmt.executeUpdate();					// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); 	// JDBC 연결해제
		}
		
		return true;
	}
	public boolean delete(MemberDTO memberDTO) {	// 회원삭제
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(DELETE);	// DELETE 쿼리 사용
			pstmt.setString(1, memberDTO.getmId());	// 쿼리의 1번째 ?(mId)에 memberDTO.getmId값 넣기 
			int result = pstmt.executeUpdate();		// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0 
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return true;
	}
}
