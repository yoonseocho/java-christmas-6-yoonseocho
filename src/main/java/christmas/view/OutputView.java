package christmas.view;

import christmas.util.ParseUtil;

import java.util.Map;

import static christmas.constant.MessageConstant.START_MESSAGE;

public class OutputView {
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

    public static void printMenuOfFree(boolean isTarget) {
        System.out.println("<증정 메뉴>");
        if (isTarget) {
            System.out.println("샴페인 1개");
        }
        if (!isTarget) {
            System.out.println("없음");
        }
        ParseUtil.newLine();
    }
}
