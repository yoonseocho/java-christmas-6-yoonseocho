package christmas.domain;

import christmas.exception.InvalidInputException;

import static christmas.constant.MessageConstant.MAXIMUM_OF_DATE_RANGE;
import static christmas.constant.MessageConstant.MINIMUM_OF_DATE_RANGE;
import static christmas.exception.ErrorMessage.INVALID_DATE;

public class DateValidator {
    public DateValidator() {
    }
    public int validateDate(int date) {
        return validateRangeOfDate(date);
    }

    public int validateRangeOfDate(int date) {
        if (!(date >= MINIMUM_OF_DATE_RANGE && date <= MAXIMUM_OF_DATE_RANGE)) {
            throw new InvalidInputException(INVALID_DATE);
        }
        return date;
    }
}
