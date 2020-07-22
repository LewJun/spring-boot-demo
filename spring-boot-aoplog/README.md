# spring-boot-aoplog

> 演示spring boot 实现日志全局拦截

[TOC]

## 添加aop依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
```

## 自定义注解@Log，注释到方法上，然后使用LogAspect解析被@Log注解的方法

### 定义Log注解

[Log.java](src/main/java/com/example/lewjun/annotation/Log.java)

```java
package com.example.lewjun.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
```

### 解析被@Log注解的方法

[LogAspect.java](src/main/java/com/example/lewjun/aspects/LogAspect.java)

```java
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
```

### [App.java](src/main/java/com/example/lewjun/App.java)

```java
    @Log("/log is logged")
    @GetMapping("/log")
    public String log(@RequestParam(name = "name", defaultValue = "World") final String name) {
        return String.format("Hello %s!", name);
    }
```

### 输出
```log
2020-07-22 15:08:23.279  INFO 13288 --- [nio-1234-exec-1] com.example.lewjun.aspects.LogAspect     : result: Hello World!, time: 20 ms
```

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

