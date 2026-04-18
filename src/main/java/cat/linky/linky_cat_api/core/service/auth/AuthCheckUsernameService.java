package cat.linky.linky_cat_api.core.service.auth;

import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.IntegrityViolationException;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthCheckUsernameUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

public class AuthCheckUsernameService implements AuthCheckUsernameUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public AuthCheckUsernameService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void execute(String username) {
        username = username.toLowerCase();

        User.validateUsername(username);
        
        if (userRepositoryPort.existsByUsername(username)) {
            throw new IntegrityViolationException("service.auth.existing_username");  
        }
    }
    
}
