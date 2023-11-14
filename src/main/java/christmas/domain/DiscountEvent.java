package christmas.domain;

import static christmas.constant.MessageConstant.DATE_Of_X_MAS;

public class DiscountEvent {
    public DiscountEvent() {
    }

    public int calculateDiscountAmountByChristmasEvent(int dateOfEvent) {
        int discount = 1000;
        if (dateOfEvent <= DATE_Of_X_MAS) {
            discount += 100 * (dateOfEvent - 1);
        }
        return -discount;
    }
}
