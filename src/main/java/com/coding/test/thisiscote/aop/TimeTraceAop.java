package com.coding.test.thisiscote.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class TimeTraceAop {

    @Around("execution(* com.coding.test.thisiscote..*(..)) && !target(com.coding.test.thisiscote.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        log.debug("start: {}", joinPoint.toShortString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.debug("end: {}ms", timeMs);
        }
    }
}
