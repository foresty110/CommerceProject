package commerce;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Category {

    private String name;
    private List<Product> products;

    public Category(List<Product> products, String name) {
        this.products = products;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addProduct(Product findProduct) {

        Product matchItem = products.stream()
                .filter(product -> product.getName().equals(findProduct.getName()))
                .findFirst()
                .orElse(null);

        //카테고리에 존재하지 않는 상품이면
        if (matchItem == null) {
            //상품 목록에 추가
            this.products.add(findProduct);
            return true;

        }//존재하지 않는 상품이면
        else {
            return false;
        }

    }

    public Product getProduct(int idx, Predicate<Product> condition) {
        if (products.size() < idx || idx < 1) {
            return null;
        }

        //필터걸기
        List<Product> filtered = products.stream()
                .filter(condition)
                .toList();

        return filtered.get(idx - 1);
    }

    public Product getProduct(int idx) {
        if (products.size() < idx || idx < 1) {
            return null;
        }

        return products.get(idx - 1);
    }

    public int getProductsSize() {
        return products.size();
    }

    public int getProductsSize(Predicate<Product> condition) {
        //필터걸기
        List<Product> filtered = products.stream()
                .filter(condition)
                .toList();

        return filtered.size();
    }

    public String showProductsInfo() {
        String info = "";
        int count = 1;
        for (Product p : products) {
            info += count++ + ". " + p.toString() + "\n";
        }
        return info;
    }

    public String showProductsInfoOver() {

        AtomicInteger count = new AtomicInteger(1);

        String info = products.stream()
                .filter(product -> product.getPrice() > 1000000)
                .map(product -> count.getAndIncrement() + ". " + product.toString())
                .collect(Collectors.joining("\n"));

        return info;
    }
    public String showProductsInfoUnder() {

        AtomicInteger count = new AtomicInteger(1);

        String info = products.stream()
                .filter(product -> product.getPrice() <= 1000000)
                .map(product -> count.getAndIncrement() + ". " + product.toString())
                .collect(Collectors.joining("\n"));

        return info;
    }




    public boolean deleteProduct(int idx) {
        if (products.size() < idx || idx < 1) {
            return false;
        }
        products.remove(idx - 1);
        return true;
    }

}
