package com.zzd.provider;

import com.zzd.api.domain.EmailModel;
import com.zzd.provider.utils.MailSendUtils;
import com.zzd.provider.utils.UniqIdUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ProviderApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(ProviderApplicationTests.class);


    @Test
    public void contextLoads() {
        logger.info("测试测试");
    }




}
