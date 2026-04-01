package cat.linky.linky_cat_api.core.domain;

import java.util.UUID;

import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;

public class Link {

    private UUID id;

    private String title;
    private String url;

    private Integer sortOrder;
    private Long clickCount;

    private Boolean isActive;

    public Link() {}
    public Link(UUID id, String title, String url, Integer sortOrder, Long clickCount, 
            Boolean isActive) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.sortOrder = sortOrder;
        this.clickCount = clickCount;
        this.isActive = isActive;
        validate();
    }
    public Link(String title, String url, Integer sortOrder, Boolean isActive) {
        this.id = null;
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

    public void update(Link link) {
        title = link.getTitle();
        url = link.getUrl();
        sortOrder = link.getsortOrder();
        isActive = link.getIsActive();
    }

    public void validate() {
        if (title == null || title.isEmpty())
            throw new InvalidArgumentException("title cannot be blank");
        
        if (url == null || url.isEmpty())
            throw new InvalidArgumentException("url cannot be blank");
        
        if (sortOrder  == null)
            throw new InvalidArgumentException("sortOrder cannot be null");

        if (sortOrder < 0)
            throw new InvalidArgumentException("sortOrder cannot be less than 0");
        
        if (clickCount < 0L)
            throw new InvalidArgumentException("clickCount cannot be less than 0");
        
        if (isActive == null)
            throw new InvalidArgumentException("isActive cannot be null");
    }
}
