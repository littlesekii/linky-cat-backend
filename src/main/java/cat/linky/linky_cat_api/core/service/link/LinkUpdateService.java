package cat.linky.linky_cat_api.core.service.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.exception.notFound.LinkNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkUpdateCommand;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkUpdateUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;

public class LinkUpdateService implements LinkUpdateUseCase {

    private final LinkRepositoryPort repositoryPort;

    public LinkUpdateService(LinkRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public LinkResult execute(UUID id, LinkUpdateCommand command) {

        Link existingLink = repositoryPort.findById(id)
            .orElseThrow(() -> new LinkNotFoundException());

        existingLink.updateTitle(command.title());
        existingLink.updateUrl(command.url());
        existingLink.updateSortOrder(command.sortOrder());
        existingLink.updateIsActive(command.isActive());

        existingLink = repositoryPort.save(existingLink);

        LinkResult result = new LinkResult(
            existingLink.getId(), 
            existingLink.getTitle(), 
            existingLink.getUrl(), 
            existingLink.getSortOrder(), 
            existingLink.getClickCount(), 
            existingLink.getIsActive()
        );

        return result;
    }
}
