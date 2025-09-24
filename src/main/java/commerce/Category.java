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

    public boolean addProduct(Product product){

        for(Product p:products){
            if(p.getName().equals(product.getName())){
                return false;
            }
        }

        this.products.add(product);
        return true;
    }

    public Product getProducts(int idx) {
        if (products.size() < idx || idx < 1) {
            return null;
        }
       return products.get(idx -1);
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
