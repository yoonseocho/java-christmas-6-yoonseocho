package christmas.domain;

import java.util.Map;

import static christmas.constant.PromotionConstant.*;

public class TotalPriceAfterPromotion {
    private int totalPromotionAmount;

    public TotalPriceAfterPromotion(int totalPromotionAmountExceptFreebie, boolean freebie) {
        this.totalPromotionAmount = calculateTotalPromotionAmount(totalPromotionAmountExceptFreebie, freebie);
    }

    public int calculateTotalPromotionAmount(int totalPromotionAmountExceptFreebie, boolean freebie) {
        if (freebie) {
            totalPromotionAmountExceptFreebie += PROMOTION_AMOUNT_OF_FREEBIE;
        }
        this.totalPromotionAmount = totalPromotionAmountExceptFreebie;
        return totalPromotionAmount;
    }

    public int calculateTotalPriceAfterPromotion(int totalPrice, Map<String, String> orderMenu) {
        if (totalPrice < MINIMUM_AMOUNT_OF_PROMOTION_EVENT) {
            return totalPrice;
        }
        if (orderMenu.containsKey(Menu.CHAMPAGNE.getName())) {
            return totalPrice + totalPromotionAmount;
        }
        return totalPrice + totalPromotionAmount + PRICE_OF_CHAMPAGNE;
    }
}
