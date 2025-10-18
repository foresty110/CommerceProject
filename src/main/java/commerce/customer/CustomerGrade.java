package commerce.customer;

import commerce.Constants;

public enum CustomerGrade {
    BRONZE(Constants.DISCOUNT_BRONZE), // 브론즈 등급
    SILVER(Constants.DISCOUNT_SILVER), // 실버 등급
    GOLD(Constants.DISCOUNT_GOLD), // 골드 등급
    PLATINUM(Constants.DISCOUNT_PLATINUM); // 플래티넘 등급

    private int value;
    CustomerGrade(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
