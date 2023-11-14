package christmas.domain;

import christmas.util.InputUtil;

import java.util.Arrays;
import java.util.Map;

public class TotalPriceBeforeDiscount {
    public TotalPriceBeforeDiscount() {
    }

    public int calculatePrice(Map<String, String> orderMenu) {
        int sum = 0;
        for (String menuName : orderMenu.keySet()) {
            int eachMenuPrice = Menu.of(menuName).getPrice();
            int countOfMenu = InputUtil.parseStringToInt(orderMenu.get(menuName));
            sum += eachMenuPrice * countOfMenu;
        }
        return sum;
    }

}
