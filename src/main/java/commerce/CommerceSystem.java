package commerce;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private List<Product> products;

    public CommerceSystem(List<Product> products){
        this.products = products;
    }

    public void start() {

        // 전자제품 목록 출력
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");

        int count = 1;
        for (Product p : products) {
            System.out.println(count++ + ". " + p);
        }

        System.out.println("0. 종료 \t\t\t | 프로그램 종료");
        System.out.println("번호를 선택하세요: ");

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

