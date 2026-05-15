package cat.linky.linky_cat_api.core.ports.out.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;

public interface LinkRepositoryPort {
    public Optional<Link> findById(UUID id);

    public List<Link> findAllByProfileId(UUID profileId);
    public List<Link> findAllByProfileIdOrderBySortOrderDesc(UUID profileId);

    public List<Link> findAllByProfileIdAndIsActiveTrue(UUID profileId);
    public List<Link> findAllByProfileIdAndIsActiveTrueOrderBySortOrderDesc(UUID profileId);

    public Link save(Link link);
    public void deleteById(UUID id);

    public boolean checkOwnership(UUID id, UUID userId);
}
