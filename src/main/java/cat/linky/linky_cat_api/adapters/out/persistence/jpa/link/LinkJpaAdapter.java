package cat.linky.linky_cat_api.adapters.out.persistence.jpa.link;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;

@Component
public class LinkJpaAdapter implements LinkRepositoryPort {

    private final LinkJpaRepository repository;
    
    public LinkJpaAdapter(LinkJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Link> findById(UUID id) {
        Optional<LinkJpaEntity> entity = repository.findById(id);
        return entity.map(LinkJpaEntity::toDomain);
    }

    @Override
    public List<Link> findAllByProfileId(UUID profileId) {
        return repository.findAllByProfileId(profileId).stream()
            .map(LinkJpaEntity::toDomain)
            .toList();
    }

    @Override
    public Link save(Link link) {
        LinkJpaEntity entity = LinkJpaEntity.fromDomain(link);
        LinkJpaEntity saved = repository.save(entity);
        return saved.toDomain();
    }

    @Override
    public void deleteById(UUID id) {
       repository.deleteById(id);
    }
}
