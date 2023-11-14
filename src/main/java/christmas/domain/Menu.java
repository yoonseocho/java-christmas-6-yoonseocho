package christmas.domain;

public enum Menu {
    MUSHROOM_SOUP("양송이수프",6000),
    TAPAS("타파스",5500),
    SALAD("시저샐러드",8000),
    STEAK("티본스테이크",55000),
    RIB("바비큐립",54000),
    SEAFOOD_PASTA("해산물파스타",35000),
    X_MAS_PASTA("크리스마스파스타",25000),
    CAKE("초코케이크",15000),
    ICE_CREAM("아이스크림",5000),
    COKE("제로콜라",3000),
    WINE("레드와인",60000),
    CHAMPAGNE("샴페인",25000);
    private final String orderMenu;
    private final int price;
    Menu(String orderMenu, int price){
        this.orderMenu = orderMenu;
        this.price = price;
    }
    public String getOrderMenu(){
        return orderMenu;
    }
    public int getPrice(){
        return price;
    }
}
