package com.zzd.provider;

import com.zzd.provider.utils.UniqIdUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProviderApplicationTests {

    @Test
    public void contextLoads() {
        String X = UniqIdUtil.getUniqId();
        System.out.println(X+"===============");
    }


}
