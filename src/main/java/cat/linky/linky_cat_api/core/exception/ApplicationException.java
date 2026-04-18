package cat.linky.linky_cat_api.core.exception;

public class ApplicationException extends RuntimeException {
    protected final String code;
    protected final Object[] args;

    public ApplicationException(String code, Object... args) {
        super(code);
        this.code = code;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }
}
