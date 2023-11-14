package christmas.controller;


import christmas.domain.DateValidator;
import christmas.domain.MenuValidator;
import christmas.domain.TotalPriceBeforeEvent;
import christmas.util.ParseUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class EventController {
    DateValidator dateValidator;
    MenuValidator menuValidator;
    TotalPriceBeforeEvent totalPriceBeforeEvent;
    int dateOfEvent;
    Map<String, String> orderMenu;
    int totalPrice;

    public EventController() {
        dateValidator = new DateValidator();
        menuValidator = new MenuValidator();
        totalPriceBeforeEvent = new TotalPriceBeforeEvent();
    }

    public void start() {
        OutputView.printStartMessage();
        this.dateOfEvent = getDateOfEvent();
        this.orderMenu = getOrderMenu();
        OutputView.printPreviewMessage(dateOfEvent);
        OutputView.printMenu(orderMenu);
        this.totalPrice = getTotalPrice();
        OutputView.printTotalPriceBeforeDiscount(totalPrice);
        OutputView.printMenuOfFree(totalPriceBeforeEvent.isTargetOfFreeEvent(totalPrice));
    }
    public int getDateOfEvent(){
        return ParseUtil.retryOnException(()->{
            int dateOfEvent = ParseUtil.parseStringToInt(InputView.readDate());
            return dateValidator.validateDate(dateOfEvent);
        });
    }
    public Map<String, String> getOrderMenu(){
        return ParseUtil.retryOnException(()->{
            Map<String, String> orderMenu = ParseUtil.parseKeyValuePairs(InputView.readMenu());
            return menuValidator.validateMenu(orderMenu);
        });
    }
    public int getTotalPrice(){
        return totalPriceBeforeEvent.calculatePrice(orderMenu);
    }
}
