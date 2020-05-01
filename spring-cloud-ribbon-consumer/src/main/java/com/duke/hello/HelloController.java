package com.duke.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Duke
 * @Description:
 * @Date: Created in 11:56 下午 2020/5/1
 * @Modified By:
 */
@RestController
public class HelloController {

    @Value("${spring.application.name}")
    String applicationName;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-hello", method = RequestMethod.GET)
    public String hello(){
        return "server from :" + applicationName + ", To:"
                + restTemplate.getForEntity("http://SPRING-CLOUD-USER/hello", String.class).getBody();
    }
}
