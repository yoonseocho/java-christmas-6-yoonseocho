package christmas.exception;


public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    private final String errorMessage;
    ErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getMessage(){
        return errorMessage;
    }
}
