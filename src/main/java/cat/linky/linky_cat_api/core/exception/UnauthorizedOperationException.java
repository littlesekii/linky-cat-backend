package cat.linky.linky_cat_api.core.exception;

public class UnauthorizedOperationException extends RuntimeException {

    public final String errorCode = "OPR-001";

    public UnauthorizedOperationException() {
        super("you do not have permission to perform this action");
    }

    public UnauthorizedOperationException(String message) {
        super(message);
    }

    public String errorCode() {
        return errorCode;
    }
}
