package christmas.view;

import java.util.Map;

public class OutputView {
    public static void printPreviewMessage(int date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n%n", date);
    }

    public static void printMenu(Map<String, String> menu) {
        System.out.println("<주문 메뉴>");

        for (Map.Entry<String, String> elem : menu.entrySet()) {
            System.out.println(elem.getKey() + " " + elem.getValue() + "개");
        }
    }
}
