package model.DTO;

public class ProductDTO {
	private int pId;	// PK
	private String pName; // 상품명
	private String pCategory;	// 카테고리
	private int pPrice;	// 가격
	private int pCnt;	// 재고
	private String pInfo;	// 제품 설명
	private String pImgUrl;
	
	public String getpImgUrl() {
		return pImgUrl;
	}
	public void setpImgUrl(String pImgUrl) {
		this.pImgUrl = pImgUrl;
	}
	private String searchCondition; // 검색조건
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpCategory() {
		return pCategory;
	}
	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpCnt() {
		return pCnt;
	}
	public void setpCnt(int pCnt) {
		this.pCnt = pCnt;
	}
	public String getpInfo() {
		return pInfo;
	}
	public void setpInfo(String pInfo) {
		this.pInfo = pInfo;
	}
	@Override
	public String toString() {
		return "ProductDTO [pId=" + pId + ", pName=" + pName + ", pCategory=" + pCategory + ", pPrice=" + pPrice
				+ ", pCnt=" + pCnt + ", pInfo=" + pInfo + ", pImgUrl=" + pImgUrl + "]";
	}
	
	
	
}
