package christmas.controller;


import christmas.domain.Date;
import christmas.domain.Menu;
import christmas.view.InputView;

public class EventController {
    Date date;
    Menu menu;

    public EventController() {
        date = new Date();
        menu = new Menu();
    }

    public void start() {
        date.validateDate(InputView.readDate());
        menu.validateMenu(InputView.readMenu());
    }


}
