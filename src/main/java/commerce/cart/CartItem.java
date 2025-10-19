package commerce.cart;

import commerce.category.Product;

/**
 * CartItem
 * -------------------------
 * 장바구니에 담기는 하나의 상품 단위를 나타냅니다.
 * 각 상품과 수량을 관리합니다.
 **/
public class CartItem {
    private Product product; // 상품 데이터
    private int quantity; // 상품 수량

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
        return product.toString() + " | 수량:" + quantity;
    }
}
