package commerce.test;

import commerce.category.Product;
import commerce.system.ScannerSystem;
import commerce.system.SearchEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceTest {
    ScannerSystem scannerSystem = new ScannerSystem();
    private SearchEngine  searchEngine = new SearchEngine();
    private Product findProduct;

    public void createData(){

        System.out.println("[대용량 데이터 생성 중...]");

        if (searchEngine.getSortedProducts() != null){
            System.out.println("이미 생성된 데이터가 있습니다.");
            return;
        }

        List<Product> sortedProducts = new ArrayList<Product>();

        for(int i = 0; i < 10000; i++){
            String name = String.format("Product_%04d", i);
            sortedProducts.add(new Product(name,i,"desc",i));
        }

        Collections.sort(sortedProducts,(a, b)->a.getName().compareTo(b.getName()));

        searchEngine.setSortedProducts(sortedProducts);

        System.out.println("10,000개 상품 데이터 생성 완료");
    }

    public void compareSearchPerformance(String name) {

        // 완전탐색 시간 측정
        long linearTime = measureLinearSearch(name);

        // 결과 출력
        System.out.println("완전탐색: " + linearTime + "ns");
        System.out.println("비교 횟수:" + searchEngine.getCompareCount());
        if (findProduct == null){
            System.out.println("탐색 결과: 실패");
        }else {
            System.out.println("탐색 결과: 성공");
        }

        // 이진탐색 시간 측정
        long binaryTime = measureBinarySearch(name);

        System.out.println("이진탐색: " + binaryTime + "ns");
        System.out.println("비교 횟수:" + searchEngine.getCompareCount());
        if (findProduct == null){
            System.out.println("탐색 결과: 실패");
        }else {
            System.out.println("탐색 결과: 성공");
        }

        System.out.println("성능 향상: " + (linearTime / binaryTime) + "배");
    }

    private long measureBinarySearch(String name) {
        long start = System.nanoTime();

        findProduct = searchEngine.searchProductsBinary(name);
        long end = System.nanoTime();

        long elapsedTime = end - start;

        return elapsedTime;
    }

    private long measureLinearSearch(String name) {
        long start = System.nanoTime();

        findProduct = searchEngine.searchProductsLinear(name);

        long end = System.nanoTime();

        long elapsedTime = end - start;

        return elapsedTime;
    }

}
