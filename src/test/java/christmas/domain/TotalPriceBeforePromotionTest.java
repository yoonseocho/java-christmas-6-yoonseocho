package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TotalPriceBeforePromotionTest {
    TotalPriceBeforePromotion totalPriceBeforePromotion;
    @BeforeEach
    void setUp(){
        totalPriceBeforePromotion = new TotalPriceBeforePromotion(Map.of("티본스테이크","1","바비큐립","1","초코케이크","2","제로콜라","1"));
    }
    @DisplayName("할인 전 총 주문 금액 반환하기")
    @Test
    void calculatePrice(){
        int price = totalPriceBeforePromotion.calculatePrice(Map.of("티본스테이크","1","바비큐립","1","초코케이크","2","제로콜라","1"));
        assertThat(price).isEqualTo(142_000);
    }
    @DisplayName("증정 메뉴 대상자인지 확인하기.")
    @Test
    void checkTargetOfFreeBie(){
        boolean isTarget = totalPriceBeforePromotion.isTargetOfFreebie();
        assertThat(isTarget).isTrue();
    }
    @DisplayName("이벤트를 받을 수 있는 금액인지 확인하기")
    @Test
    void isAvailableForDiscountEvent(){
        boolean available = totalPriceBeforePromotion.isAvailableForPromotionEvent();
        assertThat(available).isTrue();
    }
}
