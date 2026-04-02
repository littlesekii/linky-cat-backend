package cat.linky.linky_cat_api.core.exception.notFound;

public class ProfileNotFoundException extends ResourceNotFoundException {

    public static final String errorCode = "PFL-404";

    public ProfileNotFoundException() {
        super("profile not found", errorCode);
    }

    public ProfileNotFoundException(String message) {
        super(message, errorCode);
    }
}
