package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiscountEventTest {
    DiscountEvent discountEvent;
    @BeforeEach
    void setUp(){
        discountEvent = new DiscountEvent();
    }
    @DisplayName("크리스마스 디데이 할인금액 반환하기")
    @Test
    void calculateDiscountAmountByChristmasEvent(){
        int discount = discountEvent.calculateDiscountAmountByChristmasEvent(3);
        assertThat(discount).isEqualTo(-1200);
    }
    @DisplayName("평일 할인금액 반환하기")
    @Test
    void calculateDiscountAmountByWeekEvent(){
        int discount = discountEvent.calculateDiscountAmountByWeekEvent(21, Map.of("타파스","1","초코케이크","2","아이스크림","2"));
        assertThat(discount).isEqualTo(-8092);
    }
}
