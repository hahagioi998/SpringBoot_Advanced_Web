package com.wsh.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wsh on 2017/11/30.
 * Aspect 切面
 */
@Aspect
@Component
public class HttpAspect {

    //slf4j日志框架
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Before("execution(public * com.wsh.controller.HelloController.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("被AOP拦截Before");

        //拦截获取请求的一些数据
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //url
        logger.info("url={}",request.getRequestURL());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName() +"." + joinPoint.getSignature().getName());
        //参数
        logger.info("args={}",joinPoint.getArgs());
    }

    @After("execution(public * com.wsh.controller.HelloController.*(..))")
    public void doAfter() {
        logger.info("被AOP拦截After");
    }

    //可以获取响应的数据
    @AfterReturning(returning = "obj",pointcut = "execution(public * com.wsh.controller.HelloController.*(..))")
    public void doAfterReturning(Object obj) {
        logger.info("response={}",obj);
    }

//    @Pointcut("execution(public * com.wsh.controller.HelloController.*(..))")
//    public void log() {
//
//    }
//
//    @Before("log()")
//    public void doBefore() {
//        System.out.println("被AOP拦截Before");
//    }
//
//    @After("log()")
//    public void doAfter() {
//        System.out.println("被AOP拦截After");
//    }

}