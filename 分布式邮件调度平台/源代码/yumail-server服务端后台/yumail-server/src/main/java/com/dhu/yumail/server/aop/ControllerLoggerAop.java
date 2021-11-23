package com.dhu.yumail.server.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * 功能描述：全局Controller日志（控制台）
 * @author Yupi Li
 * @date 19/01/16 10:24
 */

@Component
@Aspect
public class ControllerLoggerAop {

    private static Logger logger = LoggerFactory.getLogger(ControllerLoggerAop.class);


    @Pointcut("execution(* com.dhu.yumail.server.controller.*.*(..))")
    public void controllerLog() {
    }

    @Before("controllerLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        logger.info("URL : " + httpServletRequest.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + httpServletRequest.getMethod());
        logger.info("IP : " + httpServletRequest.getRemoteAddr());
        Enumeration<String> enu = httpServletRequest.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            logger.info("name : " + name + ", value : " + httpServletRequest.getParameter(name));
        }
    }

    @AfterReturning(returning = "ret", pointcut = "controllerLog()")
    public void doAfterReturning(Object ret) {
        logger.info("RESPONSE : " + ret);
    }

}
