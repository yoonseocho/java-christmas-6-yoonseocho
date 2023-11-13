package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.constant.MessageConstant.INPUT_DATE_MESSAGE;
import static christmas.constant.MessageConstant.START_MESSAGE;
import static christmas.util.InputUtil.parseInputToInt;

public class InputView {
    public static int readDate() {
        System.out.println(START_MESSAGE);
        System.out.println(INPUT_DATE_MESSAGE);
        String input = Console.readLine();
        return parseInputToInt(input);
    }
}
