package commerce.menu;

import commerce.system.CommerceSystem;
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
public abstract class Menu {

    protected final ScannerSystem scannerSystem = new ScannerSystem();

    protected MenuType menuType;
     private String infoMessage; // 메뉴 정보 메세지
    protected int userInput; // 해당 메뉴 동작에서 사용자가 입력한 값
    protected String userInputStr; // 해당 메뉴 동작에서 사용자가 입력한 값
    protected boolean inputCondition;
    protected int inputMin;
    protected int inputMax;

    protected String setValue;

    public Menu(MenuType menuType,String infoMessage) {
        this.menuType = menuType;
        this.infoMessage = infoMessage;
  }

      public MenuType getMenuType() {
            return menuType;
      }

    public void showInfoMessage() {
        System.out.println("[ " + menuType.getName() + " ]");
        System.out.println(infoMessage);
    }

    public void setInfoMessage(String infoMessage) {
       this.infoMessage = infoMessage;
    }

    public void setInputRange(int min, int max) {
        inputMin = min;
        inputMax = max;
    }

    public void setInputCondition(boolean inputCondition) {
      this.inputCondition = inputCondition;
    }

    public void endProgress(){

        CommerceSystem.userInput = userInput;
    }

    public String getUserInputStr() {
        return userInputStr;
    }

    public void progress(){
        showInfoMessage();
        menuProgress();
        endProgress();
    }
    public abstract void menuProgress() ;

    public void infoMessage(String infoMessage)
    {
        this.infoMessage = infoMessage;
    };


    public int getUserInput() {
        return userInput;
    }

}
