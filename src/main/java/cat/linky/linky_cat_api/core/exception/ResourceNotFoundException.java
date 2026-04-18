package cat.linky.linky_cat_api.core.exception;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String code, Object... args) {
        super(code, args);
    }   
}
