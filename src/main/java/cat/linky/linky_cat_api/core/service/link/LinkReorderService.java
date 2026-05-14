package cat.linky.linky_cat_api.core.service.link;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.exception.ResourceNotFoundException;
import cat.linky.linky_cat_api.core.exception.UnauthorizedOperationException;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkReorderCommand;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkReorderUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;

public class LinkReorderService implements LinkReorderUseCase {

    private final ProfileRepositoryPort profileRepositoryPort;
    private final LinkRepositoryPort linkRepositoryPort;

    public LinkReorderService(
        ProfileRepositoryPort profileRepositoryPort,
        LinkRepositoryPort linkRepositoryPort
    ) {
        this.profileRepositoryPort = profileRepositoryPort;
        this.linkRepositoryPort = linkRepositoryPort;
    }

    @Override
    public void execute(List<LinkReorderCommand> commandList, UUID userId) {

        Profile profile = profileRepositoryPort.findByUserId(userId)
            .orElseThrow(() -> new ResourceNotFoundException("service.profile.not_found"));

        for(LinkReorderCommand command : commandList) {
            Link existingLink = linkRepositoryPort.findById(command.id())
                .orElseThrow(() -> new ResourceNotFoundException("service.link.not_found"));

            if (!existingLink.getProfileId().equals(profile.getId()))
                throw new UnauthorizedOperationException("authorization.unauthorized_operation");

            existingLink.updateSortOrder(command.sortOrder());
            linkRepositoryPort.save(existingLink);
        }
    }
}
