package commerce.category;

import commerce.Constants;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Category {

    private final String name; // 카테고리 이름
    private final List<Product> productList; //카테고리에 포함된 상품 목록

    public Category(List<Product> products, String name) {
       this.name = name;
       this.productList = products;
    }

    public String getName() {
        return name;
    }

    public boolean addProduct(Product findProduct) {

        Product matchItem = productList.stream()
                .filter(product -> product.getName().equals(findProduct.getName()))
                .findFirst()
                .orElse(null);

        //카테고리에 존재하지 않는 상품이면
        if (matchItem == null) {
            //상품 목록에 추가
            this.productList.add(findProduct);
            return true;

        }//존재하지 않는 상품이면
        else {
            return false;
        }

    }

    public Product getProduct(int idx, Predicate<Product> condition) {
        if (productList.size() < idx || idx < 1) {
            return null;
        }

        //필터걸기
        List<Product> filtered = productList.stream()
                .filter(condition)
                .toList();

        return filtered.get(idx - 1);
    }

    public Product getProduct(int idx) {
        if (productList.size() < idx || idx < 1) {
            return null;
        }

        return productList.get(idx - 1);
    }

    public Product getProduct(String name){
        return productList.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    public int getProductsSize() {
        return productList.size();
    }

    public int getProductsSizeUnder(){
        List<Product> filtered = productList.stream()
                .filter(product -> product.getPrice() <= 1000000)
                .toList();

        return filtered.size();
    }
    public int getProductsSizeOver() {

        //필터걸기
        List<Product> filtered = productList.stream()
                .filter(product -> product.getPrice() > 1000000)
                .toList();

        return filtered.size();
    }

    public String showProductsInfo() {
        StringBuilder info = new StringBuilder();
        int count = 1;
        for (Product p : productList) {
            info.append(count++).append(". ").append(p.toString()).append("\n");
        }
        return info.toString();
    }

    public String showProductsInfoOver() {

        AtomicInteger count = new AtomicInteger(1);

        return productList.stream()
                .filter(product -> product.getPrice() > Constants.PRODUCT_FILTER_PRICE)
                .map(product -> count.getAndIncrement() + ". " + product)
                .collect(Collectors.joining("\n"));
    }
    public String showProductsInfoUnder() {

        AtomicInteger count = new AtomicInteger(1);

        return productList.stream()
                .filter(product -> product.getPrice() <= Constants.PRODUCT_FILTER_PRICE)
                .map(product -> count.getAndIncrement() + ". " + product)
                .collect(Collectors.joining("\n"));
    }

    public boolean deleteProduct(int idx) {
        if (productList.size() < idx || idx < 1) {
            return false;
        }
        productList.remove(idx - 1);
        return true;
    }

}
