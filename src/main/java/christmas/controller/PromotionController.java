package christmas.controller;


import christmas.domain.*;
import christmas.exception.InvalidInputException;
import christmas.util.ParseUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.exception.ErrorMessage.INVALID_DATE;

public class PromotionController {
    private TotalPriceBeforePromotion totalPriceBeforePromotion;
    private PromotionEvent promotionEvent;
    private TotalPriceAfterPromotion totalPriceAfterPromotion;
    private int date;
    private Map<String, String> orderMenu;
    private int totalPrice;
    private boolean freebie;
    private int totalPromotionAmountExceptFreebie;
    private int totalPromotionAmount;

    public PromotionController() {
    }

    public void start() {
        getInputFromCustomer();
        printMenuOrdered();
        printTotalPrice();
        printFreebie();
        printPromotionEvent();
        printPromotionAmount();
        printTotalPriceAfterPromotion();
        printBadgeOfDecember();
    }

    private void getInputFromCustomer() {
        OutputView.printStartMessage();
        this.date = getDate();
        this.orderMenu = getOrderMenu();
    }

    private void printMenuOrdered() {
        OutputView.printPreviewMessage(date);
        OutputView.printMenu(orderMenu);
    }

    private void printTotalPrice() {
        totalPriceBeforePromotion = new TotalPriceBeforePromotion(orderMenu);
        this.totalPrice = totalPriceBeforePromotion.calculatePrice(orderMenu);
        OutputView.printTotalPriceBeforeDiscount(totalPrice);
    }

    private void printFreebie() {
        this.freebie = totalPriceBeforePromotion.isTargetOfFreebie();
        OutputView.printFreebieMessage();
        if (freebie) {
            OutputView.printMenuOfFree();
        }
        if (!freebie) {
            OutputView.printNothing();
        }
    }

    private void printPromotionEvent() {
        OutputView.printPromotionMessage();
        boolean promotionAvailable = totalPriceBeforePromotion.isAvailableForPromotionEvent();
        if (promotionAvailable) {
            printEachPromotion();
            this.totalPromotionAmountExceptFreebie = promotionEvent.calculateTotalPromotionAmountExceptFreebie(date,orderMenu);
        }
        if (!promotionAvailable) {
            OutputView.printNothing();
        }
    }

    private void printPromotionAmount() {
        totalPriceAfterPromotion = new TotalPriceAfterPromotion(totalPromotionAmount,freebie);
        this.totalPromotionAmount = totalPriceAfterPromotion.calculateTotalPromotionAmount(totalPromotionAmountExceptFreebie, freebie);
        OutputView.printTotalPromotionAmount(totalPromotionAmount);
    }

    private void printTotalPriceAfterPromotion() {
        OutputView.printTotalPriceAfterEvent(totalPriceAfterPromotion.calculateTotalPriceAfterPromotion(totalPrice,orderMenu));
    }

    private void printBadgeOfDecember() {
        BadgeOfDecember badgeOfDecember = new BadgeOfDecember(totalPromotionAmount);
        OutputView.printBadgeOfDecember(badgeOfDecember.getBadgeByTotalPromotionAmount());
    }

    private int getDate() {
        return ParseUtil.retryOnException(() -> {
            try {
                int date = Integer.parseInt(InputView.readDate());
                return new DateOfVisit(date).getDate();
            } catch (NumberFormatException e) {
                throw new InvalidInputException(INVALID_DATE);
            }
        });
    }

    private Map<String, String> getOrderMenu() {
        return ParseUtil.retryOnException(() -> {
            Map<String, String> orderMenu = ParseUtil.parseKeyValuePairs(InputView.readMenu());
            return new MenuOrdered(orderMenu).getMenu();
        });
    }

    private void printEachPromotion() {
        promotionEvent = new PromotionEvent(date);
        OutputView.printPromotionOfChristmas(promotionEvent.calculatePromotionAmountByChristmasEvent(date));
        OutputView.printPromotionOfWeek(promotionEvent.calculatePromotionAmountByWeekEvent(orderMenu));
        OutputView.printPromotionOfWeekend(promotionEvent.calculatePromotionAmountByWeekendEvent(orderMenu));
        OutputView.printPromotionOfSpecialDay(promotionEvent.calculatePromotionAmountBySpecialEvent(date));
        if (freebie) {
            OutputView.printPromotionByFreeEvent();
        }
    }
}