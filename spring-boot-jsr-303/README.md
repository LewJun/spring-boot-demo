# spring-boot-jsr-303

> JSR-303实现请求参数校验

[TOC]

## 添加依赖
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
```

## 在要校验的字段上添加约束

```java
    @NotNull
    private String aab003;
```

## 在要校验的参数实体上添加注解@Valid

```java
    @PostMapping
    public Ab01 postAb01(@RequestBody @Valid final Ab01 ab01) {
        ab01Service.save(ab01);
        return ab01;
    }
```

完成以上步骤后，测试保存ab01时，不上传aab003数据，此时就会出现异常：

```json
{
    "timestamp": "2020-07-18T11:43:57.543+00:00",
    "status": 400,
    "error": "Bad Request",
"message": "Validation failed for object='ab01'. Error count: 1",
    "errors": [
        {
            "codes": [
                "NotNull.ab01.aab003",
                "NotNull.aab003",
                "NotNull.java.lang.String",
                "NotNull"
            ],
            "arguments": [
                {
                    "codes": [
                        "ab01.aab003",
                        "aab003"
                    ],
                    "arguments": null,
                    "defaultMessage": "aab003",
                    "code": "aab003"
                }
            ],
            "defaultMessage": "must not be null",
            "objectName": "ab01",
            "field": "aab003",
            "rejectedValue": null,
            "bindingFailure": false,
            "code": "NotNull"
        }
    ],
    "path": "/demo/ab01s"
}
```


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

[spring-boot-restfull.postman_collection.json](doc/spring-boot-restfull.postman_collection.json)