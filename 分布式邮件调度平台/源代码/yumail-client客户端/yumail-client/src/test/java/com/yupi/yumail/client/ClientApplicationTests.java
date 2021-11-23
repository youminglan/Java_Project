package com.yupi.yumail.client;

import com.yupi.yumail.client.facade.YumailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTests {

    @Resource
    private YumailClient yumailClient;

    @Test
    public void contextLoads() {
        yumailClient.sendMail("592789970@qq.com", "测试一下","不错的");
    }

}
