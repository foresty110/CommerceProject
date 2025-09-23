package commerce;

public class Product {

    private String name;
    private int price;
    private String description;
    private int stockQuantity;

    public Product(String name, int price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = quantity;
    }

    public String toString(){
        return this.name + "\t | " + this.price + "원\t | " + this.description;
    }

    public String toStringDetail(){
        return this.name + "\t | " + this.price + "원\t | " + this.description + "\t | 재고:" + this.stockQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void subQuantity(int value) {
        this.stockQuantity -= value;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
