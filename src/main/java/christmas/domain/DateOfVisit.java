package christmas.domain;

import christmas.exception.InvalidInputException;

import static christmas.constant.PromotionConstant.*;
import static christmas.exception.ErrorMessage.INVALID_DATE;

public class DateOfVisit {
    private final int date;
    public DateOfVisit(int date) {
        this.date = validateRangeOfDate(date);
    }
    public int getDate() {
        return date;
    }

    private int validateRangeOfDate(int date) {
        if (!(date >= FIRST_OF_DECEMBER && date <= LAST_OF_DECEMBER)) {
            throw new InvalidInputException(INVALID_DATE);
        }
        return date;
    }
}
