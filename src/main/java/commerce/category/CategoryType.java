package commerce.category;

/**
 * CategoryType
 * -------------------------
 * 상품의 카테고리를 나타내는 Enum 클래스입니다.
 * 각 카테고리는 고유한 int 값과 매핑됩니다.
 **/
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
