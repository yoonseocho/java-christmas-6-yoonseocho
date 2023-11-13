package christmas.domain;

import christmas.exception.InvalidInputException;

import java.util.Map;

import static christmas.constant.MessageConstant.*;
import static christmas.exception.ErrorMessage.*;
import static christmas.util.InputUtil.parseStringToInt;
import static christmas.util.InputUtil.retryOnException;

public class Menu {
    public Menu() {
    }

    public Map<String, String> validateMenu(Map<String, String> menu) {
        return retryOnException(() -> {
            validateMenuExist(menu);
            validateNumberOfEachMenu(menu);
            validateOnlyDrink(menu);
            validateTotalNumberOfMenu(menu);

            return menu;
        });
    }

    public void validateMenuExist(Map<String, String> menu) {
        if (!(menu.containsKey(MUSHROOM_SOUP) || menu.containsKey(TAPAS) || menu.containsKey(SALAD) || menu.containsKey(STEAK) || menu.containsKey(RIB) || menu.containsKey(SEAFOOD_PASTA) || menu.containsKey(X_MAS_PASTA) || menu.containsKey(CAKE) || menu.containsKey(ICE_CREAM) || menu.containsKey(COKE) || menu.containsKey(WINE) || menu.containsKey(CHAMPAGNE))) {
            throw new InvalidInputException(INVALID_MENU);
        }
    }

    public void validateNumberOfEachMenu(Map<String, String> menu) {
        for (String number : menu.values()) {
            if (parseStringToInt(number) < LEAST_MENU_NUMBER) {
                throw new InvalidInputException(INVALID_MENU);
            }
        }
    }

    public void validateOnlyDrink(Map<String, String> menu) {
        if (!(menu.containsKey(MUSHROOM_SOUP) || menu.containsKey(TAPAS) || menu.containsKey(SALAD) || menu.containsKey(STEAK) || menu.containsKey(RIB) || menu.containsKey(SEAFOOD_PASTA) || menu.containsKey(X_MAS_PASTA) || menu.containsKey(CAKE) || menu.containsKey(ICE_CREAM))) {
            throw new InvalidInputException(INVALID_ONLY_DRINK);
        }
    }

    public void validateTotalNumberOfMenu(Map<String, String> menu) {
        int count = 0;
        for (String value : menu.values()) {
            count += parseStringToInt(value);
        }
        if (count > 20) {
            throw new InvalidInputException(INVALID_TOTAL_NUMBER_OF_MENU);
        }
    }
}
