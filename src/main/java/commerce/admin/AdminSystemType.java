package commerce.admin;

public enum AdminSystemType {
    ADD_Product(1), //상품 추가
    MODIFY_PRODUCT(2), //상품 수정
    DELETE_PRODUCT(3); //상품 삭제

    private final int value;

    AdminSystemType(int value) {
        this.value = value;
    }

    public static AdminSystemType fromValue(int value) {
        for (AdminSystemType type : values()) {
            if (type.value == value) return type;
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
