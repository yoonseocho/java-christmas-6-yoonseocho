package christmas.domain;

import static christmas.constant.MessageConstant.*;

public class BadgeOfDecember {
    private final int totalDiscountAmount;

    public BadgeOfDecember(int totalDiscountAmount) {
        this.totalDiscountAmount = Math.abs(totalDiscountAmount);
    }

    public String getBadgeByTotalDiscountAmount() {
        if (totalDiscountAmount >= MINIMUM_AMOUNT_FOR_STAR_BADGE && totalDiscountAmount < MINIMUM_AMOUNT_FOR_TREE_BADGE) {
            return STAR_BADGE;
        }
        if (totalDiscountAmount >= MINIMUM_AMOUNT_FOR_TREE_BADGE && totalDiscountAmount < MINIMUM_AMOUNT_FOR_SANTA_BADGE) {
            return TREE_BADGE;
        }
        if (totalDiscountAmount >= MINIMUM_AMOUNT_FOR_SANTA_BADGE) {
            return SANTA_BADGE;
        }
        return NONE;
    }

}
