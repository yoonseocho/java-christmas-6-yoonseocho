package christmas.util;

import christmas.exception.AppException;
import christmas.exception.InvalidInputException;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static christmas.constant.PromotionConstant.*;
import static christmas.exception.ErrorMessage.*;

public class ParseUtil {
    private ParseUtil() {
    }

    public static int parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INVALID_MENU);
        }
    }

    public static int parseValue(String value) {
        if (value == null) {
            return ZERO;
        }
        return Integer.parseInt(value);
    }

    public static <T> T retryOnException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Map<String, String> parseKeyValuePairs(String input) {
        if (input == null) {
            throw new InvalidInputException(INVALID_MENU);
        }
        String[] pairs = input.split(PAIR_SEPARATOR);

        Map<String, String> result = new HashMap<>();
        for (String pair : pairs) {
            if (!(pair.contains(KEY_VALUE_SEPARATOR))) {
                throw new InvalidInputException(INVALID_MENU);
            }
            String[] keyValue = pair.split(KEY_VALUE_SEPARATOR);
            if (keyValue.length != KEY_VALUE_BOTH_EXIST) {
                throw new InvalidInputException(INVALID_MENU);
            }
            result.put(keyValue[KEY], keyValue[VALUE]);
        }
        return result;
    }

    public static String parsePrice(int price) {
        if (price / PRICE_OF_1000_WON != ZERO) {
            DecimalFormat df = new DecimalFormat("###,###");
            return df.format(price);
        }
        return Integer.toString(price);
    }

    public static void newLine() {
        System.out.println();
    }
}
