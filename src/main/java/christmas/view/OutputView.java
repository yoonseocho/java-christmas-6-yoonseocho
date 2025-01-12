package christmas.view;

import christmas.util.ParseUtil;

import java.util.Map;

import static christmas.constant.PromotionConstant.START_MESSAGE;

public class OutputView {
    private OutputView() {

    }

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printPreviewMessage(int date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", date);
    }

    public static void printMenu(Map<String, String> menu) {
        System.out.println("<주문 메뉴>");

        for (Map.Entry<String, String> elem : menu.entrySet()) {
            System.out.println(elem.getKey() + " " + elem.getValue() + "개");
        }
        ParseUtil.newLine();
    }

    public static void printTotalPriceBeforeDiscount(int sum) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(ParseUtil.parsePrice(sum) + "원");
        ParseUtil.newLine();
    }

    public static void printFreebieMessage() {
        System.out.println("<증정 메뉴>");
    }

    public static void printMenuOfFree() {
        System.out.println("샴페인 1개");
        ParseUtil.newLine();
    }

    public static void printNothing() {
        System.out.println("없음");
        ParseUtil.newLine();
    }

    public static void printPromotionMessage() {
        System.out.println("<혜택 내역>");
    }

    public static void printPromotionOfChristmas(int discount) {
        if (discount != 0) {
            System.out.println("크리스마스 디데이 할인: " + ParseUtil.parsePrice(discount) + "원");
        }
    }

    public static void printPromotionOfWeek(int discount) {
        if (discount != 0) {
            System.out.println("평일 할인: " + ParseUtil.parsePrice(discount) + "원");
        }
    }

    public static void printPromotionOfWeekend(int discount) {
        if (discount != 0) {
            System.out.println("주말 할인: " + ParseUtil.parsePrice(discount) + "원");
        }
    }

    public static void printPromotionOfSpecialDay(int discount) {
        if (discount != 0) {
            System.out.println("특별 할인: " + ParseUtil.parsePrice(discount) + "원");
        }
    }

    public static void printPromotionByFreeEvent() {
        System.out.println("증정 이벤트: -25,000원");
        ParseUtil.newLine();
    }

    public static void printTotalPromotionAmount(int totalDiscountAmount) {
        System.out.println("<총혜택 금액>");
        System.out.println(ParseUtil.parsePrice(totalDiscountAmount) + "원");
        ParseUtil.newLine();
    }

    public static void printTotalPriceAfterEvent(int totalPriceAfterEvent) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(ParseUtil.parsePrice(totalPriceAfterEvent) + "원");
        ParseUtil.newLine();
    }

    public static void printBadgeOfDecember(String badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }
}
