package commerce.system;

import commerce.category.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private List<Product> sortedProducts = new ArrayList<>();
    private int compareCount = 0;

    public void setSortedProducts(List<Product> sortedProducts) {
        this.sortedProducts = sortedProducts;
    }

    public List<Product> getSortedProducts() {
        if(sortedProducts.isEmpty()){
            return null;
        }
        return sortedProducts;
    }

    public int getCompareCount() {
        return compareCount;
    }

    public Product searchProductsBinary(String productName) {

        //비교 횟수 초기화
        compareCount = 0;

        return binarySearchRecursive(productName,0,sortedProducts.size()-1);
    }

    // 이진탐색 - 재귀 방식
    public Product binarySearchRecursive(String productName, int left, int right) {

        if (left > right)
            return null;

        compareCount++;
        int mid = (left + right) / 2;
        String findProductName = sortedProducts.get(mid).getName();

        int findResult = findProductName.compareTo(productName);
        if (findResult == 0){
            return sortedProducts.get(mid);
        }else if (findResult > 0){
           return binarySearchRecursive(productName, left, mid - 1);
        }else if (findResult < 0){
           return binarySearchRecursive(productName,mid + 1, right );
        }

        return null;
    }

    // 이진탐색 - 반복문 방식
    public Product binarySearchIterative(String productName) {

        int left = 0;
        int right = sortedProducts.size() - 1;

        while (left <= right) {
            compareCount++;

            int mid = (left + right) / 2;
            String findProductName = sortedProducts.get(mid).getName();

            int findResult = findProductName.compareTo(productName);
            if (findResult == 0) {
                return sortedProducts.get(mid);
            } else if (findResult > 0) {
                right = mid - 1;
            } else if (findResult < 0) {
                left = mid + 1;
            }

        }
        return null;
    }

    public Product searchProductsLinear(String name) {

        //비교 횟수 초기화
        compareCount = 0;

        // (완전탐색)
        for (Product product : sortedProducts) {
            compareCount++;
            if (product.getName().contains(name)) {
                return product;
            }
        }
        return null;
    }
}
