package christmas.util;

import christmas.exception.AppException;
import christmas.exception.InvalidInputException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static christmas.exception.ErrorMessage.*;

public class InputUtil {
    private InputUtil() {
    }

    public static int parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(INVALID_INPUT);
        }
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
        if (!(input.contains(","))) {
            throw new InvalidInputException(INVALID_MENU);
        }
        String[] pairs = input.split(",");

        Map<String, String> result = new HashMap<>();
        for (String pair : pairs) {
            if (!(pair.contains("-"))) {
                throw new InvalidInputException(INVALID_MENU);
            }
            String[] keyValue = pair.split("-");
            if (keyValue.length != 2) {
                throw new InvalidInputException(INVALID_MENU);
            }
            if (keyValue.length == 2) {
                result.put(keyValue[0], keyValue[1]);
            }
        }
        return result;
    }
}
