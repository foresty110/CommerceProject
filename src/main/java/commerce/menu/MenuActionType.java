package commerce.menu;

/**
 * MenuActionType
 * -------------------------
 * 번호 선택시 발생할 수 있는 행동을 분류하는 Enum 클래스입니다.
 **/
public enum MenuActionType {
    CANCEL_OR_CONFIRM, // 취소-확인 선택 메뉴
    EXIT, // 종료
    SELECT, // 번호 선택
    INPUT,// 카테고리 선택
    BACK// 뒤로가기
}
