package com.duke;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


@Component
@Aspect
public class DukeTest {

    private static Logger log = LoggerFactory.getLogger(DukeTest.class);

    //@Pointcut("@annotation(com.duke.eureka.DukeAspect)")
    @Pointcut("execution(* com.duke.hello.HelloController.*hello(..))")
    public void test() {

    }



    // 匹配只有一个参数 name 的方法
    /*@Around(value = "test()")
    public Object doSomething(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
        Field proxy = methodPoint.getClass().getDeclaredField("methodInvocation");
        proxy.setAccessible(true);
        MethodInvocation invocation = (ReflectiveMethodInvocation) proxy.get(methodPoint);

        //Object argument = invocation.getArguments()[0];
        log.info("HHHHHH {}", JSON.toJSONString(invocation.getMethod().getName()));
        return joinPoint.proceed();

    }*/

    // 匹配只有一个参数 name 的方法
    @Before(value = "test()")
    public void doSomething(JoinPoint joinPoint) throws Throwable{
        MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
        Field proxy = methodPoint.getClass().getDeclaredField("methodInvocation");
        proxy.setAccessible(true);
        MethodInvocation invocation = (ReflectiveMethodInvocation) proxy.get(methodPoint);
        log.info("HHHHHH {}", JSON.toJSONString(invocation.getMethod().getName()));


    }

}
