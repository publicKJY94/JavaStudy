package classTest;

import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int[] ar = new int[3];
		for (int i = 0; i < ar.length; i++) {
			ar[i] = sc.nextInt();
		}
		Arrays.sort(ar);
		System.out.println(Arrays.toString(ar));
	}
}

//		String a =sc.next();
//		int b = sc.nextInt();
//		int c = sc.nextInt();
//		String result=""+a;
//		result = b<10 ? result+0+b : result+b;
//		result = c<10 ? result+"00"+c : c<100 ? result+"0"+c : result+c ;
//		System.out.println(result);
//	}}
//		int[] datas = new int[3];
//		for(int i=0; i<datas.length; i++) {
//			datas[i]= random.nextInt(10)+1;
//		}
//		System.out.println(Arrays.toString(datas));
//	}}
//		int old = sc.nextInt();
//		int year = 2012-old+1;
//		if(year>=2000) {
//			System.out.println(year%100 + " " + 3);
//		}else {
//			System.out.println(year%100 + " " + 1);
//		}
//	}}
//		int year = sc.nextInt()/10000;
//		int gender = sc.nextInt();
//		System.out.println(year);
//		switch (gender) {
//		case 1, 2: {
//			year+=1900;
//			break;
//		}
//		case 3, 4:{
//			year+=2000;
//			break;
//		}}
//		System.out.println(2012-year+1);// 만 나이 보정
//	}}

//		int[] ar = new int[3];
//		int result;
//		
//		for(int i=0;i<3;i++) {
//			ar[i] = sc.nextInt();
//		}
//		Arrays.sort(ar); // 사용시 정렬이 되어 저장됨
//		System.out.println(Arrays.toString(ar));
//		System.out.println(ar[1]);
//	}
//
//}
