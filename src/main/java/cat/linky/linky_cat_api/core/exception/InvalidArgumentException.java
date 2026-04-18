package cat.linky.linky_cat_api.core.exception;

public class InvalidArgumentException extends ApplicationException {
    public InvalidArgumentException(String code, Object... args) {
        super(code, args);
    }   
}
