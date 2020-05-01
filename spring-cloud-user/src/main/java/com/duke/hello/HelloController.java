package com.duke.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duke
 * @version 1.0
 * @Description TODO
 * @date 2019/7/16 11:59
 */
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @Value("${spring.application.name}")
    String applicationName;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        ServiceInstance instance = client.getInstances(applicationName).get(0);
        logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return applicationName + ": Hello World";
    }

    //@DukeAspect
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1() {
        aspectTest("duke");
        logger.info("hello world");
        logger.info("hello world");
        logger.info("hello world");
        logger.info("hello the world");
        return "Hello World";
    }

    //@DukeAspect
    public String aspectTest(String name){
        logger.info("aspectTest: {}", name);
        return name;
    }
}
