package classTest;

import java.util.Arrays;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Random random = new Random();
		int[] datas= new int[5];
		for(int i=0; i<datas.length;i++) {
			datas[i]=random.nextInt(10)+1;
		}
		System.out.println(Arrays.toString(datas));
		
		int key=2;
		int keyIndex;
		
		for(int i=0; i<datas.length; i++) {
			if(key==datas[i]) {
				keyIndex=i;
				System.out.println("찾았다");
				break;
			}
		}
//		int[] product = {500, 700, 1200, 950};
//		int total=0;
//		
//		for(int i = 0; i<product.length; i++) {
//			System.out.printf("%d번 상품은 %d원 입니다\n",i+1,product[i]);
//		}
//		for(int i =0; i <4; i++) {
//			total+=product[i];
//		}
//		//for-each문 [향상된 for문]
//		//for(자료형 임의의 변수명: 배열명){}
//		for(int price:product) {
//			total+=price;
//		}
//		System.out.printf("\n1~4번 상품의 총 합 가격은 %d원 입니다",total);
//		Random rd = new Random();
//		int[] datas = new int[5];
//		for(int i=0; i<datas.length; i++) {
//			datas[i] = rd.nextInt(45);
//		}
//		System.out.println(Arrays.toString(datas));

//		int[] datas = {10, 2, 5, -3,  1};
//		int minindex = 0;
//		
//		for(int i=1; i<datas.length; i++) {
//			if(datas[i] < datas[minindex]) {
//				minindex = i;
//			}
//		}
//		System.out.printf("최소값은 %d번 index에 있는 %d입니다",minindex ,datas[minindex] );
//		DecimalFormat decimalFormat = new DecimalFormat("0.00");
//
//		int count = 0;
//		int total = 0;
//		int[] datas = new int[5];
//
//		for (int i = 0; i < datas.length; i++) {
//			datas[i] = random.nextInt(6) + 1;
//		}
//		for (int i = 0; i < datas.length; i++) {
//			count = datas[i] == 2 ? count ++ : count;
//		}
//		for (int i = 0; i < datas.length; i++) {
//			total += datas[i];
//		}
//		System.out.println(Arrays.toString(datas));
//		System.out.printf("2는 %d번 나왔습니다\n", count);
//		System.out.printf("총합은 %d입니다\n", total);
//		System.out.printf("평균은 %.2f입니다", total * 1.0 / datas.length);
	}
}
