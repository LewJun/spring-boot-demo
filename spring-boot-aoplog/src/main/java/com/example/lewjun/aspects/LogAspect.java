package com.example.lewjun.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("@annotation(com.example.lewjun.annotation.Log)")
    public void logPointcut() {
        log.info("定义切入点: @annotation(com.example.lewjun.annotation.Log)");
    }

    @Around("logPointcut()")
    public Object around(final ProceedingJoinPoint point) throws Throwable {
        final long startTime = System.currentTimeMillis();

        // 执行具体方法，并得到结果
        final Object result = point.proceed();

        // 执行时长 ms
        final long time = System.currentTimeMillis() - startTime;

        saveLog(result, time);

        return result;
    }

    private void saveLog(final Object result, final long time) {
        log.info("result: {}, time: {} ms", result, time);
    }
}
