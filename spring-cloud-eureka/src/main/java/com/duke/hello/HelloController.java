package com.duke.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Duke
 * @Description:
 * @Date: Created in 10:52 PM 2019/7/14
 * @Modified By:
 */
@RestController
public class HelloController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        aspectTest("duke");
        logger.info("hello world");
        logger.info("hello world");
        logger.info("hello world");
        logger.info("hello the world");
        return "Hello World";
    }
    
    public String aspectTest(String name){
        logger.info("aspectTest: {}", name);
        return name;
    }
}
