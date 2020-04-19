package com.zzd.provider.config;

import com.zzd.provider.utils.MailSendUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanRegistryCenterConfig {
    @Bean
    public MailSendUtils mailSendUtils() {
        return new MailSendUtils();
    }
}
