package cat.linky.linky_cat_api.core.exception.notFound;

public class LinkNotFoundException extends ResourceNotFoundException {

    public static final String errorCode = "LNK-404";

    public LinkNotFoundException() {
        super("link not found", errorCode);
    }

    public LinkNotFoundException(String message) {
        super(message, errorCode);
    }
}
