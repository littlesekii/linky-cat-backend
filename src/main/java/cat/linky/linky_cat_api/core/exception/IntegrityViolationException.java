package cat.linky.linky_cat_api.core.exception;

public class IntegrityViolationException extends ApplicationException {
    public IntegrityViolationException(String code, Object... args) {
        super(code, args);
    }   
}
