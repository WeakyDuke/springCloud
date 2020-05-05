package com.duke.hello.service;

import com.duke.command.UserCommand;
import com.duke.user.model.SysUser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @Author: Duke
 * @Description:
 * @Date: Created in 10:59 下午 2020/5/4
 * @Modified By:
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        return restTemplate.getForEntity("http://SPRING-CLOUD-USER/hello", String.class).getBody();
    }

    public Object helloFallback(){
        return "error";
    }

    public SysUser sysUser(){
        //SysUser sysUser =  new UserCommand(restTemplate, 1L).execute();
        //return sysUser;

        //Future<SysUser> future = new UserCommand(restTemplate, 1L).queue();

        return null;
    }

    @HystrixCommand
    public SysUser getSysUserById(Long id){
        return restTemplate.getForObject("http://SPRING-CLOUD-USER/sysUser/{1}", SysUser.class, id);
    }

    @HystrixCommand
    public Future<SysUser> getSysUserByIdAsync(final Long id){
        return new AsyncResult<SysUser>(){
          @Override
          public SysUser invoke(){
              return restTemplate.getForObject("http://SPRING-CLOUD-USER/sysUser/{1}", SysUser.class, id);
          }
        };
    }

}
