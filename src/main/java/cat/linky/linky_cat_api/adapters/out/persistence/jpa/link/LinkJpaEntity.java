package cat.linky.linky_cat_api.adapters.out.persistence.jpa.link;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cat.linky.linky_cat_api.adapters.out.persistence.jpa.user.UserJpaEntity;
import cat.linky.linky_cat_api.core.domain.Link;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_link")
public class LinkJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String url;

    private Integer sortOrder;
    private Long clickCount;

    private Boolean isActive;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    public LinkJpaEntity() {}    
    public LinkJpaEntity(UUID id, String title, String url, Integer sortOrder, Long clickCount, Boolean isActive,
            UserJpaEntity user) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.sortOrder = sortOrder;
        this.clickCount = clickCount;
        this.isActive = isActive;
        this.user = user;
    }
    public LinkJpaEntity(String title, String url, Integer sortOrder, Long clickCount, Boolean isActive) {
        this.id = null;
        this.title = title;
        this.url = url;
        this.sortOrder = sortOrder;
        this.clickCount = 0L;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
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

    public UserJpaEntity getUser() {
        return user;
    }

    public Link toDomain() {
        return new Link(id,
            title,
            url,
            sortOrder,
            clickCount,
            isActive
        );
    }

    public static LinkJpaEntity fromDomain(Link link, UserJpaEntity userEntity) {
        return new LinkJpaEntity(
            link.getId(),
            link.getTitle(),
            link.getUrl(),
            link.getsortOrder(),
            link.getClickCount(),
            link.getIsActive(),
            userEntity
        );
    }    
}
