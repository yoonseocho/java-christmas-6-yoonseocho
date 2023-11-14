package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TotalPriceAfterEventTest {
    TotalPriceAfterEvent totalPriceAfterEvent;
    @BeforeEach
    void setUp(){
        totalPriceAfterEvent = new TotalPriceAfterEvent(142000,-31246, Map.of("티본스테이크","1","바비큐립","1","초코케이크","2","제로콜라","1"));
    }
    @DisplayName("할인 후 예상 결제 금액 반환하기")
    @Test
    void calculateTotalPriceAfterEvent(){
        int price = totalPriceAfterEvent.calculateTotalPriceAfterEvent();
        assertThat(price).isEqualTo(135754);
    }
}
