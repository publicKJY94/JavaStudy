package model;

import java.util.ArrayList;

public class ProductDAO {
	private ArrayList<ProductDTO> pdatas;

	public ProductDAO() {
		pdatas = new ArrayList<ProductDTO>();
		ProductDTO sample01=new ProductDTO();
		sample01.setPid(1001);
		sample01.setName("콜라");
		sample01.setPrice(1100);
		sample01.setCnt(10);
		pdatas.add(sample01);

		ProductDTO sample02=new ProductDTO();
		sample02.setPid(1002);
		sample02.setName("사이다");
		sample02.setPrice(1200);
		sample02.setCnt(1);
		pdatas.add(sample02);

		ProductDTO sample03=new ProductDTO();
		sample03.setPid(1003);
		sample03.setName("보리차");
		sample03.setPrice(2200);
		sample03.setCnt(0);
		pdatas.add(sample03);
	}

	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO) {
		ArrayList<ProductDTO> searchList = new ArrayList<ProductDTO>();
		if (pDTO.getSearchCondition().equals("가격검색")) {
			for (ProductDTO data : pdatas) {
				if (data.getPrice() >= pDTO.getStartRange() && data.getPrice() <= pDTO.getEndRange()) {
					searchList.add(data);
				}
			}
			return searchList; 
		}
		return pdatas;
	}

	public ProductDTO selectOne(ProductDTO pDTO) {
		for (ProductDTO data : pdatas) {
			if (data.getPid() == pDTO.getPid()) {
				return data;
			}
		}
		return null;
	}

	public boolean insert(ProductDTO pDTO) {
		return false;
	}

	public boolean update(ProductDTO pDTO) {
		if (pDTO.getSearchCondition().equals("재고추가")) {
			pDTO.setCnt(pDTO.getCnt() + pDTO.getAddCnt());
			return true;
		}
		return false;
	}

	public boolean delete(ProductDTO pDTO) {
		return false;
	}
}
