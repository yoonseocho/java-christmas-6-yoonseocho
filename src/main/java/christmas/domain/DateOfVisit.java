package christmas.domain;

import christmas.exception.InvalidInputException;

import static christmas.constant.PromotionConstant.*;
import static christmas.exception.ErrorMessage.INVALID_DATE;

public class DateOfVisit {
    public DateOfVisit() {
    }
    public int validateDate(int date) {
        return validateRangeOfDate(date);
    }

    public int validateRangeOfDate(int date) {
        if (!(date >= FIRST_OF_DECEMBER && date <= LAST_OF_DECEMBER)) {
            throw new InvalidInputException(INVALID_DATE);
        }
        return date;
    }
}
