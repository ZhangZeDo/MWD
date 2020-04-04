package com.zzd.api.dto;

import com.zzd.api.domain.Pager;

/**
 * @author
 * @date
 * @describe
 */
public class DiscussDTO extends Pager {
    String mediaId;

    byte status;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
