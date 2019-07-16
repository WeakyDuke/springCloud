package com.duke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author duke
 * @version 1.0
 * @Description user 服务启动类
 * @date 2019/7/16 12:00
 */
@EnableEurekaClient
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args){
        SpringApplication.run(UserApplication.class, args);
    }
}
