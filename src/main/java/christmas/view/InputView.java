package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static christmas.constant.MessageConstant.*;
import static christmas.util.InputUtil.parseStringToInt;
import static christmas.util.InputUtil.parseKeyValuePairs;

public class InputView {
    public static int readDate() {
        System.out.println(START_MESSAGE);
        System.out.println(INPUT_DATE_MESSAGE);
        String input = Console.readLine();
        return parseStringToInt(input);
    }
    public static Map<String, String> readMenu(){
        System.out.println(INPUT_MENU_MESSAGE);
        String input = Console.readLine();
        return parseKeyValuePairs(input);
    }
}
