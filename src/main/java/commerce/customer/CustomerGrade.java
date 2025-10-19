package commerce.customer;

import commerce.Constants;

/**
 * CustomerGrade
 * -------------------------
 * 손님 등급을 나타내는 Enum 클래스입니다.
 * 각 등급은 할인율을 의미하는 고유한 int 값과 매핑됩니다.
 **/
public enum CustomerGrade {
    BRONZE(Constants.DISCOUNT_BRONZE), // 브론즈 등급
    SILVER(Constants.DISCOUNT_SILVER), // 실버 등급
    GOLD(Constants.DISCOUNT_GOLD), // 골드 등급
    PLATINUM(Constants.DISCOUNT_PLATINUM); // 플래티넘 등급

    private final int value;
    CustomerGrade(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
