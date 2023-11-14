package christmas.controller;


import christmas.domain.DateValidator;
import christmas.domain.MenuValidator;
import christmas.util.InputUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class EventController {
    DateValidator dateValidator;
    MenuValidator menuValidator;
    int dateOfEvent;
    Map<String, String> orderMenu;

    public EventController() {
        dateValidator = new DateValidator();
        menuValidator = new MenuValidator();
    }

    public void start() {
        OutputView.printStartMessage();
        this.dateOfEvent = getDate();
        this.orderMenu = getMenu();
        OutputView.printPreviewMessage(dateOfEvent);
        OutputView.printMenu(orderMenu);
        //OutputView.printTotalPriceBeforeDiscount();
    }
    public int getDate(){
        return InputUtil.retryOnException(()->{
            int dateOfEvent = InputUtil.parseStringToInt(InputView.readDate());
            return dateValidator.validateDate(dateOfEvent);
        });
    }
    public Map<String, String> getMenu(){
        return InputUtil.retryOnException(()->{
            Map<String, String> orderMenu = InputUtil.parseKeyValuePairs(InputView.readMenu());
            return menuValidator.validateMenu(orderMenu);
        });
    }
}
