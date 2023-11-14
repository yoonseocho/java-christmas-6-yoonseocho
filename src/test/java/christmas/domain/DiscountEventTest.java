package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiscountEventTest {
    DiscountEvent discountEvent;
    @DisplayName("크리스마스 디데이 할인금액 반환하기")
    @Test
    void calculateDiscountAmountByChristmasEvent(){
        discountEvent = new DiscountEvent(25);
        int discount = discountEvent.calculateDiscountAmountByChristmasEvent();
        assertThat(discount).isEqualTo(-3400);
    }
    @DisplayName("평일 할인금액 반환하기")
    @Test
    void calculateDiscountAmountByWeekEvent(){
        discountEvent = new DiscountEvent(3);
        int discount = discountEvent.calculateDiscountAmountByWeekEvent(Map.of("타파스","1","초코케이크","2","아이스크림","2"));
        assertThat(discount).isEqualTo(-8092);
    }
    @DisplayName("주말 할인금액 반환하기")
    @Test
    void calculateDiscountAmountByWeekendEvent(){
        discountEvent = new DiscountEvent(15);
        int discount = discountEvent.calculateDiscountAmountByWeekendEvent(Map.of("타파스","1","티본스테이크","2","크리스마스파스타","1","바비큐립","1"));
        assertThat(discount).isEqualTo(-8092);
    }
}
