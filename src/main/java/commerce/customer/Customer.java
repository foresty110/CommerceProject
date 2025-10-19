package commerce.customer;

/**
 * Customer
 * -------------------------
 * 플랫폼 고객 정보를 관리하는 클래스입니다.
 * *
 * 주요 기능 *
 * - 고객 기본 정보 저장
 * - 고객 등급 조회
 **/
public class Customer {

    private String name; // 고객 이름
    private String email; // 고객 이메일
    private CustomerGrade grade; // 고객 등급

    public Customer(String name, String email, CustomerGrade grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }
    public CustomerGrade getCustomerGrade() {
        return grade;
    }
}
