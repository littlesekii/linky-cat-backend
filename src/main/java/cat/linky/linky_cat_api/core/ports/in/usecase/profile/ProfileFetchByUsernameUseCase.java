package cat.linky.linky_cat_api.core.ports.in.usecase.profile;

import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;

public interface ProfileFetchByUsernameUseCase {
    public ProfileResult execute(String username);
}
