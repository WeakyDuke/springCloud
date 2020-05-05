package com.duke.command;

import com.duke.user.model.SysUser;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 自定义请求命令UserCommand
 * @Description:
 * @Date: Created in 10:21 下午 2020/5/5
 * @Modified By:
 */
public class UserCommand extends HystrixCommand<SysUser> {

    private RestTemplate restTemplate;

    private Long id;

    public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected SysUser run() throws Exception {
        return restTemplate.getForObject("http://SPRING-CLOUD-USER/sysUser/{1}", SysUser.class, id);
    }

    @Override
    protected SysUser getFallback() {
        return new SysUser();
    }
}
