package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TotalPriceBeforeDiscountTest {
    @DisplayName("할인 전 총 주문 금액 반환하기")
    @Test
    void calculatePrice(){
        TotalPriceBeforeDiscount totalPriceBeforeDiscount = new TotalPriceBeforeDiscount();
        int price = totalPriceBeforeDiscount.calculatePrice(Map.of("타파스","1","제로콜라","3"));
        assertThat(price).isEqualTo(14500);
    }
}
