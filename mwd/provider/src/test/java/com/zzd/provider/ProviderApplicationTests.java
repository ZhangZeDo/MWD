package com.zzd.provider;

import com.zzd.api.domain.EmailModel;
import com.zzd.provider.utils.MailSendUtils;
import com.zzd.provider.utils.UniqIdUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ProviderApplicationTests {

    @Resource
    private MailSendUtils mailSendUtils;

    @Test
    public void contextLoads() {
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTheme("测试");
        emailModel.setRecieverName("测试");
        emailModel.setEmailContent("测试");
        emailModel.setRecieverEmailAddress("2925904594@qq.com");

        mailSendUtils.sendEmailAsText(emailModel);
    }




}
