package commerce.system;

import commerce.Constants;
import commerce.cart.Cart;
import commerce.cart.CartItem;
import commerce.category.Category;
import commerce.category.CategoryType;
import commerce.category.Product;
import commerce.customer.Customer;
import commerce.customer.CustomerGrade;
import commerce.menu.*;
import commerce.admin.AdminSystemType;
import commerce.test.PerformanceTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * CommerceSystem
 * -------------------------
 * 커머스 시스템의 핵심 컨트롤러 클래스입니다.
 * - 사용자 모드(상품 조회, 필터, 장바구니, 주문)와
 * - 관리자 모드(상품 추가/수정/삭제)를 모두 관리한다.
 * *
 * 주요 기능
 * - 카테고리, 상품, 메뉴 데이터 생성 및 관리
 * - 사용자 입력을 기반으로 한 상호작용 흐름 제어
 * - 사용자 모드(상품 조회, 필터, 장바구니, 주문)
 * - 관리자 모드(상품 추가/수정/삭제)
 **/
public class CommerceSystem {

    private final ScannerSystem scannerSystem; //사용자 입력 관리
    private final PerformanceTest performanceTest; //상품 검색 성능 테스트

    private final List<Menu> menuList = new ArrayList<>(); //메뉴 목록 관리
    private final List<Category> categories = new ArrayList<>(); //카테고리 목록 관리

    private final Cart cart; //장바구니
    private final Customer customer; //테스트용 고객 정보

    public static int userInput = -1;// 유저 입력값 저장
    public static int categoryInput = -1; //직전 유저 입력값 저장
    public static Stack<Integer> stackMenu = new Stack<>();

