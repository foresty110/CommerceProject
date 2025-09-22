package commerce;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]" +
                "\n1. Galaxy S25     | 1,200,000원 | 최신 안드로이드 스마트폰" +
                "\n2. iPhone 16      | 1,350,000원 | Apple의 최신 스마트폰" +
                "\n3. MacBook Pro    | 2,400,000원 | M3 칩셋이 탑재된 노트북" +
                "\n4. AirPods Pro    |   350,000원 | 노이즈 캔슬링 무선 이어폰" +
                "\n0. 종료            | 프로그램 종료"
        );

        Scanner scanner = new Scanner(System.in);

        try {
            int menu = scanner.nextInt();
            if (menu == 0) {
                System.out.println(" 커머스 플랫폼을 종료합니다.");
            } else if (menu > 4) {
                System.out.println("1~4 숫자를 입력해주세요");
            } else {
                System.out.println("전자제품 선택 완료");
            }
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요");
        }
    }
}
