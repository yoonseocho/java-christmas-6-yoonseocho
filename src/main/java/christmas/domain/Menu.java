package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    CHAMPAGNE("샴페인",25000),
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
