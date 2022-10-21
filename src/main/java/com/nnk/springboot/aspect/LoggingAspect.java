package com.nnk.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect
{
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Before("execution(* com.nnk.springboot.*.*.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        logger.info("Method " + methodName + " with parameters " + Arrays.asList(arguments) + " called");
    }

    @After("execution(* com.nnk.springboot.service.*.*(..))")
    public void logAfterAllMethods(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        logger.info("Method " + methodName + " with parameters " + Arrays.asList(arguments) + " executed");
    }


    public void setLogger(Logger logger)
    {
        this.logger = logger;
    }
}