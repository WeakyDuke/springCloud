package com.duke.hello;

import com.duke.hello.service.HelloService;
import com.duke.user.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/ribbon-hello", method = RequestMethod.GET)
    public String hello(){
        return "server from :" + applicationName + ", To:"
                + restTemplate.getForEntity("http://SPRING-CLOUD-USER/hello", String.class).getBody();
    }

    @RequestMapping(value = "/ribbon-sysUser", method = RequestMethod.GET)
    public SysUser sysUser(){
        return helloService.getSysUserById(1L);
        /*Future<SysUser> future = helloService.getSysUserByIdAsync(1L);
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new SysUser();*/
    }
}
