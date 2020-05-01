package com.duke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 注册到Eureka注册中心
 * 扫描mapper文件路径
 * 启用Spring应用程序上下文的自动配置，尝试猜测和配置您可能需要的bean
 * @SpringBootApplication(scanBasePackages = "com.duke", exclude = DataSourceAutoConfiguration.class)
 * 默认会扫描启动类所在包及子包文件，如果主启动类不在根目录下，则需要加扫描路径参数
 */
@EnableEurekaClient
@MapperScan("com.duke.user.*.mapper")
@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args){
        SpringApplication.run(UserApplication.class, args);
    }
}
