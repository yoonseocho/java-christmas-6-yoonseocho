package christmas.domain;

import christmas.util.ParseUtil;

import java.util.Calendar;
import java.util.Map;

import static christmas.constant.MessageConstant.*;

public class DiscountEvent {
    Calendar calendar;
    private int dateOfEvent;

    public DiscountEvent(int dateOfEvent) {
        calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_2023, Calendar.DECEMBER, dateOfEvent);
        this.dateOfEvent = dateOfEvent;
    }

    public int calculateDiscountAmountByChristmasEvent() {
        int discount = XMAS_DEFAULT_DISCOUNT;
        if (dateOfEvent <= DATE_OF_XMAS) {
            discount += INCREASE_BY_100_WON * (dateOfEvent - 1);
        }
        return -discount;
    }

    public int calculateDiscountAmountByWeekEvent(Map<String, String> orderMenu) {
        int countOfDessert = ZERO;
        int discount = ZERO;
        if (calendar.get(Calendar.DAY_OF_WEEK) >= Calendar.SUNDAY && calendar.get(Calendar.DAY_OF_WEEK) <= Calendar.THURSDAY) {
            if (orderMenu.containsKey(Menu.CAKE.getName()) || orderMenu.containsKey(Menu.ICE_CREAM.getName())) {
                countOfDessert += ParseUtil.parseStringToInt(orderMenu.get(Menu.CAKE.getName()));
                countOfDessert += ParseUtil.parseStringToInt(orderMenu.get(Menu.ICE_CREAM.getName()));
            }
            discount = countOfDessert * WEEK_DEFAULT_DISCOUNT;
        }
        return -discount;
    }

    public int calculateDiscountAmountByWeekendEvent(Map<String, String> orderMenu) {
        int countOfMain = ZERO;
        int discount = ZERO;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            if (orderMenu.containsKey(Menu.STEAK.getName()) || orderMenu.containsKey(Menu.RIB.getName()) || orderMenu.containsKey(Menu.SEAFOOD_PASTA.getName()) || orderMenu.containsKey(Menu.XMAS_PASTA.getName())) {
                countOfMain += ParseUtil.parseStringToInt(orderMenu.get(Menu.STEAK.getName()));
                countOfMain += ParseUtil.parseStringToInt(orderMenu.get(Menu.RIB.getName()));
                countOfMain += ParseUtil.parseStringToInt(orderMenu.get(Menu.SEAFOOD_PASTA.getName()));
                countOfMain += ParseUtil.parseStringToInt(orderMenu.get(Menu.XMAS_PASTA.getName()));
            }
            discount = countOfMain * WEEKEND_DEFAULT_DISCOUNT;
        }
        return -discount;
    }
}
