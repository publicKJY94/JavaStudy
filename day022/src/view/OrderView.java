package view;

import java.util.ArrayList;

import model.DTO.OrderContentDTO;

public class OrderView {
	
	// 사용자 구매내역 조회
	public void printDatas(ArrayList<OrderContentDTO> ocdatas) {
		ArrayList<Integer> oCheck = new ArrayList<Integer>();	// 주문번호 확인을 위해 생성
		System.out.println("=========주문 내역========");
        for(OrderContentDTO data : ocdatas) {
        	int orderId = data.getoId();	// 주문번호 저장
        	if(!oCheck.contains(orderId)) {	// 주문번호가 oCheck에 없다면 주문내역 출력 및 주문번호 add
        		System.out.println("=====주문번호 : "+ data.getoId()+"======");
                oCheck.add(orderId);
        	}
    		System.out.println("구매자 : "+ data.getMemberName());
            System.out.println("상품명 : "+ data.getProductName());
            System.out.println("상품 카테고리 : "+ data.getProductCategory());
            System.out.println("상품가격 : "+ data.getProductPrice());
            System.out.println("구매개수 : "+ data.getoContentCnt());
            System.out.println("구매일자 : "+ data.getOrderDate());
            System.out.println();
        }
    }
	
	// 관리자 페이지 상품별 총 판매량 및 매출액 출력
	public void printTotalSales(ArrayList<OrderContentDTO> ocdatas, OrderContentDTO ocDTO) {
		System.out.println("======상품별 매출정보=====");
        for(OrderContentDTO data : ocdatas) {
            System.out.println("상품번호 : "+ data.getpId());
            System.out.println("상품명 : "+ data.getProductName());
            System.out.println("상품가격 : "+ data.getProductPrice()+"원");
            System.out.println("판매개수 : "+ data.getoContentCnt()+"개");
            System.out.println("매출액 : "+ data.getTotalPrice()+"원");
            System.out.println("");
        }
        System.out.println("======총 매출액======");
        System.out.println("총 매출액 : "+ ocDTO.getTotalPrice()+"원");
        System.out.println("");
    }
}