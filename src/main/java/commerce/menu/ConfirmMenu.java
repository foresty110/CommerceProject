package commerce.menu;

import commerce.system.ScannerSystem;

public class ConfirmMenu extends Menu{


    public ConfirmMenu(MenuType menuType, String message) {
        super(menuType,message);
    }

    @Override
    public void menuProgress() {

        if (userInput == 2) {
            System.out.println("취소합니다.");
        }

        userInput = scannerSystem.getValidatedInput(1, 2);
    }
}