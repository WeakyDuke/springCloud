package com.duke.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.duke.user.sysuser.model.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    // Token 过期时间
    public static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 校验token是否正确
     * @param token
     * @param username
     * @param secret
     * @return
     */
    public static boolean verify(String token, String username, String secret){
        try{
            // 设置加密算法
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("uname", username)
                    .build();

            // 校验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
             return false;
        }
    }

    /**
     * 、生成签名，30分钟后过期
     * @param username
     * @param secret
     * @return
     */
    public static String sign(String username, String secret){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("uname", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 获得用户名
     * @param request
     * @return
     */
    public static String getUserNameByToken(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("uname")
                .asString();
    }
}
