package com.duke.user.sysuser.controller;

import com.duke.annotation.UserLoginToken;
import com.duke.user.sysuser.model.SysUser;
import com.duke.user.sysuser.service.SysUserService;
import com.duke.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author duke
 * @version 1.0
 * @Description 用户模块
 * @date 2019/7/24 14:43
 */
@RestController
@RequestMapping(value="sysUser")
public class SysUserController {

    private Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestBody SysUser user){
        SysUser userDB = sysUserService.selectByPrimaryKey(user.getId());
        if (userDB == null){
            throw new RuntimeException("用户不存在，请重新登录");
        }
        if (!userDB.getPassword().equals(user.getPassword())){
            throw new RuntimeException("密码错误，请重新登录");
        }
        String token = TokenUtil.getToken(userDB);
        Map map = new HashMap();
        map.put("user", userDB);
        map.put("token", token);
        return map;
    }

    @PostMapping("/login2")
    @ResponseBody
    public Object login2(@RequestBody SysUser user){
        SysUser userDB = sysUserService.selectByUname(user.getUname());
        if (userDB == null){
            throw new RuntimeException("用户不存在，请重新登录");
        }
        if (!userDB.getPassword().equals(user.getPassword())){
            throw new RuntimeException("密码错误，请重新登录");
        }
        // 实际应该传密钥而不是密码
        String token = TokenUtil.sign(user.getUname(), user.getPassword());
        Map map = new HashMap();
        if (token != null){
            map.put("code", "200");
            map.put("message","认证成功");
            map.put("token", token);
            return map;
        }
        map.put("code", "403");
        map.put("message","认证失败");
        return map;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    //@UserLoginToken
    public SysUser serchSysUsers(@PathVariable(value = "id", required = true) Integer id){
        return sysUserService.selectByPrimaryKey(id);
    }

}
