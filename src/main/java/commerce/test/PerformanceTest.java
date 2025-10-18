package commerce.test;

import commerce.Constants;
import commerce.category.Product;
import commerce.system.SearchEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceTest {
    private final SearchEngine searchEngine = new SearchEngine();
    private Product findProduct;

    public void createData() {

        System.out.println("[대용량 데이터 생성 중...]");

        // 데이터 재생성 예외처리
        if (searchEngine.getSortedProducts() != null) {
            System.out.println("이미 생성된 데이터가 있습니다.");
            return;
        }

        List<Product> sortedProducts = new ArrayList<>();

        // 임의의 데이터 생성
        for (int i = 1; i <= Constants.PERFORMANCE_TESTCASE; i++) {
            String name = String.format("Product_%04d", i);
            sortedProducts.add(new Product(name, i, "desc", i));
        }

        // 데이터 정렬
        Collections.sort(sortedProducts, (a, b) -> a.getName().compareTo(b.getName()));

        // 데이터 저장
        searchEngine.setSortedProducts(sortedProducts);

        System.out.println(Constants.PERFORMANCE_TESTCASE + "개 상품 데이터 생성 완료");
    }

    public void compareSearchPerformance(String name) {

        // 완전탐색 시간 측정
        long linearTime = measureLinearSearch(name);

        // 결과 출력
        System.out.println("완전 탐색\n탐색 속도: " + linearTime + "ns");
        System.out.println("비교 횟수:" + searchEngine.getCompareCount());
        if (findProduct == null) {
            System.out.println("탐색 결과: 실패");
        } else {
            System.out.println("탐색 결과: 성공");
        }

        // 이진탐색 시간 측정
        long binaryTime = measureBinarySearch(name);

        System.out.println("\n이진탐색 \n탐색 속도: " + binaryTime + "ns");
        System.out.println("비교 횟수:" + searchEngine.getCompareCount());
        if (findProduct == null) {
            System.out.println("탐색 결과: 실패");
        } else {
            System.out.println("탐색 결과: 성공");
        }

        System.out.println("성능 향상: " + (linearTime / binaryTime) + "배");
    }

    private long measureBinarySearch(String name) {

        // 검색 전 시간 측정
        long start = System.nanoTime();

        // 이진탐색
        findProduct = searchEngine.searchProductsBinary(name);

        // 검색 완료 후 시간 측정
        long end = System.nanoTime();

        // 검색하는데 걸린 시간 반환
        return end - start;
    }

    private long measureLinearSearch(String name) {

        // 검색 전 시간 측정
        long start = System.nanoTime();

        // 완전 탐색 수행
        findProduct = searchEngine.searchProductsLinear(name);

        // 검색 완료 후 시간 측정
        long end = System.nanoTime();

        // 검색하는데 걸린 시간 반환
        return end - start;
    }

}
