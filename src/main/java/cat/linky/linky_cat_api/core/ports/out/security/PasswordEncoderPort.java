package cat.linky.linky_cat_api.core.ports.out.security;

public interface PasswordEncoderPort {
    public String encode(String password);
    public boolean matches(String password, String hash);
}
