package com.zzd.api.dto;

import com.zzd.api.domain.Pager;

/**
 * @author
 * @date
 * @describe
 */
public class MediaWorkDTO extends Pager {
    private String id;

    private String mediaType;

    private String searchInput;

    private boolean belongTo;

    private String uploadUser;

    private byte status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isBelongTo() {
        return belongTo;
    }

    public void setBelongTo(boolean belongTo) {
        this.belongTo = belongTo;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
