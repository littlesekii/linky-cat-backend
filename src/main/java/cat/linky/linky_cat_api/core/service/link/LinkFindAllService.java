package cat.linky.linky_cat_api.core.service.link;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.exception.ResourceNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkFindAllUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;

public class LinkFindAllService implements LinkFindAllUseCase {

    private final ProfileRepositoryPort profileRepositoryPort;
    private final LinkRepositoryPort repositoryPort;

    public LinkFindAllService(
        ProfileRepositoryPort profileRepositoryPort,
        LinkRepositoryPort repositoryPort
    ) {
        this.profileRepositoryPort = profileRepositoryPort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<LinkResult> execute(UUID userId) {
        Profile existingProfile = profileRepositoryPort.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("service.profile.not_found"));

        List<Link> existingLinks = repositoryPort.findAllByProfileId(existingProfile.getId());

        List<LinkResult> result = existingLinks.stream()
            .map(link -> new LinkResult(
                link.getId(), 
                link.getTitle(), 
                link.getUrl(), 
                link.getSortOrder(), 
                link.getClickCount(), 
                link.getIsActive()
            ))
            .toList();
            
        return result;
    }
    
}