    public CommerceSystem() {
        this.cart = new Cart();
        this.customer = new Customer("김자바", "java123@gamil.com", CustomerGrade.PLATINUM);
        this.scannerSystem = new ScannerSystem();
        this.performanceTest = new PerformanceTest();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Category getCategory(int idx) {

        if (categories.size() < idx || idx < 1) {
            return null;
        }
        return categories.get(idx - 1);
    }

    public String toStringCategories() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Category category : categories) {
            result.append(++count).append(".").append(category.getName()).append("\n");
        }
        return result.toString();
    }

    public int getTotalPrice() {

        int sum = 0;
        for (CartItem item : cart.getCartItems()) {
            int price = item.getProduct().getPrice();
            sum += price * item.getQuantity();
        }

        //고객 등급에 맞는 할인율 적용
        double discountRate = (100 - customer.getCustomerGrade().getValue()) / 100.0;
        sum *= discountRate;
        return sum;
    }

    public void createData() {
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

    }

    public void menuCreate() {

        //메인 메뉴
        addMenu(new MainMenu(MenuType.MAIN));

        //상품목록 보기
        addMenu(new SelectIndexMenu(MenuType.SHOW_PRODUCT, "??", 0, categories.size()));

        // 상품 필터 목록 보기
        StringBuilder infoMessage = new StringBuilder();
        infoMessage.append("1.전체 상품 보기\n");
        infoMessage.append("2.가격대별 필터링(100만원 이하)\n");
        infoMessage.append("3.가격대별 필터링(100만원 초과)\n");
        infoMessage.append(" 0. 뒤로가기\n");
        infoMessage.append("번호를 선택하세요:");
        addMenu(new SelectIndexMenu(MenuType.SHOW_FILTER, infoMessage.toString(), 0, 3));

        // 장바구니 추가 확정
        addMenu(new ConfirmMenu(MenuType.ADDCART_CONFIM, "??"));

        //카테고리 - 상품 제거
        String categoryMessage = "제거하시겠습니까?\n1.확인 2.취소";
        addMenu(new ConfirmMenu(MenuType.CATEGORY_DELETE, categoryMessage));

        //주문 취소
        addMenu(new ConfirmMenu(MenuType.CANCLE_ORDER, "??"));

        //주문 확인
        addMenu(new ConfirmMenu(MenuType.CONFIRM_ORDER, "??"));

        //메뉴 추가 - 관리자 모드

        addMenu(new SelectIndexMenu(MenuType.MANAGEMENT_MAIN,
                """
                        1. 상품 추가
                        2. 상품 수정
                        3. 상품 삭제
                        0. 메인으로 돌아가기
                        관리자 모드 번호를 입력하세요:
                        """,
                0, 3));

        //------------------------------------------------------

        // 관리자 모드 - 카테고리 상품 추가
        addMenu(new SelectIndexMenu(MenuType.MANAGEMENT_ADD_CATEGORY,
                """
                        어느 카테고리에 상품을 추가하시겠습니까?
                        1. 전자제품
                        2. 의류
                        3. 식품
                        카테고리 번호를 입력하세요:
                        """
                , 1, 3));

        // --------------------------

        // 관리자 모드 - 상품 추가 상세 정보 입력받기
        addMenu(new ConfirmMenu(MenuType.MANAGEMENT_ADD_CONFIRM, "??"));

        // 카테고리 선택 메뉴 생성
        addMenu(new SelectIndexMenu(MenuType.MANAGEMENT_DELETE,
                "카테고리를 선택하세요\n" + toStringCategories() + "번호를 입력하세요: "
                , 1, categories.size()));

        // 관리자 모드 - 삭제할 상품 선택
        addMenu(new SelectIndexMenu(MenuType.MANAGEMENT_DELETE_SELECT_PRODUCT,
                "", -1, -1));


        // 관리자 모드의 제거 확인 메뉴 생성
        String messageDelete = "제거하시겠습니까?\n1.확인 2.취소";
        addMenu(new ConfirmMenu(MenuType.MANAGEMENT_DELETE_CONFIM, messageDelete));

        // 메뉴 추가 - 관리자 모드/ 수정할 카테고리 선택
        addMenu(new SelectIndexMenu(MenuType.MANAGEMENT_MODIFY_CATEGORY,
                """
                        어느 카테고리 상품을 수정하시겠습니까?
                        1. 전자제품
                        2. 의류
                        3. 식품
                        카테고리 번호를 입력하세요:\s"""
                , 1, 3));

        // 메뉴 추가 - 관리자모드/ 수정할 상품 입력
        addMenu(new InputNameMenu(MenuType.MANAGEMENT_MODIFY_PRODUCT, ""));

        //메뉴 추가 - 관리자모드/ 수정할 상품의 항목 선택
        addMenu(new SelectIndexMenu(MenuType.MANAGEMENT_MODIFY_INFO, "수정할 상품의 항목을 선택하세요.", 1, 3));

    }

    public void start() {

        // 상품 데이터 저장
        createData();
        //메뉴 생성
        menuCreate();

        //메인 메뉴 선택 단계
        Menu selectMenu = getMenuByMenuType(MenuType.MAIN);

        while (true) {
            selectMenu.setInputCondition(cart.getCartItemAmount() > 0);
            selectMenu.progress();
            categoryInput = userInput;
            if (userInput == 0)
                break;
            mainMenu();
        }


    }

    public Menu getMenuByMenuType(MenuType menuType) {
        for (Menu menu : menuList) {
            if (menu.getMenuType().getName().equals(menuType.getName())) {
                return menu;
            }
        }

        throw new IllegalStateException("존재하지 않는 메뉴입니다.");
    }

    public boolean returnMenu(){
        if (userInput == -1 )
            return true;
        if (stackMenu.isEmpty()) {
            return false;
        }
        if (stackMenu.peek() == 0) {
            stackMenu.pop();
            if(!stackMenu.isEmpty()) {
                userInput = stackMenu.pop();
            }
            return false;
        }
        return true;
    }

    public void mainMenu() {

        if(!returnMenu())
            return;

        if (userInput == CategoryType.ELECTRONICS.getValue()
                || userInput == CategoryType.CLOTHING.getValue()
                || userInput == CategoryType.FOOD.getValue()) {
            //카테고리 필터 선택 단계
            Menu menu = getMenuByMenuType(MenuType.SHOW_FILTER);
            menu.progress();
            selectProduct();
        } else if (userInput == 4) {
            order();
        } else if (userInput == 5) {
            orderCancle();
        } else if (userInput == 6) {//  관리자 모드
            accessPassword();
            manageMain();
        } else if (userInput == 7) {
            performanceTest();
        }

        mainMenu();

    }

    private boolean performanceTest() {

        System.out.println("[검색 성능 비교 테스트]");

        //대용량 데이터 생성
        performanceTest.createData();

        //찾을 상품명 입력받기
        System.out.print("검색어: ");
        String userInput = scannerSystem.getValidatedInput();

        //상품 찾기
        performanceTest.compareSearchPerformance(userInput);
        return true;
    }

    public void selectProduct() {

        if(!returnMenu())
            return;

        // 상품 선택 단계
        Menu menu = getMenuByMenuType(MenuType.SHOW_PRODUCT);

        String productInfoMessage = "";
        int pickMax = 0;
        if (categoryInput == CategoryType.ELECTRONICS.getValue()) {
            productInfoMessage = getCategory(categoryInput).showProductsInfo();
            pickMax = getCategory(categoryInput).getProductsSize();
        } else if (categoryInput == CategoryType.CLOTHING.getValue()) {
            productInfoMessage = getCategory(categoryInput).showProductsInfoUnder();
            pickMax = getCategory(categoryInput).getProductsSizeUnder();
        } else if (categoryInput == CategoryType.FOOD.getValue()) {
            productInfoMessage = getCategory(categoryInput).showProductsInfoOver();
            pickMax = getCategory(categoryInput).getProductsSizeOver();
        }
        productInfoMessage += "\n0. 뒤로가기 );\n번호를 선택하세요: ";
        menu.setInfoMessage(productInfoMessage);
        menu.setInputRange(0, pickMax);

        menu.progress();

        //장바구니 추가 여부 선택
        selectAddCartStep();
    }

    public void showProduct() {

        int filter = getMenuByMenuType(MenuType.SHOW_FILTER).getUserInput();
        Menu menu = getMenuByMenuType(MenuType.SHOW_PRODUCT);

        String productInfoMessage = "";
        int pickMax = 0;
        if (filter == CategoryType.ELECTRONICS.getValue()) {
            productInfoMessage = getCategory(userInput).showProductsInfo();
            pickMax = getCategory(userInput).getProductsSize();
        } else if (filter == CategoryType.CLOTHING.getValue()) {
            productInfoMessage = getCategory(userInput).showProductsInfoUnder();
            pickMax = getCategory(userInput).getProductsSizeUnder();
        } else if (filter == CategoryType.FOOD.getValue()) {
            productInfoMessage = getCategory(userInput).showProductsInfoOver();
            pickMax = getCategory(userInput).getProductsSizeOver();
        }
        productInfoMessage += "\n0. 뒤로가기 );\n번호를 선택하세요: ";
        menu.setInfoMessage(productInfoMessage);
        menu.setInputRange(0, pickMax);

        menu.progress();
    }

    public void selectAddCartStep() {

        if(!returnMenu())
            return;

        int filter = getMenuByMenuType(MenuType.SHOW_FILTER).getUserInput();
        int userInput = getMenuByMenuType(MenuType.SHOW_PRODUCT).getUserInput();
        Category category = getCategory(userInput);

        // 상품 상세 정보 출력하기
        Product product = null;
        if (filter == CategoryType.ELECTRONICS.getValue()) {
            product = category.getProduct(userInput);
        } else if (filter == CategoryType.CLOTHING.getValue()) {
            product = category.getProduct(userInput, p -> p.getPrice() <= Constants.PRODUCT_FILTER_PRICE);
        } else if (filter == CategoryType.FOOD.getValue()) {
            product = category.getProduct(userInput, p -> p.getPrice() > Constants.PRODUCT_FILTER_PRICE);
        }

        String message = "선택한 상품: " +
                product.toStringDetail() +
                "\n위 상품을 장바구니에 추가하시겠습니까?" +
                "\n1.확인 \t 2.취소";

        Menu menu = getMenuByMenuType(MenuType.ADDCART_CONFIM);
        menu.setInfoMessage(message);
        menu.progress();

        //재고 관리 & 장바구니 추가
        if (!cart.canAddToCart(product)) {
            System.out.println("재고가 없습니다.");
            return;
        }

        //장바구니 추가하지 않음
        if(userInput == 2)
            return;

        cart.addItem(new CartItem(product));
        System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
    }

    public void addMenu(Menu menu) {
        this.menuList.add(menu);
    }

    public void order() {

        //  장바구니 목록 출력
        System.out.println(cart.showCartItems());

        //총 주문 금액
        String infoTotal = "고객님의 등급은 " + customer.getCustomerGrade().toString() +
                " 으로 할인이 " + customer.getCustomerGrade().getValue() + "% 적용되었습니다." +
                "\n총 금액은" + getTotalPrice() + "원 입니다." +
                "\n1. 주문 확정 2. 취소";
        Menu menu = getMenuByMenuType(MenuType.CONFIRM_ORDER);
        menu.setInfoMessage(infoTotal);

        // 주문 취소 처리
        menu.progress();

        System.out.println("주문이 완료되었습니다 !총 금액: " + getTotalPrice() + "원");
        cart.purchase();

    }

    public boolean orderCancle() {

        Menu menu = getMenuByMenuType(MenuType.CANCLE_ORDER);

        String message = cart.showCartItems() +
                "\n진행 중인 주문을 취소하시겠습니까?" +
                "\n1.주문 취소 2.돌아가기";
        menu.setInfoMessage(message);

        menu.progress();

        cart.clearCart();
        System.out.println("주문 취소 성공. 장바구니에 담긴 상품이 모두 삭제되었습니다.");
        return true;
    }

    public boolean accessPassword() {

        //비밀번호 인증
        int accessCount = Constants.PASSWORD_ATTEMPT_LIMIT;
        while (accessCount-- >= 0) {

            if (accessCount == -1) {
                System.out.println("비밀번호 3회 입력 실패. 메인으로 돌아갑니다.");
                return false;
            }

            // 비밀번호 입력받기
            System.out.print("비밀번호를 입력하세요: ");
            String inputPassword = scannerSystem.getValidatedInput();

            if (Constants.PASSWORD.equals(inputPassword)) {
                break;
            } else {
                System.out.println("비밀번호가 다릅니다.");
            }
        }

        return true;
    }

    public void manageMain() {

        while (true) {

            //관리자 모드 선택
            Menu menu = getMenuByMenuType(MenuType.MANAGEMENT_MAIN);
            menu.progress();

            userInput = menu.getUserInput();
            AdminSystemType adminSystemType = AdminSystemType.fromValue(userInput);

            // 관리자 모드 메뉴 실행
            switch (adminSystemType) {
                case ADD_Product:
                    if (!addProduct())
                        continue;
                    break;
                //관리자 모드 - 상품 수정
                case MODIFY_PRODUCT:
                    if (!manageModify())
                        continue;
                    break;
                //관리자 모드 - 상품 삭제
                case DELETE_PRODUCT:
                    if (!manageDelete()) {
                        continue;
                    }
            }


        }
    }

    private boolean addProduct() {

        List<Menu> addMenuList = new ArrayList<>();

        //상품 추가
        Menu menu = getMenuByMenuType(MenuType.MANAGEMENT_ADD_CATEGORY);
        addMenuList.add(menu);

        //추가할 상품 정보 입력
        menu = getMenuByMenuType(MenuType.MANAGEMENT_ADD_CONFIRM);
        Product product = requestAddProductInfo();
        String message = product.toStringDetail() + "\n" + "위 정보로 상품을 추가하시겠습니까?\n1.확인\t2.취소";
        menu.setInfoMessage(message);
        addMenuList.add(menu);

        boolean progress = true;
        for (Menu modifyMenu : addMenuList) {

            menu.progress();

            if (modifyMenu.getMenuType() == MenuType.MANAGEMENT_ADD_CONFIRM) {
                {
                    if (!getCategory(userInput).addProduct(product)) {
                        System.out.println("동일한 상품이 존재합니다. 상품 등록이 취소되었습니다.");
                        progress = false;
                    } else {
                        System.out.println("상품 등록 성공!");
                    }
                }
                modifyMenu.endProgress();
            }

        }

        return progress;
    }

    public Product requestAddProductInfo() {
        // 상품 정보 입력받기

        System.out.print("이름을 입력하세요: ");
        String inputName = scannerSystem.getValidatedInput();

        System.out.println("가격을 입력하세요: ");
        int inputPrice = scannerSystem.getValidatedInput(0, Integer.MAX_VALUE);

        System.out.println("상품 설명을 입력하세요: ");
        String inputDescription = scannerSystem.getValidatedInput();

        System.out.print("재고 수량을 입력하세요: ");
        int inputStock = scannerSystem.getValidatedInput(0, Integer.MAX_VALUE);

        Product product = new Product(inputName, inputPrice, inputDescription, inputStock);

        return product;

    }

    public boolean manageModify() {

        List<Menu> modifyMenuList = new ArrayList<>();

        Menu menu = getMenuByMenuType(MenuType.MANAGEMENT_MODIFY_CATEGORY);
        menu.setInputRange(1, categories.size());
        modifyMenuList.add(menu);

        //MANAGEMENT_MODIFY_PRODUCT
        menu = getMenuByMenuType(MenuType.MANAGEMENT_MODIFY_PRODUCT);
        Category category = getCategory(userInput);
        menu.setInfoMessage(category.showProductsInfo() + "수정할 상품명을 입력해주세요: ");

        // MANAGEMENT_MODIFY_INFO
        menu = getMenuByMenuType(MenuType.MANAGEMENT_MODIFY_INFO);
        String pickProduct = getMenuByMenuType(MenuType.MANAGEMENT_MODIFY_PRODUCT).getUserInputStr();
        Category productList = getCategory(userInput);
        Product oldProduct = productList.getProduct(pickProduct);

        menu.setInfoMessage("현재 상품 정보: " +
                oldProduct.toStringDetail() +
                "\n1. 가격" +
                "\n2. 설명" +
                "\n3. 재고수량" +
                "\n수정할 항목을 선택해주세요:");


        boolean progress = true;
        for (Menu modifyMenu : modifyMenuList) {

            if (modifyMenu.getMenuType() == MenuType.MANAGEMENT_MODIFY_PRODUCT) {
                //존재하지 않는 상품명을 입력했다면
                if (category.getProduct(menu.getUserInputStr()) == null) {
                    System.out.println("잘못된 상품명 입력입니다.");
                    progress = false;
                    break;
                }
            }

            modifyMenu.progress();

            if (modifyMenu.getMenuType() == MenuType.MANAGEMENT_MODIFY_INFO) {
                inputModifyInfo(oldProduct);
            }

        }

        return progress;
    }

    public boolean inputModifyInfo(Product oldProduct) {

        //가격 정보 수정
        if (userInput == 1) {

            //변경 전 가격
            int oldPrice = oldProduct.getPrice();

            System.out.println("현재 가격: " + oldPrice);
            System.out.print("새로운 가격을 입력하세요: ");

            int inputPrice = scannerSystem.getValidatedInput(0, Integer.MAX_VALUE);

            // 수정하기
            oldProduct.setPrice(inputPrice);
            System.out.println(oldProduct.getName() + "의 가격이 " + oldPrice + "원 -> " + oldProduct.getPrice() + "으로 수정되었습니다.");


        } // 상품 설명 수정
        else if (userInput == 2) {

            //변경 전 설명
            String oldDesc = oldProduct.getDescription();

            System.out.println("현재 설명: " + oldDesc);
            System.out.print("새로운 설명을 입력하세요: ");
            String inputDescription = scannerSystem.getValidatedInput();

            //수정하기
            oldProduct.setDescription(inputDescription);
            System.out.println(oldProduct.getName() + "의 설명이 [" + oldDesc + " ] -> [ " + oldProduct.getDescription() + " ]으로 수정되었습니다.");


        } // 재고 수량 수정
        else if (userInput == 3) {

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

    public boolean manageDelete() {

        // 제거할 카테고리 선택
        List<Menu> deleteProduct = new ArrayList<>();
        Menu menu = getMenuByMenuType(MenuType.MANAGEMENT_DELETE);
        menu.setInputRange(1, categories.size());
        deleteProduct.add(menu);


        // 제거할 상품 선택 -------------------------
        menu = getMenuByMenuType(MenuType.MANAGEMENT_DELETE_SELECT_PRODUCT);

        // 상품 선택 메뉴 가져오기
        String messageProduct = getCategory(userInput).showProductsInfo() + "상품을선택하세요: ";
        menu.setInfoMessage(messageProduct);
        menu.setInputRange(1, getCategory(userInput).getProductsSize());
        deleteProduct.add(menu);

        // 상품 제거 확인 입력받기
        menu = getMenuByMenuType(MenuType.MANAGEMENT_DELETE_CONFIM);
        deleteProduct.add(menu);


        boolean progress = true;
        for (Menu deleteMenu : deleteProduct) {

            if (deleteMenu.getMenuType() == MenuType.MANAGEMENT_DELETE_SELECT_PRODUCT) {
                if (getCategory(userInput).getProductsSize() < 1) {
                    System.out.println("제거할 상품이 없습니다.");
                    progress = false;
                    break;
                }
            }

            deleteMenu.progress();

            // 상품 제거 실행하기
            if (deleteMenu.getMenuType() == MenuType.MANAGEMENT_DELETE_CONFIM) {
                progress = manageDeleteProgress();
            }
        }

        return progress;

    }

    public boolean manageDeleteProgress() {

        // 제거할 상품 가져오기
        int userInput = getMenuByMenuType(MenuType.MANAGEMENT_DELETE_SELECT_PRODUCT).getUserInput();
        String deleteProductName = getCategory(userInput).getProduct(userInput).getName();

        // 상품 제거
        if (!getCategory(userInput).deleteProduct(userInput)) {
            System.out.println("상품 제거 실패");
            return false;
        } else {
            System.out.println("상품 제거 성공");
        }

        // 장바구니 제거

        if (cart.removeItem(deleteProductName)) {
            System.out.println("장바구니 제거 완료");
        }

        return true;
    }

}



