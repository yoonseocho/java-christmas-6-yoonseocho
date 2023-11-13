package christmas.exception;


public enum ErrorMessage {
    INVALID_INPUT("입력값은 숫자여야 합니다."),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_ONLY_DRINK("음료만 주문 시, 주문할 수 없습니다."),
    INVALID_TOTAL_NUMBER_OF_MENU("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
    private final String errorMessage;
    ErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getMessage(){
        return errorMessage;
    }
}
