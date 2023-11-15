package christmas.domain;

import christmas.util.ParseUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static christmas.constant.PromotionConstant.*;

public class PromotionEvent {
    private final Calendar calendar;
    public PromotionEvent(int dateOfEvent) {
        calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_2023, Calendar.DECEMBER, dateOfEvent);
    }

    public int calculateTotalPromotionAmountExceptFreebie(int dateOfEvent, Map<String, String> orderMenu) {
        int sum = ZERO;
        sum += calculatePromotionAmountByChristmasEvent(dateOfEvent);
        sum += calculatePromotionAmountByWeekEvent(orderMenu);
        sum += calculatePromotionAmountByWeekendEvent(orderMenu);
        sum += calculatePromotionAmountBySpecialEvent(dateOfEvent);
        return sum;
    }

    public int calculatePromotionAmountByChristmasEvent(int dateOfEvent) {
        int discount = ZERO;
        if (dateOfEvent <= DATE_OF_XMAS) {
            discount += INCREASE_BY_100_WON * (dateOfEvent - 1) + XMAS_DEFAULT_DISCOUNT;
        }
        return -discount;
    }

    public int calculatePromotionAmountByWeekEvent(Map<String, String> orderMenu) {
        int countOfDessert = ZERO;
        int discount = ZERO;
        if (calendar.get(Calendar.DAY_OF_WEEK) >= Calendar.SUNDAY && calendar.get(Calendar.DAY_OF_WEEK) <= Calendar.THURSDAY) {
            if (orderMenu.containsKey(Menu.CAKE.getName()) || orderMenu.containsKey(Menu.ICE_CREAM.getName())) {
                countOfDessert += ParseUtil.parseValue(orderMenu.get(Menu.CAKE.getName()));
                countOfDessert += ParseUtil.parseValue(orderMenu.get(Menu.ICE_CREAM.getName()));
            }
            discount = countOfDessert * WEEK_DEFAULT_DISCOUNT;
        }
        return -discount;
    }

    public int calculatePromotionAmountByWeekendEvent(Map<String, String> orderMenu) {
        int countOfMain = ZERO;
        int discount = ZERO;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            if (orderMenu.containsKey(Menu.STEAK.getName()) || orderMenu.containsKey(Menu.RIB.getName()) || orderMenu.containsKey(Menu.SEAFOOD_PASTA.getName()) || orderMenu.containsKey(Menu.XMAS_PASTA.getName())) {
                countOfMain += ParseUtil.parseValue(orderMenu.get(Menu.STEAK.getName()));
                countOfMain += ParseUtil.parseValue(orderMenu.get(Menu.RIB.getName()));
                countOfMain += ParseUtil.parseValue(orderMenu.get(Menu.SEAFOOD_PASTA.getName()));
                countOfMain += ParseUtil.parseValue(orderMenu.get(Menu.XMAS_PASTA.getName()));
            }
            discount = countOfMain * WEEKEND_DEFAULT_DISCOUNT;
        }
        return -discount;
    }

    public int calculatePromotionAmountBySpecialEvent(int dateOfEvent) {
        List<Integer> specialDay = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));
        int discount = ZERO;
        if (specialDay.contains(dateOfEvent)) {
            discount += SPECIAL_DEFAULT_DISCOUNT;
        }
        return -discount;
    }

}
