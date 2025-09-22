package commerce;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 전자제품 데이터 저장
        List<Product> products = new ArrayList<>();
        Product p1 = new Product("Galaxy S25", 1200000, "Apple의 최신 스마트폰");
        Product p2 = new Product("iPhone 16", 1350000, "최신 안드로이드 스마트폰");
        Product p3 = new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북");
        Product p4 = new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰");

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);

        // 전자제품 목록 출력
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");

        int count = 1;
        for (Product p : products) {
            System.out.println(count++ + ". " + p );
        }

        System.out.println("0. 종료 \t\t\t | 프로그램 종료");

        // 사용자로부터 입력받기
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
