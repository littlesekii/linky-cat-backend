package cat.linky.linky_cat_api.core.exception.notFound;

public class EmailVerificationNotFound extends ResourceNotFoundException {

    public static final String errorCode = "EMV-404";

    public EmailVerificationNotFound() {
        super("email verification not found", errorCode);
    }

    public EmailVerificationNotFound(String message) {
        super(message, errorCode);
    }
}
