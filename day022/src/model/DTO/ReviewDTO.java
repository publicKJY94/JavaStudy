package model.DTO;

import java.util.Date;

public class ReviewDTO {
	private int rid;
	private String mid;
	private String content;
	private int order_num;
	private int product_grade;
	private Date registed_date;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getProduct_grade() {
		return product_grade;
	}
	public void setProduct_grade(int product_grade) {
		this.product_grade = product_grade;
	}
	public Date getRegisted_date() {
		return registed_date;
	}
	public void setRegisted_date(Date registed_date) {
		this.registed_date = registed_date;
	}
	@Override
	public String toString() {
		return "ReviewDTO [rid=" + rid + ", mid=" + mid + ", content=" + content + ", order_num=" + order_num
				+ ", product_grade=" + product_grade + ", registed_date=" + registed_date + "]";
	}
	
	
}
