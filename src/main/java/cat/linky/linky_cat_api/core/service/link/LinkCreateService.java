package cat.linky.linky_cat_api.core.service.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.exception.notFound.ProfileNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkCreateCommand;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkCreateUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;

public class LinkCreateService implements LinkCreateUseCase {

    private final ProfileRepositoryPort profileRepositoryPort;
    private final LinkRepositoryPort repositoryPort;

    public LinkCreateService(
        ProfileRepositoryPort profileRepositoryPort,
        LinkRepositoryPort repositoryPort
    ) {
        this.profileRepositoryPort = profileRepositoryPort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public LinkResult execute(LinkCreateCommand command, UUID userId) {
        Profile existingProfile = profileRepositoryPort.findByUserId(userId)
            .orElseThrow(() -> new ProfileNotFoundException());

        Link newLink = new Link(
            existingProfile.getId(), 
            command.title(), 
            command.url(), 
            command.sortOrder(), 
            command.isActive()
        );

        newLink = repositoryPort.save(newLink);

        LinkResult result = new LinkResult(
            newLink.getId(), 
            newLink.getTitle(), 
            newLink.getUrl(), 
            newLink.getSortOrder(), 
            newLink.getClickCount(), 
            newLink.getIsActive()
        );

        return result;
    }    
}
