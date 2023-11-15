package christmas.controller;


import christmas.domain.*;
import christmas.exception.InvalidInputException;
import christmas.util.ParseUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.constant.PromotionConstant.*;
import static christmas.exception.ErrorMessage.INVALID_DATE;

public class PromotionController {
    private final DateOfVisit dateOfVisit;
    private final MenuOrdered menuOrdered;
    private TotalPriceBeforeEvent totalPriceBeforeEvent;
    private int date;
    private Map<String, String> orderMenu;
    private int totalPrice;
    private boolean isTargetForFreebie;
    private int totalDiscountAmount = ZERO;

    public PromotionController() {
        dateOfVisit = new DateOfVisit();
        menuOrdered = new MenuOrdered();
    }

    public void start() {
        OutputView.printStartMessage();
        this.date = getDate();
        this.orderMenu = getOrderMenu();
        OutputView.printPreviewMessage(date);
        OutputView.printMenu(orderMenu);
        this.totalPrice = getTotalPrice();
        OutputView.printTotalPriceBeforeDiscount(totalPrice);
        this.isTargetForFreebie = totalPriceBeforeEvent.isTargetOfFreeEvent(totalPrice);
        printMenuOfFree();
        OutputView.printDiscountMessage();
        printDiscountEvent();
        OutputView.printTotalDiscountAmount(totalDiscountAmount);
        int totalPriceToPay = getTotalPriceToPay();
        OutputView.printTotalPriceAfterEvent(totalPriceToPay);
        BadgeOfDecember badgeOfDecember = new BadgeOfDecember(totalDiscountAmount);
        OutputView.printBadgeOfDecember(badgeOfDecember.getBadgeByTotalDiscountAmount());
    }

    public int getDate() {
        return ParseUtil.retryOnException(() -> {
            try{
                int date = Integer.parseInt(InputView.readDate());
                return dateOfVisit.validateDate(date);
            }catch (NumberFormatException e) {
                throw new InvalidInputException(INVALID_DATE);
            }
        });
    }

    public Map<String, String> getOrderMenu() {
        return ParseUtil.retryOnException(() -> {
            Map<String, String> orderMenu = ParseUtil.parseKeyValuePairs(InputView.readMenu());
            return this.menuOrdered.validateMenu(orderMenu);
        });
    }

    public int getTotalPrice() {
        totalPriceBeforeEvent = new TotalPriceBeforeEvent(orderMenu);
        return totalPriceBeforeEvent.calculatePrice(orderMenu);
    }

    public int getTotalPriceToPay() {
        TotalPriceAfterEvent totalPriceAfterEvent = new TotalPriceAfterEvent(totalPrice, totalDiscountAmount, orderMenu);
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
        PromotionEvent promotionEvent = new PromotionEvent(date, orderMenu);
        if (promotionEvent.hasDiscountOfChristmas()) {
            this.totalDiscountAmount += promotionEvent.calculateDiscountAmountByChristmasEvent();
            OutputView.printDiscountOfChristmas(promotionEvent.calculateDiscountAmountByChristmasEvent());
        }
        if (promotionEvent.hasDiscountOfWeek()) {
            this.totalDiscountAmount += promotionEvent.calculateDiscountAmountByWeekEvent();
            OutputView.printDiscountOfWeek(promotionEvent.calculateDiscountAmountByWeekEvent());
        }
        if (promotionEvent.hasDiscountOfWeekend()) {
            this.totalDiscountAmount += promotionEvent.calculateDiscountAmountByWeekendEvent();
            OutputView.printDiscountOfWeekend(promotionEvent.calculateDiscountAmountByWeekendEvent());
        }
        if (promotionEvent.hasDiscountOfSpecialDay()) {
            this.totalDiscountAmount += promotionEvent.calculateDiscountAmountBySpecialStarEvent();
            OutputView.printDiscountOfSpecialDay(promotionEvent.calculateDiscountAmountBySpecialStarEvent());
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