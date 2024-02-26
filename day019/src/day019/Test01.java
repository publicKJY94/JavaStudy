package day019;

import java.util.ArrayList;
import java.util.Scanner;

import model.ProductDAO;
import model.ProductDTO;

public class Test01 {

	public static void main(String[] args) {
		ProductDAO model = new ProductDAO();
		System.out.println("전체데이터 출력할래?");
		Scanner sc = new Scanner(System.in);
		String ans = sc.next();
		if (ans.equals("YES")) {
			ArrayList<ProductDTO> datas=model.selectAll(null);
			for(ProductDTO data:datas) {
				System.out.println(data);
			}
		}
		sc.close();
	}

}
