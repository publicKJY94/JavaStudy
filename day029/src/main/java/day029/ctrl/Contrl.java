package day029.ctrl;

import java.util.ArrayList;

import day029.DAO.ProductDAO;
import day029.DTO.ProductDTO;
public class Contrl {
	private ArrayList<ProductDTO> cart;

	public ArrayList<ProductDTO> getCart() {
		return cart;
	}

	public void setCart(ArrayList<ProductDTO> cart) {
		System.out.println("카트에 값 저장됨");
		this.cart = cart;
	}

//	view - JavaScript
//	controller - JAVA
//	model - jdbc
	
	public void start() {
		ProductDAO pDAO = new ProductDAO();
		System.out.println("db에 저장 시작");
		for (ProductDTO data : this.cart) {
			System.out.println(data.getProduct());
			System.out.println(data.getCnt());
			pDAO.insert(data);
			System.out.println("저장중");
		}
	}

}
