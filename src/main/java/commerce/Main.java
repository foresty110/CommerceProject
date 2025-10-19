package commerce;

import commerce.system.CommerceSystem;

/**
 * Main
 * -------------------------
 * 프로젝트 진입점입니다.
 * 커머스 시스템을 실행하기 위한 메인 클래스입니다.
 **/
public class Main {
    public static void main(String[] args) {

        CommerceSystem commerceSystem = new CommerceSystem();
        commerceSystem.start();
    }
}
