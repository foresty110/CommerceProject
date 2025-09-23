package commerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<CartItem>();

    public void addItem(CartItem item) {
        items.add(item);
        item.getProduct().subQuantity(item.getQuantity());
    }

    public boolean canAddToCart(Product product) {
        if (product.getStockQuantity() < 1) {
            return false;
        }
        return true;
    }
}
