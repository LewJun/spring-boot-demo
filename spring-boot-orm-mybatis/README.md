# spring-boot-orm-mybatis

> 一个spring boot整合mybatis的例子

[TOC]


## 添加mybatis依赖

```xml
    <dependencies>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.3</version>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
```

## 数据源配置
```yaml
# DataSource Config
spring:
  datasource:
    driver-class-name: org.h2.Driver
    schema: classpath:db/schema-h2.sql
    data: classpath:db/data-h2.sql
    url: jdbc:h2:mem:test
    username: root
    password: test

mybatis:
  # 扫描xml的位置
  mapper-locations: classpath:/mappers/**/*Mapper.xml
```

## mybatis 配置
用于扫描mapper的位置 
```java

/**
 * Mybatis 配置
 */
@Configuration
@MapperScan(basePackages = {"com.example.lewjun.mapper"})
@EnableTransactionManagement
public class MybatisConfig {
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

