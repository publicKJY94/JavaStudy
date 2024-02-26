package model.DTO;

public class MemberDTO {
	private String mId; 	// 아이디(PK)
	private String mPw;		// 패스워드
	private String mName;	// 이름
	private String mGrade;	// 상태
	private int mAge;		// 나이
	
	private String searchCondition;	// 검색조건
	
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmGrade() {
		return mGrade;
	}
	public void setmGrade(String mGrade) {
		this.mGrade = mGrade;
	}
	public int getmAge() {
		return mAge;
	}
	public void setmAge(int mAge) {
		this.mAge = mAge;
	}
	@Override
	public String toString() {
		return "MemberDTO [mId=" + mId + ", mName=" + mName + ", mGrade=" + mGrade + ", mAge=" + mAge + "]";
	}
	
	
}
