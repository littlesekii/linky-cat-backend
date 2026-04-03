package cat.linky.linky_cat_api.core.service.auth;

import java.util.ArrayList;

import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.IntegrityViolationException;
import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthRegisterCommand;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthRegisterResult;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;
import cat.linky.linky_cat_api.core.ports.in.dto.user.UserResult;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthRegisterUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.security.PasswordEncoderPort;

public class AuthRegisterService implements AuthRegisterUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final ProfileRepositoryPort profileRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public AuthRegisterService(
        UserRepositoryPort userRepositoryPort, 
        ProfileRepositoryPort profileRepositoryPort,
        PasswordEncoderPort passwordEncoderPort
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.profileRepositoryPort = profileRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public AuthRegisterResult execute(AuthRegisterCommand command) {

        if (userRepositoryPort.existsByUsername(command.username())) {
            throw new IntegrityViolationException("this username is already taken");
        }

        if (userRepositoryPort.existsByEmail(command.email())) {
            throw new IntegrityViolationException("this email is already taken");
        }

        if (!command.password().equals(command.passwordConfirmation()))
            throw new InvalidArgumentException("passwords does not match");

        String encodedPassword = passwordEncoderPort.encode(command.password());

        User newUser = new User(command.username(), command.email(), encodedPassword);
        newUser = userRepositoryPort.save(newUser);
        
        Profile newProfile = new Profile(newUser.getId(), command.displayName(), command.bio());
        newProfile = profileRepositoryPort.save(newProfile);

        ProfileResult profileResult = new ProfileResult(
            newProfile.getId(), 
            newProfile.getDisplayName(), 
            newProfile.getBio(),
            newProfile.getProfileViews(), 
            new ArrayList<>()
        );

        UserResult userResult = new UserResult(
            newUser.getId(),
            newUser.getUsername(),
            newUser.getEmail(),
            profileResult
        );
            
        AuthRegisterResult result = new AuthRegisterResult(userResult);
        return result;
    }
}
