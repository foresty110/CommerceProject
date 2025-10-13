package commerce;

import java.util.List;

public class SearchEngine {
    private List<Product> sortedProducts;

    SearchEngine(List<Product> sortedProducts) {
        this.sortedProducts = sortedProducts;
    }

    public List<Product> getSortedProducts() {
        return sortedProducts;
    }

    // 이진탐색 - 재귀 방식
    public Product binarySearchRecursive(String productName, int left, int right) {
        // 구현하세요
        return null;
    }

    // 이진탐색 - 반복문 방식
    public Product binarySearchIterative(String productName) {
        // 구현하세요
        return null;
    }
}
