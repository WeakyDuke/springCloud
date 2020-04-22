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
    public Object login(@RequestParam(name = "id") Integer userId, @RequestParam(name = "password") String password){
        SysUser userDB = sysUserService.selectByPrimaryKey(userId);
        if (userDB == null){
            throw new RuntimeException("用户不存在，请重新登录");
        }
        if (!userDB.getPassword().equals(password)){
            throw new RuntimeException("密码错误，请重新登录");
        }
        String token = TokenUtil.getToken(userDB);
        Map map = new HashMap();
        map.put("user", userDB);
        map.put("token", token);
        return map;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    @UserLoginToken
    public SysUser serchSysUsers(@PathVariable(value = "id", required = true) Integer id){
        return sysUserService.selectByPrimaryKey(id);
    }

}
