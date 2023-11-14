package christmas.controller;


import christmas.domain.*;
import christmas.util.ParseUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.constant.MessageConstant.*;

public class EventController {
    DateValidator dateValidator;
    MenuValidator menuValidator;
    TotalPriceBeforeEvent totalPriceBeforeEvent;
    DiscountEvent discountEvent;
    TotalPriceAfterEvent totalPriceAfterEvent;
    BadgeOfDecember badgeOfDecember;
    int dateOfEvent;
    Map<String, String> orderMenu;
    int totalPrice;
    boolean isFree;
    int totalDiscountAmount = ZERO;

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
        OutputView.printTotalDiscountAmount(totalDiscountAmount);
        totalPriceAfterEvent = new TotalPriceAfterEvent(totalPrice,totalDiscountAmount,orderMenu);
        OutputView.printTotalPriceAfterEvent(totalPriceAfterEvent.calculateTotalPriceAfterEvent());
        badgeOfDecember = new BadgeOfDecember(totalDiscountAmount);
        OutputView.printBadgeOfDecember(badgeOfDecember.getBadgeByTotalDiscountAmount());
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

    public void printMenuOfFree() {
        OutputView.printFreebieMessage();
        if (isFree) {
            OutputView.printMenuOfFree();
        }
        if (!isFree) {
            OutputView.printNothing();
        }
    }

    public void printDiscountEvent() {
        OutputView.printDiscountMessage();
        discountEvent = new DiscountEvent(dateOfEvent, orderMenu);
        if (totalPrice >= MINIMUM_AMOUNT_OF_EVENT) {
            if (discountEvent.hasDiscountOfChristmas()) {
                this.totalDiscountAmount += discountEvent.calculateDiscountAmountByChristmasEvent();
                OutputView.printDiscountOfChristmas(discountEvent.calculateDiscountAmountByChristmasEvent());
            }
            if (discountEvent.hasDiscountOfWeek()) {
                this.totalDiscountAmount += discountEvent.calculateDiscountAmountByWeekEvent();
                OutputView.printDiscountOfWeek(discountEvent.calculateDiscountAmountByWeekEvent());
            }
            if (discountEvent.hasDiscountOfWeekend()) {
                this.totalDiscountAmount += discountEvent.calculateDiscountAmountByWeekendEvent();
                OutputView.printDiscountOfWeekend(discountEvent.calculateDiscountAmountByWeekendEvent());
            }
            if (discountEvent.hasDiscountOfSpecialDay()) {
                this.totalDiscountAmount += discountEvent.calculateDiscountAmountBySpecialStarEvent();
                OutputView.printDiscountOfSpecialDay(discountEvent.calculateDiscountAmountBySpecialStarEvent());
            }
            if (isFree) {
                this.totalDiscountAmount += DISCOUNT_AMOUNT_OF_FREE_EVENT;
                OutputView.printDiscountByFreeEvent();
            }
        }
        if (totalPrice < MINIMUM_AMOUNT_OF_EVENT) {
            OutputView.printNothing();
        }
    }

}