package cat.linky.linky_cat_api.core.service.auth;

import java.util.Map;

import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.InvalidCredentialsException;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthLoginCommand;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthLoginResult;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthLoginUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.security.AccessTokenPort;
import cat.linky.linky_cat_api.core.ports.out.security.PasswordEncoderPort;

public class AuthLoginService implements AuthLoginUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final AccessTokenPort accessTokenPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public AuthLoginService(
        UserRepositoryPort userRepositoryPort, 
        AccessTokenPort accessTokenPort, 
        PasswordEncoderPort passwordEncoderPort
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.accessTokenPort = accessTokenPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public AuthLoginResult execute(AuthLoginCommand command) {

        User existingUser = userRepositoryPort.findByUsername(command.username())
            .orElseThrow(() -> new InvalidCredentialsException());

        if (!passwordEncoderPort.matches(command.password(), existingUser.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = accessTokenPort.generateTokenWithClaims(
            existingUser.getUsername(), 
            Map.of("userId", existingUser.getId().toString())
        );

        AuthLoginResult result = new AuthLoginResult(existingUser.getUsername(), token);
        return result;
    }
}
