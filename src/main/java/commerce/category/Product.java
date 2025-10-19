package commerce.category;

/**
 * Product
 * -------------------------
 * 판매하는 상품의 정보를 관리하는 클래스입니다.
 * *
 * 주요 기능 *
 * 상품의 기본 정보 조회
 * 재고 수량 감소 (구매 시)
 * 상품 정보 수정 (가격, 설명, 재고)
 **/
public class Product {

    private final String name; // 상품 이름
    private int price; // 상품 가격
    private String description; // 상품 설명
    private int stockQuantity; // 상품 수량

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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
