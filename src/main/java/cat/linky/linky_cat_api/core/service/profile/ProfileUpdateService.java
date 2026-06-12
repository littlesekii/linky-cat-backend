package cat.linky.linky_cat_api.core.service.profile;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.exception.ResourceNotFoundException;
import cat.linky.linky_cat_api.core.exception.UnauthorizedOperationException;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileUpdateCommand;
import cat.linky.linky_cat_api.core.ports.in.usecase.profile.ProfileUpdateUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;

public class ProfileUpdateService implements ProfileUpdateUseCase {

    private final ProfileRepositoryPort repositoryPort;

    public ProfileUpdateService(ProfileRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public ProfileResult execute(UUID id, ProfileUpdateCommand command, UUID userId) {
        String displayName = command.displayName();
        String bio = command.bio();

        if (!repositoryPort.checkOwnership(id, userId))
            throw new UnauthorizedOperationException("authorization.unauthorized_operation");

        Profile existingProfile = repositoryPort.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("service.profile.not_found"));

        existingProfile.updateDisplayName(displayName);
        existingProfile.updateBio(bio);

        existingProfile = repositoryPort.save(existingProfile);

        ProfileResult result = new ProfileResult(
            null, 
            existingProfile.getDisplayName(), 
            existingProfile.getBio(), 
            null,
            null, 
            null
        );

        return result;
    }
    
}
