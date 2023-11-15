package christmas.domain;

import java.util.Map;

import static christmas.constant.PromotionConstant.*;

public class TotalPriceBeforeEvent {
    private final int totalPrice;

    public TotalPriceBeforeEvent(Map<String, String> orderMenu) {
        this.totalPrice = calculatePrice(orderMenu);
    }

    public int calculatePrice(Map<String, String> orderMenu) {
        int sum = ZERO;
        for (String menuName : orderMenu.keySet()) {
            int eachMenuPrice = Menu.of(menuName).getPrice();
            int countOfMenu = Integer.parseInt(orderMenu.get(menuName));
            sum += eachMenuPrice * countOfMenu;
        }
        return sum;
    }

    public boolean isTargetOfFreeEvent(int totalPriceBeforeDiscount) {
        return totalPriceBeforeDiscount > MINIMUM_AMOUNT_OF_FREE_EVENT;
    }

    public boolean isAvailableForDiscountEvent() {
        return totalPrice >= MINIMUM_AMOUNT_OF_EVENT;
    }

}
