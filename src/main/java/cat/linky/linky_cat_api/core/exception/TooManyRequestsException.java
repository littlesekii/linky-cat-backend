package cat.linky.linky_cat_api.core.exception;

public class TooManyRequestsException extends RuntimeException {

    public final String errorCode = "TMR-001";

    public TooManyRequestsException() {
        super("too many requests");
    }

    public TooManyRequestsException(String message) {
        super(message);
    }

    public String errorCode() {
        return errorCode;
    }
}
