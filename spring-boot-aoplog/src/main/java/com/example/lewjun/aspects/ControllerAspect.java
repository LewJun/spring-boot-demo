package com.example.lewjun.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

    // 拦截web目录下以Controller文件名结尾的任意个参数的任意方法
    @Pointcut("execution( * com.example.lewjun.web.*Controller.*(..))")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void before(final JoinPoint point) throws Throwable {
        final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = requestAttributes.getRequest();

        log.info("=========================================");
        log.info("请求地址: {}", request.getRequestURL().toString());
        log.info("请求方法: {}", request.getMethod());
        final MethodSignature signature = (MethodSignature) point.getSignature();

        log.info("执行方法: {}.{}", signature.getDeclaringTypeName(), signature.getName());
        log.info("执行方法参数: {}", Arrays.toString(point.getArgs()));
    }

    @AfterReturning(returning = "result", pointcut = "logPointcut()")
    public void afterReturn(final Object result) {
        log.info("返回值: {}", result);
        log.info("=========================================");
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
