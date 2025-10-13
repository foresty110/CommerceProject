import commerce.system.CommerceSystem;
import org.junit.jupiter.api.Test;

public class PerformanceTest {
    CommerceSystem commerceSystem = new CommerceSystem();

    @Test
    public void compareSearchPerformance() {

        commerceSystem.createData();

        // 완전탐색 시간 측정
        long linearTime =  measureLinearSearch();

        // 이진탐색 시간 측정
        long binaryTime = measureBinarySearch();

        // 결과 출력
        System.out.println("완전탐색: " + linearTime + "ns");
        System.out.println("이진탐색: " + binaryTime + "ns");
        System.out.println("성능 향상: " + (linearTime / binaryTime) + "배");

        return ;
    }

    private long measureBinarySearch() {

        long start = System.nanoTime();

        commerceSystem.getCategory(1).getProduct("제스프리 키위");

        long end = System.nanoTime();

        long elapsedTime = end - start;

        return elapsedTime;
    }

    private long measureLinearSearch() {
        long start = System.nanoTime();

        commerceSystem.getCategory(1).getProductLinear("제스프리 키위");

        long end = System.nanoTime();

        long elapsedTime = end - start;

        return elapsedTime;
    }

}
