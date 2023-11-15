package christmas.domain;

import java.util.Map;

import static christmas.constant.PromotionConstant.MINIMUM_AMOUNT_OF_EVENT;
import static christmas.constant.PromotionConstant.PRICE_OF_CHAMPAGNE;

public class TotalPriceAfterEvent {
    private final int totalPrice;
    private final int totalDiscountAmount;
    private final Map<String, String> orderMenu;

    public TotalPriceAfterEvent(int totalPrice, int totalDiscountAmount, Map<String, String> orderMenu) {
        this.totalPrice = totalPrice;
        this.totalDiscountAmount = totalDiscountAmount;
        this.orderMenu = orderMenu;
    }

    public int calculateTotalPriceAfterEvent() {
        if (totalPrice < MINIMUM_AMOUNT_OF_EVENT) {
            return totalPrice;
        }
        if (orderMenu.containsKey(Menu.CHAMPAGNE.getName())) {
            return totalPrice + totalDiscountAmount;
        }
        return totalPrice + totalDiscountAmount + PRICE_OF_CHAMPAGNE;
    }
}
