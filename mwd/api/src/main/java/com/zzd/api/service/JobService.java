package com.zzd.api.service;

import com.zzd.api.domain.TJob;
import com.zzd.api.dto.JobDTO;
import com.zzd.api.dto.PageResponseResult;

/**
 * @author
 * @date
 * @describe
 */
public interface JobService {
    void insert(TJob tJob,String operator);

    void update(TJob tJob,String operator);

    TJob findByJobName(String jobName);

    void updateJob(TJob tJob);

    /* 查询所有的定时任务 */
    PageResponseResult queryJobList(JobDTO jobDTO);

    void runOnce(TJob tJob);
}
