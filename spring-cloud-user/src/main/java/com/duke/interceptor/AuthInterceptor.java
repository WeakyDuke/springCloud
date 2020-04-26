package com.duke.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.duke.annotation.PassToken;
import com.duke.annotation.UserLoginToken;
import com.duke.user.sysuser.model.SysUser;
import com.duke.user.sysuser.service.SysUserService;
import com.duke.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author duke
 * @version 1.0
 * @Description 拦截器
 * @date 2020/4/22 11:06
 */
public class AuthInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        String url = request.getRequestURI();
        logger.info(String.format("toke=%s, url=%s", token, url));
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                /*String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                SysUser user = sysUserService.selectByPrimaryKey(Integer.valueOf(userId));
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;*/

                //
                String uname = TokenUtil.getUserNameByToken(request);
                // 这边拿到的 用户名 应该去数据库查询获得密码，简略，步骤在service直接获取密码
                SysUser userDB = sysUserService.selectByUname(uname);
                if (userDB == null){
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                boolean result = TokenUtil.verify(token,uname,userDB.getPassword());
                if(result){
                    System.out.println("通过拦截器");
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
