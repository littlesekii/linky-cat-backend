package cat.linky.linky_cat_api.core.service.auth;

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
        email = email.toLowerCase();

        if (userRepositoryPort.existsByEmail(email)) {
            throw new IntegrityViolationException("this email is already taken");  
        }
    }
    
}
