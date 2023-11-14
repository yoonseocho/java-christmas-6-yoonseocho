package christmas.controller;


import christmas.domain.DateValidator;
import christmas.domain.DiscountEvent;
import christmas.domain.MenuValidator;
import christmas.domain.TotalPriceBeforeEvent;
import christmas.util.ParseUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.constant.MessageConstant.MINIMUM_AMOUNT_OF_EVENT;

public class EventController {
    DateValidator dateValidator;
    MenuValidator menuValidator;
    TotalPriceBeforeEvent totalPriceBeforeEvent;
    DiscountEvent discountEvent;
    int dateOfEvent;
    Map<String, String> orderMenu;
    int totalPrice;
    boolean isFree;

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
        this.isFree = totalPriceBeforeEvent.isTargetOfFreeEvent(totalPrice);
        printMenuOfFree();
        printDiscountEvent();
    }

    public int getDateOfEvent() {
        return ParseUtil.retryOnException(() -> {
            int dateOfEvent = ParseUtil.parseStringToInt(InputView.readDate());
            return dateValidator.validateDate(dateOfEvent);
        });
    }

    public Map<String, String> getOrderMenu() {
        return ParseUtil.retryOnException(() -> {
            Map<String, String> orderMenu = ParseUtil.parseKeyValuePairs(InputView.readMenu());
            return menuValidator.validateMenu(orderMenu);
        });
    }

    public int getTotalPrice() {
        return totalPriceBeforeEvent.calculatePrice(orderMenu);
    }
    public void printMenuOfFree(){
        OutputView.printFreebieMessage();
        if(isFree){
            OutputView.printMenuOfFree();
        }
        if(!isFree){
            OutputView.printNothing();
        }
    }

    public void printDiscountEvent() {
        OutputView.printDiscountMessage();
        discountEvent = new DiscountEvent(dateOfEvent, orderMenu);
        if (totalPrice >= MINIMUM_AMOUNT_OF_EVENT) {
            if (discountEvent.hasDiscountOfChristmas()) {
                OutputView.printDiscountOfChristmas(discountEvent.calculateDiscountAmountByChristmasEvent());
            }
            if (discountEvent.hasDiscountOfWeek()) {
                OutputView.printDiscountOfWeek(discountEvent.calculateDiscountAmountByWeekEvent());
            }
            if (discountEvent.hasDiscountOfWeekend()) {
                OutputView.printDiscountOfWeekend(discountEvent.calculateDiscountAmountByWeekendEvent());
            }
            if (discountEvent.hasDiscountOfSpecialDay()) {
                OutputView.printDiscountOfSpecialDay(discountEvent.calculateDiscountAmountBySpecialStarEvent());
            }
            if (isFree) {
                OutputView.printDiscountByFreeEvent();
            }
        }
        if(totalPrice<MINIMUM_AMOUNT_OF_EVENT){
            OutputView.printNothing();
        }

    }
}