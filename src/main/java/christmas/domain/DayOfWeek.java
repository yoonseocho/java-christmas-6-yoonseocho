package christmas.domain;

import java.util.Arrays;

public enum DayOfWeek {
    SUNDAY("일요일",1),
    MONDAY("월요일",2),
    TUESDAY("화요일",3),
    WEDNESDAY("수요일",4),
    THURSDAY("목요일",5),
    FRIDAY("금요일",6),
    SATURDAY("토요일",7),
    NONE("",0);
    private final String day;
    private final int value;
    DayOfWeek(String day, int value){
        this.day = day;
        this.value = value;
    }
    public String getDayOfWeek(){
        return day;
    }
    public int getValue(){
        return value;
    }
    public static DayOfWeek of(int value){
        return Arrays.stream(values())
                .filter(i->i.value==value)
                .findAny()
                .orElse(NONE);
    }
}
