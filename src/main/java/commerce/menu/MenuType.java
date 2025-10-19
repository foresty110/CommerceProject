package commerce.menu;

/**
 * MenuType
 * -------------------------
 * 플랫폼 메뉴 종류를 나타내는 Enum 클래스입니다.
 * 각 메뉴는 고유한 string 값(메뉴 이름)과 매핑됩니다.
 **/
public enum MenuType {
    MAIN("커머스 플랫폼 메인"),
    SHOW_PRODUCT("상품 목록 보기 - 카테고리 선택"),
    SHOW_FILTER("상품 목록 보기 - 필터 선택"),
    ADDCART_CONFIM("장바구니 추가 확인"),
    CONFIRM_ORDER("주문 확정"),
    CANCLE_ORDER("주문 취소"),
    CATEGORY_DELETE("카테고리 제거"),
    MANAGEMENT_MAIN("관리자 모드"),
    MANAGEMENT_ADD_CATEGORY("관리자 모드 - 상품 추가 카테고리 선택"),
    MANAGEMENT_MODIFY_CATEGORY("관리자 모드 - 상품 수정 카테고리 선택"),
    MANAGEMENT_MODIFY_PRODUCT("관리자 모드 - 상품 수정 대상 입력"),
    MANAGEMENT_MODIFY_INFO("관리자 모드 - 상품 수정 항목 선택"),
    MANAGEMENT_ADD_CONFIRM("관리자 모드 - 상품 추가 확정"),
    MANAGEMENT_DELETE("관리자 모드 - 상품 삭제"),
    MANAGEMENT_DELETE_SELECT_PRODUCT("관리자 모드 - 상품 삭제 대상 선택"),
    MANAGEMENT_DELETE_CONFIM("관리자 모드 - 상품 삭제 확정");


    private final String name;

    MenuType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
