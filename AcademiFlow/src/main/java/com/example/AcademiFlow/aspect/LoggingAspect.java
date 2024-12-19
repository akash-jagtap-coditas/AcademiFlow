package com.example.AcademiFlow.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private Date startTime;

    @Before("execution(* com.example.AcademiFlow.service.*.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
         startTime = new Date(System.currentTimeMillis());
        logger.info("Entering method: " + joinPoint.getSignature());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("Argument: " + arg);
        }
    }

    @After("execution(* com.example.AcademiFlow.service.*.*.*.(..))")
    public void logAfter(JoinPoint joinPoint) {
        Date endTime = new Date(System.currentTimeMillis());
        long executionTime = endTime.getTime() - startTime.getTime();
        logger.info("Exiting method: " + joinPoint.getSignature().getName()+"  Execution time : "+executionTime);
    }
}

