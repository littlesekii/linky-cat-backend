package cat.linky.linky_cat_api.core.domain;

import java.util.UUID;

import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;

public class Link {

    private UUID id;
    private UUID profileId;

    private String title;
    private String url;

    private Integer sortOrder;
    private Long clickCount;

    private Boolean isActive;

    public Link() {}
    public Link(UUID id, UUID profileId, String title, String url, Integer sortOrder, Long clickCount, 
            Boolean isActive) {
        this.id = id;
        this.profileId = profileId;
        this.title = title;
        this.url = url;
        this.sortOrder = sortOrder;
        this.clickCount = clickCount;
        this.isActive = isActive;
        validate();
    }
    public Link(UUID profileId, String title, String url, Integer sortOrder, Boolean isActive) {
        this.id = null;
        this.profileId = profileId;
        this.title = title;
        this.url = url;
        this.sortOrder = sortOrder;
        this.clickCount = 0L;
        this.isActive = isActive;
        validate();
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

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void updateTitle(String title) {
        if (title != null) {
            if (title.isEmpty())
                throw new InvalidArgumentException("domain.link.title.blank");
            
            this.title = title;
        }
    }

    public void updateUrl(String url) {
        if (url != null) {
            if (url.isEmpty())
                throw new InvalidArgumentException("domain.link.url.blank");
            
            this.url = url;
        }
    }

    public void updateSortOrder(Integer sortOrder) {
        if (sortOrder != null) {
            if (sortOrder < 0)
                throw new InvalidArgumentException("domain.link.sort_order.less_than_zero");
            
            this.sortOrder = sortOrder;
        }
    }
    
    public void updateIsActive(Boolean isActive) {
        if (isActive != null) {
            this.isActive = isActive;
        }
    }

    public void validate() {
        if (profileId == null)
            throw new InvalidArgumentException("domain.link.profile_id.null");

        if (title == null || title.isEmpty())
            throw new InvalidArgumentException("domain.link.title.blank");
        
        if (url == null || url.isEmpty())
            throw new InvalidArgumentException("domain.link.url.blank");
        
        if (sortOrder  == null)
            throw new InvalidArgumentException("domain.link.sort_order.null");

        if (sortOrder < 0)
            throw new InvalidArgumentException("domain.link.sort_order.less_than_zero");
        
        if (clickCount < 0L)
            throw new InvalidArgumentException("domain.link.click_count.less_than_zero");
        
        if (isActive == null)
            throw new InvalidArgumentException("domain.link.is_active.null");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Link other = (Link) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
