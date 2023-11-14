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
        discountEvent = new DiscountEvent(17,Map.of("타파스","1","티본스테이크","2","크리스마스파스타","1","바비큐립","1","초코케이크","2","아이스크림","2"));
    }
    @DisplayName("크리스마스 디데이 할인금액 반환하기")
    @Test
    void calculateDiscountAmountByChristmasEvent(){
        int discount = discountEvent.calculateDiscountAmountByChristmasEvent();
        assertThat(discount).isEqualTo(-2600);
    }
    @DisplayName("평일 할인금액 반환하기")
    @Test
    void calculateDiscountAmountByWeekEvent(){
        int discount = discountEvent.calculateDiscountAmountByWeekEvent();
        assertThat(discount).isEqualTo(-8092);
    }
    @DisplayName("주말 할인금액 반환하기")
    @Test
    void calculateDiscountAmountByWeekendEvent(){
        int discount = discountEvent.calculateDiscountAmountByWeekendEvent();
        assertThat(discount).isEqualTo(0);
    }
    @DisplayName("특별 할인금액 반환하기")
    @Test
    void calculateDiscountAmountBySpecialStarEvent(){
        int discount = discountEvent.calculateDiscountAmountBySpecialStarEvent();
        assertThat(discount).isEqualTo(-1000);
    }
}
