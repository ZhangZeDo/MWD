package com.zzd.provider.config;

import com.zzd.api.domain.TJob;
import com.zzd.api.utils.ThreadUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * @author
 * @date
 * @describe
 */
public class QuartzJobFactory implements Job {
    private ApplicationContext applicationContext = null;

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ThreadUtil.addTask(new Runnable() {
            @Override
            public void run() {
                TJob tJob = (TJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
                invokeMethod(tJob);
            }
        });
    }

    public void invokeMethod(TJob job) {
        Object object = null;
        Method method = null;
        try {
            if (StringUtils.isNotBlank(job.getBeanClazz())) {
                object = applicationContext.getBean(job.getBeanClazz());
            }
            if (object != null) {
                method = object.getClass().getDeclaredMethod(job.getMethodName(),String.class);
                if (method != null) {
                    method.invoke(object,job.getParams());
                }
            }
        } catch (Exception e) {

        }
    }
}
