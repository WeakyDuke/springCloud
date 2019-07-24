package com.duke;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author duke
 * @version 1.0
 * @Description 用户系统基础测试类
 * @date 2019/7/18 11:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
@WebAppConfiguration
public class UserApplicationTest {
    @Test
    public void test(){
        System.out.println("--> UserApplicationTest start");
    }
}
