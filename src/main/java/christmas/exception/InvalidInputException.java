package christmas.exception;

public class InvalidInputException extends AppException{
    public InvalidInputException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }
}
