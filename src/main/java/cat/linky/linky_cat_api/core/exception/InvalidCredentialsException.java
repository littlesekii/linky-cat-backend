package cat.linky.linky_cat_api.core.exception;

public class InvalidCredentialsException extends RuntimeException {

    public final String errorCode = "AUT-001";

    public InvalidCredentialsException() {
        super("invalid credentials");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public String errorCode() {
        return errorCode;
    }
}
