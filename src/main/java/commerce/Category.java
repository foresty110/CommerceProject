package commerce;

import java.util.List;

public class Category {

    private String name;
    private List<Product> products;

    public Category(List<Product> products,String name){
        this.products = products;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Product getProducts(int idx) {
       return products.get(idx);
    }

    public int getProductsSize() {
        return products.size();
    }

    public void showProductsInfo() {
        int count = 1;
        for (Product p : products) {
            System.out.println(count++ + ". " + p.toString());
        }
    }


}
