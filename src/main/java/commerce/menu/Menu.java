package commerce.menu;

import commerce.system.ScannerSystem;

public class Menu {
    private MenuType menuType;
    private MenuActionType menuActionType;
    private int menuNumber;
    private String name;
    private String infoMessage;
    private int userInput;
    private String userInputStr;
    private int inputMin;
    private int inputMax;
    private boolean inputCondition;


    Menu(MenuType menuType, MenuActionType menuActionType) {
        this.menuType = menuType;
        this.menuActionType = menuActionType;
        this.name = menuType.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void showInfoMessage() {
        System.out.println("[ " + name + " ]");
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
            userInput = scannerSystem.getValidatedInput(new int[]{0, 1, 2, 3, 4, 5, 6});
        } else {
            userInput = scannerSystem.getValidatedInput(new int[]{0, 1, 2, 3, 6});
        }

        //프로그램 종료
        if (!isSelectConfirm(userInput)) {
            return false;
        }

        return true;
    }

    public boolean inputMenu(int min, int max) {

        ScannerSystem scannerSystem = new ScannerSystem();

        userInput = scannerSystem.getValidatedInput(min, max);

        //프로그램 종료
        if (!isSelectConfirm(userInput)) {
            return false;
        }

        return true;
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
                    System.out.println(this.name + "(을)를 취소합니다.");
                    return false;
                }
                break;
            case BACK:
                if (select == 0) {
                    System.out.println(this.name + "으로 돌아갑니다.");
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
