package class01;

import java.util.ArrayList;
import java.util.Scanner;

public class Test01 { // V + C

	public static void main(String[] args) {

		// JDBC
		
		ProductDAO model=new ProductDAO();
		
		System.out.println("전체 데이터 출력할래?");
		String ans=new Scanner(System.in).next();
		if(ans.equals("YES")) {
			ArrayList<ProductDTO> datas=model.selectAll(null);
			for(ProductDTO data:datas) {
				System.out.println(data);
			}
		}
				
	}

}
