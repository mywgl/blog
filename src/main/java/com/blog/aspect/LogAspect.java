package com.blog.aspect;

import com.blog.pojo.log.RequestLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Date 2021/3/24 17:00
 * @Version 1.0
 */
@Aspect
@Component
public class LogAspect {
    
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.blog.controller.*.*(..))")
    public void log(){
    
    }
    
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String method = joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, method, args);
        logger.info("Request {}",requestLog);
    }

    
    @AfterReturning(returning = "result", pointcut = "log()")
    public void afterReturning(Object result){
      logger.info("Result {}",result);
    }
    
    //after 最后才执行
    @After("log()")
    public void after(){
        // logger.info("--------after最后执行-------");
    }
}
