package commerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<CartItem>();

    public void addItem(CartItem item)
    {
        items.add(item);
    }

}
