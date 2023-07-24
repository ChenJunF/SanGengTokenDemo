package com.sangeng.aop;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class LogAspect {

    //切点表达式：execution(方法修饰符 返回值类型 方法所属的包.类名.方法名(参数))
    //execution(* com.sangeng.service.*.*(..))
    @Pointcut("execution(* com.sangeng.controller.*.*(..))")
    public void logControllerAspect(){}

    @Pointcut("execution(* com.sangeng.service.*.*(..))")
    public void logServiceAspect(){}

    @Pointcut("logControllerAspect() || logServiceAspect()")
    public void logAspect(){}

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.info("开始执行:{}",joinPoint.getSignature().toString());
        Object[] args = joinPoint.getArgs();
//        String collect = Arrays.stream(args).map(Object::toString).collect(Collectors.joining(","));
//        log.info("参数:{}",collect);
        Object object = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("执行结束:{},执行时间:{} ms",joinPoint.getSignature().toString(),endTime-startTime);
        return object;
    }

}
