package commerce;

public class Product {

    private String name;
    private int price;
    private String description;
    private int quantity;

    public Product(String name, int price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public String toString(){
        return this.name + "\t | " + this.price + "원\t | " + this.description;
    }

    public String toStringDetail(){
        return this.name + "\t | " + this.price + "원\t | " + this.description + "\t | 재고:" + this.quantity;
    }
}
