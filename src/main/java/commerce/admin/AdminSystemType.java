package commerce.admin;

// 관리 시스템에서 사용하는 메뉴 타입을 정의한 Enum 클래스입니다.
public enum AdminSystemType {
    ADD_Product(1), //상품 추가
    MODIFY_PRODUCT(2), //상품 수정
    DELETE_PRODUCT(3); //상품 삭제

    private final int value;

    AdminSystemType(int value) {
        this.value = value;
    }

    // 파라미터로 전달받은 값을 AdminSystemType으로 바꿔주는 매서드입니다.
    public static AdminSystemType fromValue(int value) {
        for (AdminSystemType type : values()) {
            if (type.value == value) return type;
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
