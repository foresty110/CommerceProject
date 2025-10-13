package commerce.customer;

public class Customer {

    private String name;
    private String email;
    private CustomerGrade grade;

    public Customer(String name, String email, CustomerGrade grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }
    public CustomerGrade getCustomerGrade() {
        return grade;
    }
}
