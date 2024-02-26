package model;

public class ProductDTO {
	private int pid;
	private String name;
	private int price;
	private int cnt;
	private int addCnt;
	private int startRange;
	private int endRange;
	private String searchCondition;

	public int getAddCnt() {
		return addCnt;
	}

	public void setAddCnt(int addCnt) {
		this.addCnt = addCnt;
	}

	public int getStartRange() {
		return startRange;
	}

	public void setStartRange(int startRange) {
		this.startRange = startRange;
	}

	public int getEndRange() {
		return endRange;
	}

	public void setEndRange(int endRange) {
		this.endRange = endRange;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "ProductDTO [pid=" + pid + ", name=" + name + ", price=" + price + ", cnt=" + cnt + "]";
	}
}
