package christmas.util;

import christmas.exception.AppException;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;

import java.util.function.Supplier;

public class InputUtil {
    private InputUtil() {
    }

    public static int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT);
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
}
