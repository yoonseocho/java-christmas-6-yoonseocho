package christmas.domain;

import christmas.util.ParseUtil;

import java.util.Map;

import static christmas.constant.MessageConstant.MINIMUM_AMOUNT_OF_FREE_EVENT;

public class TotalPriceBeforeEvent {
    public TotalPriceBeforeEvent() {
    }

    public int calculatePrice(Map<String, String> orderMenu) {
        int sum = 0;
        for (String menuName : orderMenu.keySet()) {
            int eachMenuPrice = Menu.of(menuName).getPrice();
            int countOfMenu = ParseUtil.parseStringToInt(orderMenu.get(menuName));
            sum += eachMenuPrice * countOfMenu;
        }
        return sum;
    }

    public boolean isTargetOfFreeEvent(int totalPriceBeforeDiscount) {
        return totalPriceBeforeDiscount > MINIMUM_AMOUNT_OF_FREE_EVENT;
    }

}
