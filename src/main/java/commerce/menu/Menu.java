package commerce.menu;

import commerce.system.ScannerSystem;

/**
 * Menu
 * -------------------------
 * 콘솔 기반 UI에서 메뉴 출력 및 사용자 입력을 처리하는 클래스입니다.
 * *
 * 주요 기능
 * - 메뉴 정보 출력
 * - 사용자 입력 검증 및 처리
 * - 메뉴 타입에 따른 실행 동작 처리
 * - 종료/뒤로가기/확인 등의 공통 동작 처리
 **/
public class Menu {
    private final MenuType menuType; // 메뉴 타입
    private final MenuActionType menuActionType; //
    private String infoMessage; // 메뉴 정보 메세지
    private int userInput; // 해당 메뉴 동작에서 사용자가 입력한 값
    private String userInputStr; // 해당 메뉴 동작에서 사용자가 입력한 값
    private int inputMin; // 해당 메뉴에서 입력할 수 있는 최소 번호
    private int inputMax; // 해당 메뉴에서 입력할 수 있는 최대 번호
    private boolean inputCondition; // 특정 상황에서 허용되는 메뉴 옵션 제어용

    public Menu(MenuType menuType, MenuActionType menuActionType) {
        this.menuType = menuType;
        this.menuActionType = menuActionType;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void showInfoMessage() {
        System.out.println("[ " + this.menuType.getName() + " ]");
        System.out.println(infoMessage);
    }

    public void setInfoMessage(String infoMessage) {
       this.infoMessage = infoMessage;
    }

    public void setInputRange(int min, int max) {
        inputMin = min;
        inputMax = max;
    }

    public String getUserInputStr() {
        return userInputStr;
    }

    public void setCondition(boolean condition) {
        inputCondition = condition;
    }

    public boolean menuProgress() {
        showInfoMessage();

        if (menuType == MenuType.MAIN) {
            return inputMenu(inputCondition);
        } else if (menuType == MenuType.MANAGEMENT_MODIFY_PRODUCT) {
            return inputMenu();
        }
        else{
            return inputMenu(inputMin, inputMax);
        }
    }

    public boolean inputMenu(boolean condition) {

        ScannerSystem scannerSystem = new ScannerSystem();

        if (condition) {
            userInput = scannerSystem.getValidatedInput(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        } else {
            userInput = scannerSystem.getValidatedInput(new int[]{0, 1, 2, 3, 6, 7});
        }

        //프로그램 종료
        return isSelectConfirm(userInput);
    }

    public boolean inputMenu(int min, int max) {

        ScannerSystem scannerSystem = new ScannerSystem();

        userInput = scannerSystem.getValidatedInput(min, max);

        //프로그램 종료
        return isSelectConfirm(userInput);
    }

    public boolean inputMenu(){
        ScannerSystem scannerSystem = new ScannerSystem();

        userInputStr = scannerSystem.getValidatedInput();

        return true;
    }

    public int getUserInput() {
        return userInput;
    }

    public boolean isSelectConfirm(int select) {

        switch (menuActionType) {
            case CANCEL_OR_CONFIRM:

                if (select == 2) {
                    System.out.println(this.menuType.name() + "(을)를 취소합니다.");
                    return false;
                }
                break;
            case BACK:
                if (select == 0) {
                    System.out.println(this.menuType.name() + "으로 돌아갑니다.");
                    return false;
                }
                break;
            case EXIT:
                if (select == 0) {
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    return false;
                }
                break;
            case SELECT:
                if (select == 0) {
                    System.out.println("돌아가기를 선택하였습니다.");
                    return false;
                }
                break;
        }

        return true;
    }
}
