package com.nnk.springboot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
//import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class LoggingAspect
{
    @Before("execution(* com.nnk.springboot.controllers.*.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        log.info("Method " + methodName + " with parameters " + Arrays.asList(arguments) + " called");
    }

    @After("execution(* com.nnk.springboot.controllers.*.*(..))")
    public void logAfterAllMethods(JoinPoint joinPoint)
    {
        String methodName = joinPoint.getSignature().getName();
        Object [] arguments = joinPoint.getArgs();
        log.info("Method " + methodName + " with parameters " + Arrays.asList(arguments) + " executed");
    }
}