import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) {
		// BufferedReader, BufferedWriter를 사용할 것
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 테스트케이스는 한줄에 띄어쓰기로 입력된다 // 띄어쓰기 된 값을 숫자로 받아오는 방법이 있는가?
		String[] ar = {};
		// 테스트케이스로 입력된 값의 합을 출력한다 // 합을 구한다는것은 정수형의 계산
		try {
			// 테스트케이스의 개수 T // int
			int T =Integer.parseInt(br.readLine()); // 
			System.out.println("T는 : " + T);
			System.out.println(T);
			ar = new String[T];
			for(int i=0; i<T; i++) {
				// 테스트 케이스를 한줄의 문자열로 읽어옴
				ar[i] = br.readLine();
				// 연산해야하는 값이 2개이기 때문에 하드코딩으로 작성 가능
				ar[i] = ""+(Integer.parseInt(ar[i].split(" ")[0])+Integer.parseInt(ar[i].split(" ")[1]));
				System.out.println(ar[i]);
				// 두개 이상의 값을 연산하는 경우를 고려해본 코드...
//				String[] temp = ar[i].split(" ");
//				int result = 0;
//				for(int j = 0; j<temp.length; j++) {
//					result+=Integer.parseInt(temp[j]);
//				}
//				System.out.println(result);
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
