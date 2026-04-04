package cat.linky.linky_cat_api.core.service.profile;

import java.util.List;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.notFound.ProfileNotFoundException;
import cat.linky.linky_cat_api.core.exception.notFound.UserNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;
import cat.linky.linky_cat_api.core.ports.in.usecase.profile.ProfileFetchByUsernameUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

public class ProfileFetchByUsernameService implements ProfileFetchByUsernameUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final ProfileRepositoryPort repositoryPort;
    private final LinkRepositoryPort linkRepositoryPort;

    public ProfileFetchByUsernameService(
        UserRepositoryPort userRepositoryPort, 
        ProfileRepositoryPort repositoryPort, 
        LinkRepositoryPort linkRepositoryPort
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.repositoryPort = repositoryPort;
        this.linkRepositoryPort = linkRepositoryPort;
    }

    @Override
    public ProfileResult execute(String username) {

        User existingUser = userRepositoryPort.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException());

        Profile existingProfile = repositoryPort.findByUserId(existingUser.getId())
            .orElseThrow(() -> new ProfileNotFoundException());

        List<Link> existingLinks = linkRepositoryPort.findAllByProfileId(existingProfile.getId());

        List<LinkResult> linkResultList = existingLinks.stream()
            .map((link) -> new LinkResult(
                link.getId(), 
                link.getTitle(),
                link.getUrl(),
                null,
                null,
                null
            ))
            .toList();

        ProfileResult result = new ProfileResult(
            null,
            existingProfile.getDisplayName(), 
            existingProfile.getBio(), 
            null,
            linkResultList
        );

        return result;
    }
}
