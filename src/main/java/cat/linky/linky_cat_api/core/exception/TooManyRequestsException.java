package cat.linky.linky_cat_api.core.exception;

public class TooManyRequestsException extends ApplicationException {
    public TooManyRequestsException(String code, Object... args) {
        super(code, args);
    }   
}
