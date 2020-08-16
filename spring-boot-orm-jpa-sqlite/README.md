# spring-boot-orm-jpa-sqlite

> 一个spring boot整合jpa + sqlite 的例子

[TOC]

## 添加依赖
```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <!--        hibernate新版本中去掉了sqlite的支持，如要使用需要导入jar包-->
        <dependency>
            <groupId>com.zsoltfabok</groupId>
            <artifactId>sqlite-dialect</artifactId>
        </dependency>
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
        </dependency>
    </dependencies>
```

## 数据源配置

```yaml
# DataSource Config
spring:
  datasource:
    driver-class-name: org.sqlite.JDBC
    url: jdbc:sqlite:/Users/huiye/Documents/scott.db3
    username:
    password:
  jpa:
    database-platform: org.hibernate.dialect.SQLiteDialect
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

