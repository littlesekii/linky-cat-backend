package cat.linky.linky_cat_api.core.ports.in.usecase.profile;

import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileUpdateCommand;

public interface ProfileUpdateUseCase {
    public ProfileResult execute(UUID id, ProfileUpdateCommand command, UUID userId);
}
