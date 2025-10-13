package commerce;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchEngine {
    private List<Product> sortedProducts;

    SearchEngine(List<Product> sortedProducts) {
        this.sortedProducts = sortedProducts;

        Collections.sort(sortedProducts,(a,b)->a.getName().compareTo(b.getName()));

    }

    public List<Product> getSortedProducts() {
        return sortedProducts;
    }

    public Product getProducts(String productName) {
       return binarySearchRecursive(productName,0,sortedProducts.size()-1);
    }

    // 이진탐색 - 재귀 방식
    public Product binarySearchRecursive(String productName, int left, int right) {

        if (left > right)
            return null;

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
}
