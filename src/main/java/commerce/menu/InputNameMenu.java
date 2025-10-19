package commerce.menu;

public class InputNameMenu extends Menu {
    public InputNameMenu(MenuType menuType, String message) {
        super(menuType,message);
    }

    @Override
    public void menuProgress(){
        userInputStr = scannerSystem.getValidatedInput();
    }
}
