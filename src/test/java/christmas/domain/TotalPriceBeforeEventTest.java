package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TotalPriceBeforeEventTest {
    TotalPriceBeforeEvent totalPriceBeforeEvent;
    @BeforeEach
    void setUp(){
        totalPriceBeforeEvent = new TotalPriceBeforeEvent(Map.of("타파스","1","제로콜라","3"));
    }
    @DisplayName("할인 전 총 주문 금액 반환하기")
    @Test
    void calculatePrice(){
        int price = totalPriceBeforeEvent.calculatePrice(Map.of("타파스","1","제로콜라","3"));
        assertThat(price).isEqualTo(14500);
    }
    @DisplayName("증정 메뉴 대상자인지 확인하기.")
    @Test
    void checkTargetOfFreeBie(){
        boolean isTarget = totalPriceBeforeEvent.isTargetOfFreeEvent(130000);
        assertThat(isTarget).isTrue();
    }
    @DisplayName("이벤트를 받을 수 있는 금액인지 확인하기")
    @Test
    void isAvailableForDiscountEvent(){
        boolean isAvailable = totalPriceBeforeEvent.isAvailableForDiscountEvent();
        assertThat(isAvailable).isTrue();
    }
}
