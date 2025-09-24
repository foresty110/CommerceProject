package commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private Scanner scanner = new Scanner(System.in);
    private List<Category> categories = new ArrayList<>();
    private Cart cart = new Cart();
    private ScannerSystem scannerSystem = new ScannerSystem();

    private final String PASSWORD = "admin123";

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Category getCategory(int idx) {

        if (categories.size() < idx || idx < 1) {
            return null;
        }

        return categories.get(idx - 1);
    }

    public void start() {

        // 전자제품 데이터 저장
        List<Product> electronicProducts = new ArrayList<>();

        electronicProducts.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 30));
        electronicProducts.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 25));
        electronicProducts.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 6));
        electronicProducts.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 10));

        Category electronics = new Category(electronicProducts, "전자제품");

        //의류 데이터 저장
        List<Product> clothingProducts = new ArrayList<>();

        clothingProducts.add(new Product("오버핏 맨투맨", 55000, "편하게 입기 좋은 오버핏 맨투맨", 15));
        clothingProducts.add(new Product("와이드핏 청바지", 78000, "데일리룩으로 딱인 트렌디한 와이드핏 청바지", 40));
        clothingProducts.add(new Product("크롭 기장 자켓", 99900, "봄/가을에 걸치기 좋은 크롭 스타일 자켓", 3));
        clothingProducts.add(new Product("빈티지 가디건", 62000, "레트로 느낌의 따뜻한 니트 가디건", 21));

        Category clothing = new Category(clothingProducts, "의류");

        //식품 데이터 저장
        List<Product> foodProducts = new ArrayList<>();

        foodProducts.add(new Product("샤브샤브 밀키트", 18900, "집에서 간편하게 즐기는 샤브샤브 세트", 4));
        foodProducts.add(new Product("제스프리 키위", 8500, "새콤달콤한 맛이 일품인 싱싱한 키위", 8));
        foodProducts.add(new Product("락토프리 우유", 3000, "유당불내증 걱정 없이 마시는 우유", 2));
        foodProducts.add(new Product("통밀 식빵", 2650, "건강하고 담백한 맛의 통밀 식빵", 30));

        Category food = new Category(foodProducts, "식품");

        // 카테고리 정보 저장
        this.addCategory(electronics);
        this.addCategory(clothing);
        this.addCategory(food);

        //---------------------------------------------------------------------------------------------------

        // 사용자로부터 입력받기
        while (true) {

            // 카테고리 목록 출력
            String categoryInfoMessage = "";

            int count = 0;
            for (Category category : categories) {
                categoryInfoMessage += ++count + ". " + category.getName() + "\n";
            }
            if (cart.getCartItemAmount() > 0) {
                categoryInfoMessage += "4. 장바구니 확인\t| 장바구니를 확인 후 주문합니다.";
                categoryInfoMessage += "\n5. 주문 취소\t| 진행중인 주문을 취소합니다.";
            }
            categoryInfoMessage += "\n6. 관리자 모드\n0. 프로그램 종료\n번호를 선택하세요: ";

            Menu menu = new Menu(MenuType.EXIT, "커머스 플랫폼 메인", categoryInfoMessage);

            // 메뉴 번호 입력 받기
            int inputMenu = scannerSystem.getValidatedInput(0, 6);

            //프로그램 종료
            if (menu.isSelectCancle(inputMenu)) {
                continue;
            }

            if (inputMenu == 4 && cart.getCartItemAmount() > 0) {
                order();
                continue;
            } else if (inputMenu == 5 && cart.getCartItemAmount() > 0) {

            } else if (inputMenu == 6) {
                //  관리자 모드
                management();
                continue;
            }

            // 선택한 카테고리의 상품 데이터 출력
            String productInfoMessage = getCategory(inputMenu).showProductsInfo() +
                    "0. 뒤로가기" +
                    "\n번호를 선택하세요: ";
            Menu categoryMenu = new Menu(MenuType.BACK, "카테고리 선택", productInfoMessage);

            // 상품 선택 입력받기
            int pickProduct = scannerSystem.getValidatedInput(0, getCategory(inputMenu).getProductsSize());
            if (categoryMenu.isSelectCancle(pickProduct)) {
                continue;
            }

            // 상품 상세 정보 출력하기
            Product product = getCategory(inputMenu).getProducts(pickProduct);
            System.out.println("선택한 상품: " + product.toStringDetail());

            // 장바구니 -----------------------------------------------------------------------------
            String insertCart = "위 상품을 장바구니에 추가하시겠습니까?\n1.확인 \t 2.취소";
            Menu cartMenu = new Menu(MenuType.CANCEL_OR_CONFIRM, "장바구니 추가", insertCart);

            //장바구니 사용 입력 받기
            int inputCartItem = scannerSystem.getValidatedInput(1, 2);

            if (cartMenu.isSelectCancle(inputCartItem)) {
                continue;
            }

            //재고 관리 & 장바구니 추가
            if (!cart.canAddToCart(product)) {
                System.out.println("재고가 없습니다.");
                continue;
            }

            cart.addItem(new CartItem(product));
            System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");


        }

    }

    public void order() {

        //  장바구니 목록 출력
        cart.showCartItems();

        //총 주문 금액
        String infoTotal = cart.getTotalPrice() + "원 \n1. 주문 확정 2. 취소";
        Menu menu = new Menu(MenuType.CANCEL_OR_CONFIRM, "총 주문 금액", infoTotal);

        int input = scannerSystem.getValidatedInput(1, 2);

        if (menu.isSelectCancle(input)) {
            return;
        }

        if (input == 1) {
            System.out.println("주문이 완료되었습니다 !총 금액: " + cart.getTotalPrice() + "원");
            cart.purchase();
        }

    }

    public boolean management() {

        //비밀번호 인증
        int accessCount = 3;
        while (accessCount-- >= 0) {

            if (accessCount == -1) {
                System.out.println("비밀번호 3회 입력 실패. 메인으로 돌아갑니다.");
                return false;
            }

            // 비밀번호 입력받기
            System.out.print("비밀번호를 입력하세요: ");
            String inputPassword = scannerSystem.getValidatedInput();

            if (PASSWORD.equals(inputPassword)) {
                break;
            } else {
                System.out.println("비밀번호가 다릅니다.");
            }
        }

        //관리자 모드 선택

        System.out.println("[ 관리자 모드 ]");
        System.out.println("1. 상품 추가" +
                "\n2. 상품 수정" +
                "\n3. 상품 삭제" +
                "\n4. 전체 상품 현황" +
                "\n0. 메인으로 돌아가기");

        //관리자 모드 번호 입력
        System.out.println("관리자 모드 번호를 입력하세요: ");
        int userManageMenu = scannerSystem.getValidatedInput(0, 4);

        // 관리자 모드 메뉴 실행

        switch (userManageMenu) {
            case 1:
                //상품 추가
                if (!addProduct())
                    return false;
                break;
            case 2:
                modifyProduct();
                break;
            case 3:
                /*
                - [ ]  **상품 삭제 기능**
                    - [ ]  기존 상품을 카테고리에서 제거
                - [ ]  삭제 전 확인 메시지 출력
                    - [ ]  삭제된 상품이 장바구니에 있다면 장바구니에서도 제거*/
                break;
            default:
                System.out.println("잘못된 메뉴 입력입니다.");
                return false;
        }


        return true;
    }

    public boolean addProduct() {
        //카테고리 선택
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        System.out.println("1. 전자제품");
        System.out.println("2. 의류");
        System.out.println("3. 식품");

        //카테고리 번호 입력

        System.out.println("카테고리 번호를 입력하세요: ");
        int inputCategory = scannerSystem.getValidatedInput(1, categories.size());

        System.out.println("/" + inputCategory + "/");
        String categoryName = getCategory(inputCategory).getName();
        System.out.println("[ " + categoryName + " 카테고리에 상품 추가 ]");

        // 상품 정보 입력받기

        System.out.print("이름을 입력하세요: ");
        String inputName = scannerSystem.getValidatedInput();

        System.out.println("가격을 입력하세요: ");
        int inputPrice = scanner.nextInt();

        System.out.println("상품 설명을 입력하세요: ");
        String inputDescription = scannerSystem.getValidatedInput();

        System.out.print("재고 수량을 입력하세요: ");
        int inputStock = scanner.nextInt();

        Product product = new Product(inputName, inputPrice, inputDescription, inputStock);

        //상품 정보 추가 여부 입력받기
        String infoAddProduct = product.toStringDetail()+"\n"+"위 정보로 상품을 추가하시겠습니까?\n1.확인\t2.취소";
        Menu menu = new Menu(MenuType.CANCEL_OR_CONFIRM, "상품 추가",infoAddProduct );

        int userChoice = scannerSystem.getValidatedInput(1, 2);
        if (menu.isSelectCancle(userChoice)) {
            return false;
        }

        if (userChoice == 1) {
            // 카테고리에 추가하기
            if (!getCategory(inputCategory).addProduct(product)) {
                System.out.println("동일한 상품이 존재합니다. 상품 등록이 취소되었습니다.");
                return false;
            }
            System.out.println("상품 등록 성공!");
        }

        return true;
    }

    public boolean modifyProduct() {

        //카테고리 선택
        System.out.println("어느 카테고리 상품을 수정하시겠습니까?");
        System.out.println("1. 전자제품");
        System.out.println("2. 의류");
        System.out.println("3. 식품");

        // 카테고리 번호 입력
        System.out.println("카테고리 번호를 입력하세요: ");
        int pickCategory = scannerSystem.getValidatedInput(1, categories.size());

        // 상품 목록 출력
        Category productList = getCategory(pickCategory);
        System.out.println(productList.showProductsInfo());

        //수정할 상품 번호 입력
        System.out.println("수정할 항목을 선택하세요.");
        int inputProduct = scannerSystem.getValidatedInput(1, productList.getProductsSize());

        // 수정할 항목 선택 입력받기
        Product oldProduct = productList.getProducts(inputProduct);
        System.out.println("현재 상품 정보: " + oldProduct.toStringDetail());
        System.out.println(" 수정할 항목을 선택해주세요:");
        System.out.println("1. 가격\n2. 설명\n3. 재고수량");

        // 수정할 상품 정보 입력받기
        int userEditIdx = scannerSystem.getValidatedInput(1, 3);

        //가격 정보 수정
        if (userEditIdx == 1) {

            //변경 전 가격
            int oldPrice = oldProduct.getPrice();

            System.out.println("현재 가격: " + oldPrice);
            System.out.print("새로운 가격을 입력하세요: ");

            int inputPrice = scannerSystem.getValidatedInput(0, Integer.MAX_VALUE);

            // 수정하기
            oldProduct.setPrice(inputPrice);
            System.out.println(oldProduct.getName() + "의 가격이 " + oldPrice + "원 -> " + oldProduct.getPrice() + "으로 수정되었습니다.");

            // 상품 설명 수정
        } else if (userEditIdx == 2) {

            //변경 전 설명
            String oldDesc = oldProduct.getDescription();

            System.out.println("현재 설명: " + oldDesc);
            System.out.print("새로운 설명을 입력하세요: ");
            String inputDescription = scannerSystem.getValidatedInput();

            //수정하기
            oldProduct.setDescription(inputDescription);
            System.out.println(oldProduct.getName() + "의 설명이 [" + oldDesc + " ] -> [ " + oldProduct.getDescription() + " ]으로 수정되었습니다.");

            // 재고 수량 수정
        } else if (userEditIdx == 3) {

            //변경 전 재고량
            int oldStock = oldProduct.getStockQuantity();

            System.out.println("현재 재고: " + oldStock);
            System.out.print("새로운 재고량을 입력하세요: ");

            int inputStock = scannerSystem.getValidatedInput(0, Integer.MAX_VALUE);

            //수정하기
            oldProduct.setStockQuantity(inputStock);
            System.out.println(oldProduct.getName() + "의 재고가 " + oldStock + "개 -> " + oldProduct.getStockQuantity() + "으로 수정되었습니다.");
        }

        return true;
    }


}


