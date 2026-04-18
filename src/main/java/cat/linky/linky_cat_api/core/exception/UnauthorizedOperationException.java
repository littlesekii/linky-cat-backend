package cat.linky.linky_cat_api.core.exception;

public class UnauthorizedOperationException extends ApplicationException {
    public UnauthorizedOperationException(String code, Object... args) {
        super(code, args);
    }   
}