package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.constant.PromotionConstant.*;


public class InputView {
    private InputView() {

    }

    public static String readDate() {
        System.out.println(INPUT_DATE_MESSAGE);
        return Console.readLine();

    }

    public static String readMenu() {
        System.out.println(INPUT_MENU_MESSAGE);
        return Console.readLine();
    }
}
