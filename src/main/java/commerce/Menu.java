package commerce;

public class Menu {
    private MenuType menuType;
    private int menuNumber;
    private String name;
    private String infoMessage;
    Menu(MenuType menuType, String name,String infoMessage) {
        this.menuType = menuType;
        this.name = name;
        this.infoMessage = infoMessage;

        showInfoMessage();
    }

    public void showInfoMessage(){
        System.out.println("[ "+name+" ]");
        System.out.println(infoMessage);
    }
    public boolean isSelectCancle(int select) {

        switch (menuType) {
            case CANCEL_OR_CONFIRM:

                if (select == 2) {
                    System.out.println(this.name + "(을)를 취소합니다.");
                    return true;
                }
                break;
            case BACK:
                if (select == 0) {
                    System.out.println(this.name + "으로 돌아갑니다.");
                    return true;
                }
                break;
            case EXIT:
                if (select == 0) {
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    return true;
                }
                break;
        }

        return false;
    }
}
