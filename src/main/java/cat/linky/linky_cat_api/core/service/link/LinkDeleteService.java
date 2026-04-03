package cat.linky.linky_cat_api.core.service.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.exception.UnauthorizedOperationException;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkDeleteUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;

public class LinkDeleteService implements LinkDeleteUseCase {

    private final LinkRepositoryPort repositoryPort;

    public LinkDeleteService(LinkRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public void execute(UUID id, UUID userId) {
        if (!repositoryPort.checkOwnership(id, userId))
            throw new UnauthorizedOperationException();

        repositoryPort.deleteById(id);
    }
}
