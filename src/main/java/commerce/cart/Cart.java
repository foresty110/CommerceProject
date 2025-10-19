package commerce.cart;

import commerce.category.Product;

import java.util.ArrayList;
import java.util.List;

/*Cart 클래스
-------------------------
- 사용자의 장바구니 상품을 관리하는 클래스
- 장바구니에 상품 추가, 삭제, 조회, 구매 기능을 제공합니다.*/
public class Cart {

    private final List<CartItem> items = new ArrayList<>(); // 장바구니에 추가된 상품 목록 리스트

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

    //장바구니에서 단일 상품 제거
    public boolean removeItem(String findItem) {

        CartItem matchItem = items.stream()
                .filter(item -> item.getProduct().getName().equals(findItem))
                .findFirst()
                .orElse(null);

        if (matchItem == null) {
            return false;
        }else{
            items.remove(matchItem);
            return true;
        }

    }

    // 장바구니에 상품 추가
    public boolean canAddToCart(Product product) {

        return product.getStockQuantity() >= 1;
    }

    // 장바구니에 담긴 상품 개수 반환
    public int getCartItemAmount() {
        return items.size();
    }

    // 장바구니에 담긴 상품 정보 출력
    public String showCartItems() {

        StringBuilder result = new StringBuilder();
        for (CartItem item : items) {
            result.append(item.toString()).append("\n");
        }
        return result.toString();
    }

    // 장바구니 상품 데이터 반환
    public List<CartItem> getCartItems() {
        return items;
    }

    // 장바구니 상품 구매 처리
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
       clearCart();
    }
    public void clearCart() {
        items.clear();
    }
}
