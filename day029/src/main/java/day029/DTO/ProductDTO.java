package day029.DTO;

public class ProductDTO {
	private String product;
	private int cnt;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "ProductDTO [product=" + product + ", cnt=" + cnt + "]";
	}
}
