package commerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem newItem) {

        //이미 장바구니에 있던 상품이라면
        for (CartItem i : items) {
            if (newItem.getProduct().getName().equals(i.getProduct().getName())) {
                i.addQuantity();
                return;
            }
        }

        // 장바구니에 새로 추가하는 상품이라면
        items.add(newItem);
        newItem.addQuantity();
    }

    public boolean canAddToCart(Product product) {

        if (product.getStockQuantity() < 1) {
            return false;
        }
        return true;
    }

    public int getCartItemAmount() {
        return items.size();
    }

    public void showCartItems() {

        for (CartItem item : items) {
            System.out.println(item.toString());
        }
    }

    public int getTotalPrice() {

        int sum = 0;
        for (CartItem item : items) {
            int price = item.getProduct().getPrice();
            sum += price * item.getQuantity();
        }
        return sum;
    }

    public void purchase() {

        for (CartItem item : items) {
            //구매 이전 재고량
            int beforeQuantity = item.getProduct().getStockQuantity();
            // 구매 수량만큼 차감
            item.getProduct().subQuantity(item.getQuantity());
            // 구매 후 재고량
            int afterQuantity = item.getProduct().getStockQuantity();

            System.out.println(item.getProduct().getName() + " 재고가" + beforeQuantity + "개 ->" + afterQuantity + "개로 업데이트 되었습니다.");
        }
        // 장바구니 비우기
        items.clear();
    }
}
