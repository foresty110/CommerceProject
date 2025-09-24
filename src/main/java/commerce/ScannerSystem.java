package commerce;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerSystem {

    static Scanner scanner = new Scanner(System.in);

    public Integer validateInput(int min, int max) {

        int userInput;
        //입력 예외처리

        try {
            userInput = scanner.nextInt();
            scanner.nextLine(); //줄바꿈 제거
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해 주세요.");
            return null;
        } //런타임으로 예외던지기
        //재귀함수

        //입력값 유효성 검증
        if (userInput < min || userInput > max) {
            System.out.println("잘못된 입력입니다.");
            return null;
        }

        //validate - boolean으로 대부분은 응답
        //get in range
        return userInput;
    }
}
