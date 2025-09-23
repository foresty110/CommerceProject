package commerce;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private List<Category> categories = new ArrayList<>();
    private Cart cart = new Cart();

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Category getCategory(int idx) {
        return categories.get(idx);
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

        // 사용자로부터 입력받기
        while (true) {

            // 전자제품 목록 출력
            System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1." + electronics.getName()
                    + "\n2." + clothing.getName()
                    + "\n3." + food.getName());
            System.out.println("0. 프로그램 종료");
            System.out.print("번호를 선택하세요: ");

            Scanner scanner = new Scanner(System.in);
            int menu;
            // 카테고리 번호 입력 받기
            try {
                menu = scanner.nextInt();
                if (menu == 0) {
                    System.out.println(" 커머스 플랫폼을 종료합니다.");
                    break;
                } else if (menu > categories.size()) {
                    System.out.println("메뉴 번호를 입력해 주세요.");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해 주세요");
                continue;
            }

            // 선택한 카테고리의 상품 데이터 출력
            for (int i = 0; i < categories.size(); i++) {
                if (menu - 1 == i) {
                    getCategory(i).showProductsInfo();
                }
            }
            System.out.print("0. 뒤로가기 \n 번호를 선택하세요: ");

            // 상품 선택 입력받기
            int product = scanner.nextInt();

            if (product == 0) {
                continue;
            } else if (product > getCategory(menu - 1).getProductsSize()) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            // 상품 상세 정보 출력하기
            for (int i = 0; i < getCategory(menu - 1).getProductsSize(); i++) {
                if (product - 1 == i) {
                    Product p = getCategory(menu - 1).getProducts(i);
                    System.out.println("선택한 상품: " + p.toStringDetail());

                    //재고 관리 & 장바구니 추가
                    if(!cart.canAddToCart(p))
                    {
                        System.out.println("재고가 없습니다.");
                    }

                    cart.addItem(new CartItem(p, 1));

                }
            }
        }
    }
}


