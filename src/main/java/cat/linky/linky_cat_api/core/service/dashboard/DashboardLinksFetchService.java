package cat.linky.linky_cat_api.core.service.dashboard;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.exception.ResourceNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.dto.dashboard.DashboardLinksResult;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;
import cat.linky.linky_cat_api.core.ports.in.usecase.dashboard.DashboardLinksFetchUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;

public class DashboardLinksFetchService implements DashboardLinksFetchUseCase {

    private final ProfileRepositoryPort profileRepositoryPort;

    public DashboardLinksFetchService(
        ProfileRepositoryPort profileRepositoryPort,
        LinkRepositoryPort linkRepositoryPort
    ) {
        this.profileRepositoryPort = profileRepositoryPort;
        this.linkRepositoryPort = linkRepositoryPort;
    }

    private final LinkRepositoryPort linkRepositoryPort;

    @Override
    public DashboardLinksResult execute(UUID userId) {

        Profile existingProfile = profileRepositoryPort.findByUserId(userId)
         .orElseThrow(() -> new ResourceNotFoundException("service.profile.not_found"));

        List<Link> existingLinks = linkRepositoryPort.findAllByProfileId(existingProfile.getId());

        List<LinkResult> linkResultList = existingLinks.stream()
            .map(link -> new LinkResult(
                link.getId(), 
                link.getTitle(), 
                link.getUrl(), 
                link.getSortOrder(), 
                link.getClickCount(), 
                link.getIsActive()
            ))
            .toList();

        DashboardLinksResult result = new DashboardLinksResult(
            existingProfile.getId(),
            existingProfile.getDisplayName(), 
            existingProfile.getBio(), 
            linkResultList
        );
        
        return result;
    }
}
