package model;

public class ProductDTO {
	private int pid;
	private String name;
	private int price;
	private int cnt;

	@Override
	public String toString() {
		return "ProductDTO [pid=" + pid + ", name=" + name + ", price=" + price + ", cnt=" + cnt + "]";
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

}
