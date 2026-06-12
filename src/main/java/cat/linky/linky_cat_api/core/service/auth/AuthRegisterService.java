package cat.linky.linky_cat_api.core.service.auth;

import java.util.ArrayList;

import cat.linky.linky_cat_api.core.domain.EmailVerification;
import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.IntegrityViolationException;
import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthRegisterCommand;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthRegisterResult;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;
import cat.linky.linky_cat_api.core.ports.in.dto.user.UserResult;
import cat.linky.linky_cat_api.core.ports.in.usecase.auth.AuthRegisterUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.EmailVerificationRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.security.PasswordEncoderPort;

public class AuthRegisterService implements AuthRegisterUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final ProfileRepositoryPort profileRepositoryPort;
    private final EmailVerificationRepositoryPort emailVerificationRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public AuthRegisterService(
        UserRepositoryPort userRepositoryPort, 
        ProfileRepositoryPort profileRepositoryPort,
        EmailVerificationRepositoryPort emailVerificationRepositoryPort,
        PasswordEncoderPort passwordEncoderPort
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.profileRepositoryPort = profileRepositoryPort;
        this.emailVerificationRepositoryPort = emailVerificationRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public AuthRegisterResult execute(AuthRegisterCommand command) {
        String email = command.email().trim().toLowerCase();
        String username = command.username().trim().toLowerCase();
        String password = command.password();
        String displayName = command.displayName();
        String bio = command.bio();

        checkVerifiedEmail(email);
        checkExistingEmail(email);
        checkExistingUsername(username);
        User.validatePassword(password);

        String encodedPassword = passwordEncoderPort.encode(password);

        User newUser = new User(username, email, encodedPassword);
        newUser = userRepositoryPort.save(newUser);
        
        Profile newProfile = new Profile(newUser.getId(), displayName, bio);
        newProfile = profileRepositoryPort.save(newProfile);

        ProfileResult profileResult = new ProfileResult(
            newProfile.getId(), 
            newProfile.getDisplayName(), 
            newProfile.getBio(),
            newProfile.getImageUrl(),
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
    
    private void checkVerifiedEmail(String email) {
        EmailVerification emailVerification = emailVerificationRepositoryPort.findByEmail(email)
            .orElseThrow(() -> new InvalidArgumentException("service.auth.email_not_verified"));

        if (!emailVerification.getIsVerified()) 
            throw new InvalidArgumentException("service.auth.email_not_verified");
    }

    private void checkExistingUsername(String username) {
        if (userRepositoryPort.existsByUsername(username))
            throw new IntegrityViolationException("service.auth.existing_username");
    }

    private void checkExistingEmail(String email) {
        if (userRepositoryPort.existsByEmail(email))
            throw new IntegrityViolationException("service.auth.existing_email");
    }
}
