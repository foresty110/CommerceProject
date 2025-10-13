package commerce.customer;

public enum CustomerGrade {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    private int value;
    CustomerGrade(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
