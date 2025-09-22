package commerce;

public class Product {
    //TODO
    private String name;
    private int price;
    private String description;
    private int quantity;

    public Product() {

    }
    public Product(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String toString(){
        return this.name + "\t |" + this.price + "\t |" + this.description;
    }
}
