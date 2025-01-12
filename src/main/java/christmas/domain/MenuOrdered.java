package christmas.domain;

import christmas.exception.InvalidInputException;
import christmas.util.ParseUtil;

import java.util.Map;

import static christmas.constant.PromotionConstant.*;
import static christmas.exception.ErrorMessage.*;

public class MenuOrdered {
    private final Map<String, String> orderMenu;

    public MenuOrdered(Map<String, String> orderMenu) {
        validateMenuExist(orderMenu);
        validateNumberOfEachMenu(orderMenu);
        validateOnlyDrink(orderMenu);
        validateTotalNumberOfMenu(orderMenu);
        this.orderMenu = orderMenu;
    }

    public Map<String, String> getMenu() {
        return orderMenu;
    }

    private void validateMenuExist(Map<String, String> orderMenu) {
        Menu[] menu = Menu.values();
        orderMenu.keySet().forEach(each -> {
            boolean isExist = false;
            for (Menu value : menu) {
                if (each.equals(value.getName())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                throw new InvalidInputException(INVALID_MENU);
            }
        });
    }

    private void validateNumberOfEachMenu(Map<String, String> orderMenu) {
        for (String number : orderMenu.values()) {
            if (ParseUtil.parseStringToInt(number) < LEAST_MENU_NUMBER) {
                throw new InvalidInputException(INVALID_MENU);
            }
        }
    }

    private void validateOnlyDrink(Map<String, String> orderMenu) {
        Menu[] menu = Menu.values();
        boolean isOnlyDrink;
        isOnlyDrink = orderMenu.keySet().stream().allMatch(each -> {
            for (int i = 0; i < menu.length - DRINKS; i++) {
                if (each.equals(menu[i].getName())) {
                    return false;
                }
            }
            return true;
        });
        if (isOnlyDrink) {
            throw new InvalidInputException(INVALID_MENU);
        }
    }

    private void validateTotalNumberOfMenu(Map<String, String> orderMenu) {
        int count = ZERO;
        for (String value : orderMenu.values()) {
            count += ParseUtil.parseStringToInt(value);
        }
        if (count > MAXIMUM_QUANTITY_OF_ORDER_AVAILABLE) {
            throw new InvalidInputException(INVALID_MENU);
        }
    }
}
