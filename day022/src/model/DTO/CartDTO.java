package model.DTO;

public class CartDTO {
	private int cId;		// PK
	private String mId;		// 사용자ID(FK - MEBER 테이블 MEMBER_ID)
	private int pId;		// 상품번호(FK - PRODUCT 테이블 PRODUCT_ID)
	private int cCnt;		// 장바구니 담은 개수
	private String pName;	// 상품명
	private int pPrice;		// 상품가격

	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	private String searchCondition;
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getcCnt() {
		return cCnt;
	}
	public void setcCnt(int cCnt) {
		this.cCnt = cCnt;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	@Override
	public String toString() {
		return "CartDTO [cId=" + cId + ", mId=" + mId + ", pId=" + pId + "]";
	}
	
	
	
}
