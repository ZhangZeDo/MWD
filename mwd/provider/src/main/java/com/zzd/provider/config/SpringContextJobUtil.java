package com.zzd.provider.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class SpringContextJobUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    @SuppressWarnings("static-access")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static String getMessage(String key){
        return context.getMessage(key,null, Locale.getDefault());
    }
}
