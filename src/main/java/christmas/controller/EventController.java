package christmas.controller;


import christmas.domain.Date;
import christmas.domain.Menu;

import java.util.Map;

import static christmas.view.InputView.readDate;
import static christmas.view.InputView.readMenu;
import static christmas.view.OutputView.printMenu;
import static christmas.view.OutputView.printPreviewMessage;

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
        this.dateOfEvent = date.validateDate(readDate());
        this.orderMenu = menu.validateMenu(readMenu());
        printPreviewMessage(dateOfEvent);
        printMenu(orderMenu);
    }


}
