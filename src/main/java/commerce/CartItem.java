package commerce;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity() {
        this.quantity ++;
    }

   public String toString(){
        return product.toString() + " 수량:" + quantity;
    }
}
