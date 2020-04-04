package com.zzd.api.dto;

import com.zzd.api.domain.Pager;

/**
 * @author
 * @date
 * @describe
 */
public class BookmarkDTO extends Pager {
    private String userAccount;

    private String mediaId;

    private String mediaType;

    private String searchInput;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }
}
