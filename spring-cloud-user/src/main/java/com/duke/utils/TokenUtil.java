package com.duke.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.duke.user.sysuser.model.SysUser;

/**
 * @author duke
 * @version 1.0
 * @Description 令牌工具
 * @date 2020/4/22 12:07
 */
public class TokenUtil {

    public static String getToken(SysUser user) {
        String token = "";
        token = JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
