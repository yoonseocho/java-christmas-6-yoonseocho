package christmas.controller;


import christmas.exception.InvalidInputException;
import christmas.util.InputUtil;
import christmas.view.InputView;

import static christmas.constant.MessageConstant.*;
import static christmas.exception.ErrorMessage.INVALID_DATE;

public class EventController {
    public EventController() {
    }

    public void start() {
        getDate();
    }

    private int getDate() {
        return InputUtil.retryOnException(() -> {
            int date = InputView.readDate();
            return dateValidator(date);
        });
    }

    private int dateValidator(int date) {
        if (!(date >= MINIMUM_OF_DATE_RANGE && date <= MAXIMUM_OF_DATE_RANGE)) {
            throw new InvalidInputException(INVALID_DATE);
        }
        return date;
    }

}
