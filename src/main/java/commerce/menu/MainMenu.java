package commerce.menu;

import commerce.system.CommerceSystem;
import commerce.system.ScannerSystem;

public class MainMenu extends Menu {

    public MainMenu(MenuType menuType) {
        super(menuType,"");
    }


    @Override
    public void showInfoMessage() {
        System.out.println("[ " + this.menuType.getName() + " ]");
        String infoMessage = "1. 전자제품\n2. 의류\n3. 식품";
        if (inputCondition) {
            infoMessage += """
                        
                        4. 장바구니 확인\t| 장바구니를 확인 후 주문합니다.\
                        
                        5. 주문 취소\t| 진행중인 주문을 취소합니다.""";
        }
        infoMessage += "\n6. 관리자 모드\n7. 상품 검색 성능 테스트\n0. 프로그램 종료\n번호를 선택하세요: ";
        System.out.println(infoMessage);
    }
    @Override
    public void menuProgress()
    {

        if (inputCondition) {
            userInput = scannerSystem.getValidatedInput(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        } else {
            userInput = scannerSystem.getValidatedInput(new int[]{0, 1, 2, 3, 6, 7});
        }

        if (userInput == 0) {
            System.out.println("커머스 플랫폼을 종료합니다.");
        }
        CommerceSystem.stackMenu.push(userInput);
    }
}
