package commerce.category;

// 커머스 상품 카테고리 종류
public enum CategoryType {
    ELECTRONICS(1), // 전자제품
    CLOTHING(2), // 의류
    FOOD(3); // 음식

    private final int value;
    CategoryType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
