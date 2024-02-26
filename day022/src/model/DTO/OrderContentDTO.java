package model.DTO;

import java.util.Date;

public class OrderContentDTO {
	private int oContentId;		// 시리얼 넘버(구매한 상품마다 증가)
	private int oId;			// ORDERLIST 테이블 PK (주문번호)
	private int pId;			// PRODUCT 테이블 PK (상품번호)
	private int oContentCnt;	// 구매 개수

	// join 결과를 받아오기 위한 자바에서만 사용하는 멤버변수
	private String productName; 	// 상품명
	private String productCategory; // 카테고리  
	private int productPrice;		// 상품가격
	private String memberName;		// 구매자
	private Date OrderDate;			// 구매일자
	private int totalPrice;			// 총 매출액
	
	
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getoContentId() {
		return oContentId;
	}
	public void setoContentId(int oContentId) {
		this.oContentId = oContentId;
	}
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public int getoContentCnt() {
		return oContentCnt;
	}
	public void setoContentCnt(int oContentCnt) {
		this.oContentCnt = oContentCnt;
	}
	@Override
	public String toString() {
		return "OrderContentDTO [oContentId=" + oContentId + ", oId=" + oId + ", pId=" + pId + ", oContentCnt="
				+ oContentCnt + "]";
	}
	
	
}
