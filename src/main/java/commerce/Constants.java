package commerce;

/**
 * Constants
 * -------------------------
 * 프로젝트 전반에서 사용되는 상수 값을 관리하는 클래스입니다.
 **/
public class Constants {
    public static String PASSWORD = "admin123"; // 관리자 모드 비밀번호
    public static int PASSWORD_ATTEMPT_LIMIT = 3; // 관리자 모드 비밀번호 입력 횟수 제한

    public static int DISCOUNT_BRONZE = 0; // 브론즈 등급 고객의 할인율
    public static int DISCOUNT_SILVER = 5; // 실버 등급 고객의 할인율
    public static int DISCOUNT_GOLD = 10; // 골드 등급 고객의 할인율
    public static int DISCOUNT_PLATINUM = 15; // 플래티넘 등급 고객의 할인율

    public static int PERFORMANCE_TESTCASE = 10000; // 성능 테스트 개수

    public static int PRODUCT_FILTER_PRICE = 1000000; // 상품 필터 기준값

}
