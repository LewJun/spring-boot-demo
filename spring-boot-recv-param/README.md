# spring-boot-recv-param

> spring boot 接收参数的几种方式

[TOC]

## [App.java](src/main/java/com/example/lewjun/App.java)

```java
/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/")
    public String index() {
        return "/";
    }
}
```

## @RequestParam
```java
    @GetMapping("/request_param")
    public String request_param(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
```

* GET 请求方式：
    1. http://localhost:1234/demo/request_param
    2. http://localhost:1234/demo/request_param?name=Amy

`?name=Amy 被称为queryParam`

@RequestParam默认`required = true`，要求`name`参数必传，但是如果设置了defaultValue，那么上面请求方式1没有传递name参数也是可以的。

```java
    @PostMapping("/request_param/post")
    public String request_param_post(@RequestParam(name = "name", defaultValue = "World") final String name) {
        return String.format("Hello %s!", name);
    }
```
* POST 请求方式：
    1. http://localhost:1234/demo/request_param/post
    2. http://localhost:1234/demo/request_param/post?name=Amy
    3. http://localhost:1234/demo/request_param/post
        * 把参数放到form-data中
        * 把参数放到x-www-form-urlencoded中
如上4中方式都是可以的。
 
## @PathVariable

从URL里面读取参数值，可以在@RequestMapping里面添加占位符{paramXXX}，还可以添加正则表达式

```java
    @GetMapping("/path_variable/{hello}/{world:[0-9]{5}}")
    public String path_variable(@PathVariable("hello") final String hello, @PathVariable("world") final String world) {
        return String.format("%s %s!", hello, world);
    }
```

如果参数名和占位符一致，可以@PathVariable("hello") 改为 @PathVariable

```java
    @GetMapping("/path_variable/{hello}/{world:[0-9]{5}}")
    public String path_variable(@PathVariable final String hello, @PathVariable final String world) {
        return String.format("%s %s!", hello, world);
    }
```

如果请求中，没有对应的正则表达式可满足，会出现404的错误。

## 自动匹配

```java
    @RequestMapping("/auto_match")
    public String auto_match(final String hello, final String world) {
        return String.format("%s %s!", hello, world);
    }
```

请求方式：
* http://localhost:1234/demo/auto_match?hello=a&world=b
* http://localhost:1234/demo/request_param/post
    * 把参数放到form-data中
    * 把参数放到x-www-form-urlencoded中

## 实体类接收参数

* [Ab01.java](src/main/java/com/example/lewjun/model/Ac01.java)
```java
@Data
@Accessors(chain = true)
public class Ab01 extends BaseObj {
    private int aab001;
    private String aab002;
    private String aab003;
}
```

```java
    @RequestMapping("/pass_ab01")
    public String pass_ab01(final Ab01 ab01) {
        log.info("ab01: {}", ab01);
        return ab01.toString();
    }
```

请求：
* http://localhost:1234/demo/pass_ab01?aab001=123&aab002=ACCOUNTING&aab003=NEW YORK
* http://localhost:1234/demo/pass_ab01
    * x-www-form-urlencoded
    * form-data
        * aab001:123
        * aab002:ACCOUNTING
        * aab003:NEW YORK

如上3中方式都可以。

## @RequestBody

```java
    @RequestMapping("/request_body")
    public String request_body(@RequestBody final Ab01 ab01) {
        log.info("ab01: {}", ab01);
        return GsonUtil.objToJsonString(ab01);
    }
```

```json
{"aab001": 10, "aab002": "ACCOUNTING", "aab003": "NEW YORK"}
```

postman发的raw信息请求和响应日志：
```log
POST http://localhost:1234/demo/request_body
200
16 ms
POST /demo/request_body HTTP/1.1
Content-Type: application/json                               // <--------content-type
User-Agent: PostmanRuntime/7.26.1
Accept: */*
Cache-Control: no-cache
Postman-Token: d3bb2e79-3949-4537-bd01-2c0241b4cb0f
Host: localhost:1234
Accept-Encoding: gzip, deflate, br
Connection: keep-alive
Content-Length: 60
{"aab001": 10, "aab002": "ACCOUNTING", "aab003": "NEW YORK"}  // <--------请求体
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 55
Date: Wed, 15 Jul 2020 08:21:12 GMT
Keep-Alive: timeout=60
Connection: keep-alive
{"aab001":10,"aab002":"ACCOUNTING","aab003":"NEW YORK"}        // <--------请求体
```

注意：请求类型必须是application/json，get或者post方式都是可以的。

以form-data或x-www-form-urlencoded请求数据，会得到415错误，不支持的媒体类型
```log
{
    "status": 415,
    "error": "Unsupported Media Type",
    "message": "Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported",
    "path": "/demo/request_body"
}
```

## json date和long之间的相互转换
要求：
1. 前端上传的日期，转换为long类型后，再传递给后端
2. 后端返回的日期，转换为long类型后，再返回给前端

例如前端上传的json
```json
{
  "aac001": 7839,
  "aac002": "KING",
  "aac003": "PRESIDENT",
  "aac005": 1590681600000,
  "aac006": 1,
  "aac007": 66.6,
  "aac008": 1.72,
  "aac009": [
    "Reading",
    "Coding",
    "Play"
  ],
  "ab01": {
    "aab001": 10,
    "aab002": "ACCOUNTING",
    "aab003": "NEW YORK"
  },
  "aac100": 1590738530054,
  "aac101": 1590738530060
}
```

