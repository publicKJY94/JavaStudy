//부모클래스 VIEW
// 모든 view파일에서 공통적으로 사용하는 자원인 Scanner의 초기화를 진행
package view;

import java.util.Scanner;

public class VIEW {
    public static Scanner sc;
    public VIEW() {
        sc = new Scanner(System.in);
    }
}