package cat.linky.linky_cat_api.core.service.profile;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.exception.UnauthorizedOperationException;
import cat.linky.linky_cat_api.core.exception.notFound.ProfileNotFoundException;
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
        if (!repositoryPort.checkOwnership(id, userId))
            throw new UnauthorizedOperationException();

        Profile existingProfile = repositoryPort.findById(id)
            .orElseThrow(() -> new ProfileNotFoundException());

        existingProfile.updateDisplayName(command.displayName());
        existingProfile.updateBio(command.bio());

        existingProfile = repositoryPort.save(existingProfile);

        ProfileResult result = new ProfileResult(
            null, 
            existingProfile.getDisplayName(), 
            existingProfile.getBio(), 
            null, 
            null
        );

        return result;
    }
    
}
