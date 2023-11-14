package christmas.domain;

import christmas.util.ParseUtil;

import java.util.Calendar;
import java.util.Map;

import static christmas.constant.MessageConstant.*;

public class DiscountEvent {
    public DiscountEvent() {
    }

    public int calculateDiscountAmountByChristmasEvent(int dateOfEvent) {
        int discount = XMAS_DEFAULT_DISCOUNT;
        if (dateOfEvent <= DATE_OF_XMAS) {
            discount += INCREASE_BY_100_WON * (dateOfEvent - 1);
        }
        return -discount;
    }

    public int calculateDiscountAmountByWeekEvent(int dateOfEvent, Map<String, String> orderMenu) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_2023, Calendar.DECEMBER, dateOfEvent);

        int countOfDessert = ZERO;
        int discount = ZERO;
        if (calendar.get(Calendar.DAY_OF_WEEK) >= SUNDAY && calendar.get(Calendar.DAY_OF_WEEK) <= THURSDAY) {
            if(orderMenu.containsKey(Menu.CAKE.getName()) || orderMenu.containsKey(Menu.ICE_CREAM.getName())){
                countOfDessert += ParseUtil.parseStringToInt(orderMenu.get(Menu.CAKE.getName()));
                countOfDessert += ParseUtil.parseStringToInt(orderMenu.get(Menu.ICE_CREAM.getName()));
            }
            discount = countOfDessert*WEEK_DEFAULT_DISCOUNT;
        }
        return -discount;
    }
}
