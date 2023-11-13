package christmas.exception;


public enum ErrorMessage {
    INVALID_INPUT("입력값은 숫자여야 합니다."),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    private final String errorMessage;
    ErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getMessage(){
        return errorMessage;
    }
}
