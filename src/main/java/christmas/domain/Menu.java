package christmas.domain;

import java.util.Arrays;
public enum Menu {
    MUSHROOM_SOUP("양송이수프",6_000),
    TAPAS("타파스",5_500),
    SALAD("시저샐러드",8_000),
    STEAK("티본스테이크",55_000),
    RIB("바비큐립",54_000),
    SEAFOOD_PASTA("해산물파스타",35_000),
    XMAS_PASTA("크리스마스파스타",25_000),
    CAKE("초코케이크",15_000),
    ICE_CREAM("아이스크림",5_000),
    COKE("제로콜라",3_000),
    WINE("레드와인",60_000),
    CHAMPAGNE("샴페인",25_000),
    NONE("",0);
    private final String name;
    private final int price;
    Menu(String name, int price){
        this.name = name;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public static Menu of(String name){
        return Arrays.stream(values())
                .filter(i -> i.name.equals(name))
                .findAny()
                .orElse(NONE);
    }
}
