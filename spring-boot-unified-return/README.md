# spring-boot-unified-return

> 演示spring boot 实现restfull api返回统一格式的数据

示例是在[spring-boot-restfull](../spring-boot-restfull)的基础上添加

## 定义统一返回格式的类
[ApiResult.java](src/main/java/com/example/lewjun/common/ApiResult.java)

```java
package com.example.lewjun.common;

import lombok.Data;

@Data
public class ApiResult<T> {
    private final T data;
    private int code;
    private String msg = "接口响应正常";

    public ApiResult(final T data) {
        this.data = data;
    }
}
```

## UnifiedReturnConfig

借助几个关键注解来完成一下相关配置

* @EnableWebMvc
* @Configuration
* @RestControllerAdvice

[UnifiedReturnConfig.java](src/main/java/com/example/lewjun/config/UnifiedReturnConfig.java)

```java
package com.example.lewjun.config;

import com.example.lewjun.common.ApiResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@EnableWebMvc
@Configuration
public class UnifiedReturnConfig {
    @RestControllerAdvice("com.example.lewjun.web")
    static class UnifiedReturnAdvice implements ResponseBodyAdvice<Object> {

        @Override
        public boolean supports(final MethodParameter methodParameter, final Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(final Object obj, final MethodParameter methodParameter, final MediaType mediaType, final Class<? extends HttpMessageConverter<?>> aClass, final ServerHttpRequest serverHttpRequest, final ServerHttpResponse serverHttpResponse) {
            if (obj instanceof ApiResult) {
                return obj;
            }
            return new ApiResult<>(o);
        }
    }
}
```

## 完成
如上两步，就这样就可以了。

不需要改任何其它的内容

数据返回格式为：

```json
{
    "data": {
        "aac001": 1,
        "aac002": "scott",
        "aac003": "xyz",
        "aac006": -1,
        "aac007": 1.74,
        "aac008": 40.0,
        "aac009": [
            "0",
            "1",
            "2"
        ]
    },
    "code": 0,
    "msg": ""
}
```

## 异常统一处理返回

### 处理手动抛出异常 throw

```java
    /**
     * 获取列表
     *
     * @return List<Ac01>
     */
    @GetMapping
    public List<Ac01> getList() {
        try {
            final int i = 1 / 0;
        } catch (final Exception ex) {
            throw BussException.of(EnumApiResultStatus.FAIL, ex);
        }
        return ac01Service.getList();
    }
```

如上，模拟了一个异常，并通过throw抛出了一个自定义的BussException

* [BussException.java](src/main/java/com/example/lewjun/common/BussException.java)

```java
package com.example.lewjun.common;

public class BussException extends RuntimeException {
    private EnumApiResultStatus status;

    public EnumApiResultStatus getStatus() {
        return status;
    }

    private BussException() {
    }

    private BussException(final EnumApiResultStatus status, final Throwable throwable) {
        super(throwable);
        this.status = status;
    }

    public static BussException of() {
        return of(EnumApiResultStatus.FAIL);
    }

    public static BussException of(EnumApiResultStatus status) {
        return of(status, null);
    }

    public static BussException of(final Throwable throwable) {
        return of(EnumApiResultStatus.FAIL, throwable);
    }

    public static BussException of(final EnumApiResultStatus status, final Throwable throwable) {
        return new BussException(status, throwable);
    }
}
```

* [EnumApiResultStatus.java](src/main/java/com/example/lewjun/common/EnumApiResultStatus.java)
```java
package com.example.lewjun.common;

public enum EnumApiResultStatus {
    OK(0, "请求成功"),
    FAIL(-1, "系统异常");

    public final int code;

    public final String msg;

    EnumApiResultStatus(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
```

* 统一异常处理方式
[UnifiedReturnConfig.java](src/main/java/com/example/lewjun/config/UnifiedReturnConfig.java)

```java
    @ControllerAdvice
    static class ExceptionHandlerAdvice {
        @ExceptionHandler
        @ResponseBody
        public ApiResult exceptionHandle(final Throwable throwable) {
            log.error("【出现异常】", throwable);

            if (throwable instanceof BussException) {
                final BussException ex = (BussException) throwable;
                return ApiResult.of(ex.getStatus());
            }

            return ApiResult.of(EnumApiResultStatus.FAIL);
        }
    }
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

## postman文档

[spring-boot-restfull.postman_collection.json](doc/spring-boot-restfull.postman_collection.json)

