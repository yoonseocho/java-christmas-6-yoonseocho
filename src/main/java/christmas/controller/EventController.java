package christmas.controller;


import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.util.InputUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class EventController {
    Date date;
    Menu menu;
    int dateOfEvent;
    Map<String, String> orderMenu;

    public EventController() {
        date = new Date();
        menu = new Menu();
    }

    public void start() {
        OutputView.printStartMessage();
        this.dateOfEvent = getDate();
        this.orderMenu = getMenu();
        OutputView.printPreviewMessage(dateOfEvent);
        OutputView.printMenu(orderMenu);
    }
    public int getDate(){
        return InputUtil.retryOnException(()->{
            int dateOfEvent = InputUtil.parseStringToInt(InputView.readDate());
            return date.validateDate(dateOfEvent);
        });
    }
    public Map<String, String> getMenu(){
        return InputUtil.retryOnException(()->{
            Map<String, String> orderMenu = InputUtil.parseKeyValuePairs(InputView.readMenu());
            return menu.validateMenu(orderMenu);
        });
    }


}
