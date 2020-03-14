package com.zzd.api.domain;

import com.zzd.api.domain.BaseEntity;
import java.util.Date;

public class TInformation extends BaseEntity {
    private String id;

    private String proposeUser;

    private String proposeContent;

    private String replyUser;

    private String replyContent;

    private Byte status;

    private String createBy;

    private Date createDatetime;

    private String updateBy;

    private Date updateDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProposeUser() {
        return proposeUser;
    }

    public void setProposeUser(String proposeUser) {
        this.proposeUser = proposeUser == null ? null : proposeUser.trim();
    }

    public String getProposeContent() {
        return proposeContent;
    }

    public void setProposeContent(String proposeContent) {
        this.proposeContent = proposeContent == null ? null : proposeContent.trim();
    }

    public String getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(String replyUser) {
        this.replyUser = replyUser == null ? null : replyUser.trim();
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
