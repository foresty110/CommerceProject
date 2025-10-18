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

        //비교 횟수 증가
        compareCount++;

        int mid = (left + right) / 2;
        String findProductName = sortedProducts.get(mid).getName();

        // 문자열 비교
        int findResult = findProductName.compareTo(productName);

        if (findResult == 0){ // 검색한 상품명과 일치할 경우
            return sortedProducts.get(mid);
        }else if (findResult > 0){ // 탐색한 상품이 내가 찾고자 하는 것 보다 사전적으로 순서가 뒤에 있음 -> 앞으로 이동해야한다
           return binarySearchRecursive(productName, left, mid - 1);
        }else { // 탐색한 상품이 내가 찾고자 하는 것 보다 사전적으로 순서가 앞에 있음 -> 뒤로 이동해야한다
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

            // 문자열 비교
            int findResult = findProductName.compareTo(productName);

            if (findResult == 0) { // 검색한 상품명과 일치할 경우
                return sortedProducts.get(mid);
            } else if (findResult > 0) { // 탐색한 상품이 내가 찾고자 하는 것 보다 사전적으로 순서가 뒤에 있음 -> 앞으로 이동해야한다
                right = mid - 1;
            } else { // 탐색한 상품이 내가 찾고자 하는 것 보다 사전적으로 순서가 앞에 있음 -> 뒤로 이동해야한다
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
