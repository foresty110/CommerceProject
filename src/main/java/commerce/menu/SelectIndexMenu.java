package commerce.menu;

import commerce.system.CommerceSystem;

public class SelectIndexMenu extends Menu {
    private int inputMin; // 해당 메뉴에서 입력할 수 있는 최소 번호
    private int inputMax; // 해당 메뉴에서 입력할 수 있는 최대 번호

    public SelectIndexMenu(MenuType menuType,String infoMesssage, int inputMin, int inputMax) {
        super(menuType,infoMesssage);
        this.inputMin = inputMin;
        this.inputMax = inputMax;
    }
    @Override
    public void menuProgress() {
        userInput = scannerSystem.getValidatedInput(inputMin, inputMax);
        CommerceSystem.stackMenu.push(userInput);
    }
}
