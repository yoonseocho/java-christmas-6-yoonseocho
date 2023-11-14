package christmas.domain;

import java.util.Map;

import static christmas.constant.MessageConstant.PRICE_OF_CHAMPAGNE;

public class TotalPriceAfterEvent {
    private int totalPrice;
    private int totalDiscountAmount;
    private Map<String,String> orderMenu;
    public TotalPriceAfterEvent(int totalPrice, int totalDiscountAmount, Map<String, String> orderMenu) {
        this.totalPrice = totalPrice;
        this.totalDiscountAmount = totalDiscountAmount;
        this.orderMenu = orderMenu;
    }
    public int calculateTotalPriceAfterEvent() {
        if(orderMenu.containsKey(Menu.CHAMPAGNE.getName())){
            return totalPrice + totalDiscountAmount;
        }
        return totalPrice + totalDiscountAmount + PRICE_OF_CHAMPAGNE;
    }
}
