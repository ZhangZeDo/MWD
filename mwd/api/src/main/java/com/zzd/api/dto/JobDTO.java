package com.zzd.api.dto;

import com.zzd.api.domain.Pager;

/**
 * @author
 * @date
 * @describe
 */
public class JobDTO extends Pager {
    private String jobName;

    private String searchInput;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }
}
