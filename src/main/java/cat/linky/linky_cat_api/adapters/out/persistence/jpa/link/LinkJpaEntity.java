package cat.linky.linky_cat_api.adapters.out.persistence.jpa.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_link")
public class LinkJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "profileId", nullable = false)
    private UUID profileId;

    private String title;
    private String url;

    private Integer sortOrder;
    private Long clickCount;

    private Boolean isActive;

    public LinkJpaEntity() {}    
    public LinkJpaEntity(UUID id, UUID profileId, String title, String url, Integer sortOrder, Long clickCount, 
            Boolean isActive) {
        this.id = id;
        this.profileId = profileId;
        this.title = title;
        this.url = url;
        this.sortOrder = sortOrder;
        this.clickCount = clickCount;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Integer getsortOrder() {
        return sortOrder;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Link toDomain() {
        return new Link(
            id,
            profileId,
            title,
            url,
            sortOrder,
            clickCount,
            isActive
        );
    }

    public static LinkJpaEntity fromDomain(Link link) {
        return new LinkJpaEntity(
            link.getId(),
            link.getProfileId(),
            link.getTitle(),
            link.getUrl(),
            link.getSortOrder(),
            link.getClickCount(),
            link.getIsActive()
        );
    }    
}
