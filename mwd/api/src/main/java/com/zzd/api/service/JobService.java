package com.zzd.api.service;

import com.zzd.api.domain.TJob;

/**
 * @author
 * @date
 * @describe
 */
public interface JobService {
    void insert(TJob tJob);

    int update(TJob tJob);

    TJob findByJobName(String jobName);

    void updateJob(TJob tJob);
}
