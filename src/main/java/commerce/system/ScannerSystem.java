package commerce.system;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerSystem {

    static Scanner scanner = new Scanner(System.in);

    public Integer getValidatedInput(int min, int max) {

        int userInput;
        //입력 예외처리

        while (true) {
            try {
                userInput = scanner.nextInt();
                scanner.nextLine(); //줄바꿈 제거

                //입력값 유효성 검증
                if (userInput < min || userInput > max) {
                    System.out.print("잘못된 입력입니다. 다시 입력해 주세요: ");
                    continue;
                }
                break;

            } catch (InputMismatchException e) {
                System.out.print("숫자를 입력해 주세요 :");
                scanner.nextLine(); //줄바꿈 제거
                continue;
            }
        }

        return userInput;
    }

    public Integer getValidatedInput(int[] validNumbers) {

        int userInput;
        //입력 예외처리

        while (true) {
            try {
                userInput = scanner.nextInt();
                scanner.nextLine(); //줄바꿈 제거

                //입력값 유효성 검증
                boolean isValid = false;
                for (int i : validNumbers) {
                    if(i == userInput) {
                        isValid = true;
                        break;
                    }
                }

                if (!isValid) {
                    System.out.print("잘못된 입력입니다. 다시 입력해 주세요: ");
                    continue;
                }
                break;

            } catch (InputMismatchException e) {
                System.out.print("숫자를 입력해 주세요 :");
                scanner.nextLine(); //줄바꿈 제거
                continue;
            }
        }

        return userInput;
    }

    public String getValidatedInput() {

        String userInput;

        //입력 예외처리
        while (true) {
            userInput = scanner.nextLine();

            //입력값 유효성 검증
            if (userInput.isEmpty()) {
                System.out.print("값이 입력되지 않았습니다. 다시 입력해 주세요: ");
                continue;
            }
            break;
        }

        return userInput;
    }
}
