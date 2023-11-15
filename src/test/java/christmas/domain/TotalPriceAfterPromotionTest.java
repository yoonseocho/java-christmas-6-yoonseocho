package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TotalPriceAfterPromotionTest {
    TotalPriceAfterPromotion totalPriceAfterPromotion;
    @BeforeEach
    void setUp(){
        totalPriceAfterPromotion = new TotalPriceAfterPromotion(-6_246,true);
    }
    @DisplayName("총 혜택 금액 반환하기")
    @Test
    void calculateTotalPromotionAmount(){
        int price = totalPriceAfterPromotion.calculateTotalPromotionAmount(-6_246,true);
        assertThat(price).isEqualTo(-31_246);
    }
    @DisplayName("할인 후 예상 결제 금액 반환하기")
    @Test
    void calculateTotalPriceAfterEvent(){
        int price = totalPriceAfterPromotion.calculateTotalPriceAfterPromotion(142_000,Map.of("티본스테이크","1","바비큐립","1","초코케이크","2","제로콜라","1"));
        assertThat(price).isEqualTo(135_754);
    }
}