后端对应的实体 [Ac01.java](src/main/java/com/example/lewjun/model/Ac01.java)
```java
@Data
@Accessors(chain = true)
public class Ac01 extends BaseObj {
    private int aac001;
    private String aac002;
    private String aac003;
    private Integer aac004;
    private LocalDate aac005;
    private int aac006;
    private float aac007;
    private float aac008;
    private List<String> aac009;
    private Ab01 ab01;
    private LocalDateTime aac100;
    private Date aac101;
}
```

接收
```java
    @RequestMapping("/request_body_ac01")
    public String request_body_ac01(@RequestBody final Ac01 ac01) {
        log.info("ac01: {}", ac01);
        return GsonUtil.objToJsonString(ac01);
    }
```

* 如果Ac01类不做任何处理，直接上传，会出现类型不匹配问题，需要将long转换为相应的日期类型

### Date转long
[DateToLongJsonSerializer.java](src/main/java/com/example/lewjun/util/DateToLongJsonSerializer.java)
```java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Date转long
 */
public class DateToLongJsonSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(final Date date, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime());
    }
}
```

### long转Date
[LongToDateJsonDeserializer.java](src/main/java/com/example/lewjun/util/LongToDateJsonDeserializer.java)
```java
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * long转Date
 */
public class LongToDateJsonDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return new Date(jsonParser.getLongValue());
    }
}
```

### LocalDate转long
[LocalDateToLongJsonSerializer.java](src/main/java/com/example/lewjun/util/LocalDateToLongJsonSerializer.java)

```java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

/**
 * LocalDate转long
 */
public class LocalDateToLongJsonSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(final LocalDate localDate, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(DateUtils.asLong(localDate));
    }
}
```

### long转LocalDate
[LongToLocalDateJsonDeserializer.java](src/main/java/com/example/lewjun/util/LongToLocalDateJsonDeserializer.java)
```java
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * long转LocalDate
 */
public class LongToLocalDateJsonDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return DateUtils.asLocalDate(jsonParser.getLongValue());
    }
}
```

### LocalDateTime转long
[LocalDateTimeToLongJsonSerializer.java](src/main/java/com/example/lewjun/util/LocalDateTimeToLongJsonSerializer.java)
```java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * LocalDateTime转long
 */
public class LocalDateTimeToLongJsonSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(final LocalDateTime localDateTime, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(DateUtils.asLong(localDateTime));
    }
}
```

### long转LocalDateTime
[LongToLocalDateTimeJsonDeserializer.java](src/main/java/com/example/lewjun/util/LongToLocalDateTimeJsonDeserializer.java)
```java
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class LongToLocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return DateUtils.asLocalDateTime(jsonParser.getLongValue());
    }
}
```

### @JsonSerialize & @JsonDeserialize

经过上面long和date之间的相互转换后，就可以应用在字段上了。
```java
    @JsonSerialize(using = LocalDateToLongJsonSerializer.class)
    @JsonDeserialize(using = LongToLocalDateJsonDeserializer.class)
    private LocalDate aac005;

    @JsonSerialize(using = LocalDateTimeToLongJsonSerializer.class)
    @JsonDeserialize(using = LongToLocalDateTimeJsonDeserializer.class)
    private LocalDateTime aac100;

    @JsonSerialize(using = DateToLongJsonSerializer.class)
    @JsonDeserialize(using = LongToDateJsonDeserializer.class)
    private Date aac101;
```
这样就能够实现上传的long转换为相对应日期，和响应的日期转换为long了。

### 改进，放弃在日期字段上加@JsonSerialize和@JsonDeserialize
如上，在Ac01的字段上添加@JsonSerialize和@JsonDeserialize是一个巨大的工程，所有类的日期类型都需要加这两个注解。
可以通过一种简单的配置来实现。

[LocalDateTimeSerializerConfig.java](src/main/java/com/example/lewjun/config/LocalDateTimeSerializerConfig.java)
```java
package com.example.lewjun.config;

import com.example.lewjun.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Configuration
public class LocalDateTimeSerializerConfig {

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JavaTimeModule javaTimeModule = new JavaTimeModule();

        javaTimeModule.addSerializer(Date.class, new DateToLongJsonSerializer());
        javaTimeModule.addDeserializer(Date.class, new LongToDateJsonDeserializer());

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateToLongJsonSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LongToLocalDateJsonDeserializer());

        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeToLongJsonSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LongToLocalDateTimeJsonDeserializer());

        objectMapper.registerModule(javaTimeModule);

        return objectMapper;
    }
}
```

然后Ac01回到最原始的状态。

```java
package com.example.lewjun.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class Ac01 extends BaseObj {
    private int aac001;
    private String aac002;
    private String aac003;
    private Integer aac004;
    private LocalDate aac005;
    private int aac006;
    private float aac007;
    private float aac008;
    private List<String> aac009;
    private Ab01 ab01;
    private LocalDateTime aac100;
    private Date aac101;
}
```
这样就在也不用在日期字段上添加那两个注解了。

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo
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

## postman文件

[postman文件](doc/spring-boot-recv-param.postman_collection.json)

