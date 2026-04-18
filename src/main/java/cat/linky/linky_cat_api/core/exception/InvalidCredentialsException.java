package cat.linky.linky_cat_api.core.exception;

public class InvalidCredentialsException extends ApplicationException {
    public InvalidCredentialsException(String code, Object... args) {
        super(code, args);
    }   
}