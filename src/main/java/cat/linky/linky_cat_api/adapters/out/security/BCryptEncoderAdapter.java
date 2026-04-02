package cat.linky.linky_cat_api.adapters.out.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import cat.linky.linky_cat_api.core.ports.out.security.PasswordEncoderPort;

@Component
public class BCryptEncoderAdapter implements PasswordEncoderPort {

    private final BCryptPasswordEncoder passwordEncoder;

    public BCryptEncoderAdapter() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String password, String hash) {
        return passwordEncoder.matches(password, hash);
    }
}
