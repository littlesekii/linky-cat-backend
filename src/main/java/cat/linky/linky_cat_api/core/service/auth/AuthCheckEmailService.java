package cat.linky.linky_cat_api.core.service.auth;

import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.IntegrityViolationException;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthCheckEmailUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

public class AuthCheckEmailService implements AuthCheckEmailUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public AuthCheckEmailService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void execute(String email) {
        email = email.trim().toLowerCase();

        User.validateEmail(email);

        if (userRepositoryPort.existsByEmail(email)) {
            throw new IntegrityViolationException("service.auth.existing_email");  
        }
    }
    
}
