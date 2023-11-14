package christmas.controller;


import christmas.domain.*;
import christmas.exception.InvalidInputException;
import christmas.util.ParseUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.constant.MessageConstant.*;
import static christmas.exception.ErrorMessage.INVALID_DATE;
import static christmas.exception.ErrorMessage.INVALID_MENU;

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
    boolean isTargetForFreebie;
    int totalDiscountAmount = ZERO;
    int totalPriceToPay;

    public EventController() {
        dateValidator = new DateValidator();
        menuValidator = new MenuValidator();
    }

    public void start() {
        OutputView.printStartMessage();
        this.dateOfEvent = getDateOfEvent();
        this.orderMenu = getOrderMenu();
        OutputView.printPreviewMessage(dateOfEvent);
        OutputView.printMenu(orderMenu);
        this.totalPrice = getTotalPrice();
        OutputView.printTotalPriceBeforeDiscount(totalPrice);
        this.isTargetForFreebie = totalPriceBeforeEvent.isTargetOfFreeEvent(totalPrice);
        printMenuOfFree();
        OutputView.printDiscountMessage();
        printDiscountEvent();
        OutputView.printTotalDiscountAmount(totalDiscountAmount);
        this.totalPriceToPay = getTotalPriceToPay();
        OutputView.printTotalPriceAfterEvent(totalPriceToPay);
        badgeOfDecember = new BadgeOfDecember(totalDiscountAmount);
        OutputView.printBadgeOfDecember(badgeOfDecember.getBadgeByTotalDiscountAmount());
    }

    public int getDateOfEvent() {
        return ParseUtil.retryOnException(() -> {
            int dateOfEvent;
            try{
                dateOfEvent = Integer.parseInt(InputView.readDate());
            }catch (NumberFormatException e) {
                throw new InvalidInputException(INVALID_DATE);
            }
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
        totalPriceBeforeEvent = new TotalPriceBeforeEvent(orderMenu);
        return totalPriceBeforeEvent.calculatePrice(orderMenu);
    }

    public int getTotalPriceToPay() {
        totalPriceAfterEvent = new TotalPriceAfterEvent(totalPrice, totalDiscountAmount, orderMenu);
        return totalPriceAfterEvent.calculateTotalPriceAfterEvent();
    }

    public void printMenuOfFree() {
        OutputView.printFreebieMessage();
        if (isTargetForFreebie) {
            OutputView.printMenuOfFree();
        }
        if (!isTargetForFreebie) {
            OutputView.printNothing();
        }
    }

    public void printEachDiscountEvent() {
        discountEvent = new DiscountEvent(dateOfEvent, orderMenu);
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
        if (isTargetForFreebie) {
            this.totalDiscountAmount += DISCOUNT_AMOUNT_OF_FREE_EVENT;
            OutputView.printDiscountByFreeEvent();
        }
    }

    public void printDiscountEvent() {
        if (totalPriceBeforeEvent.isAvailableForDiscountEvent()) {
            printEachDiscountEvent();
        }
        if (!totalPriceBeforeEvent.isAvailableForDiscountEvent()) {
            OutputView.printNothing();
        }
    }

}