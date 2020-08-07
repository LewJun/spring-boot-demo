# spring-boot-read-properties

> 一个spring boot读取properties/yml的例子

[TOC]
## [ApplicationProperties](src/main/java/com/example/lewjun/model/ApplicationProperties.java)
```java
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApplicationProperties {
    @Value("${server.port}")
    private int port;

    @Value("${server.servlet.context-path}")
    private String contextPath;
}
```

## [App.java](src/main/java/com/example/lewjun/App.java)

```java
/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
public class App {

    @Autowired
    private ApplicationProperties applicationProperties;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    public String index() {
        return applicationProperties.toString();
    }
}
```

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo
```

## 使用Jasypt对配置加密
在application.yml中，有很多敏感信息，例如远程服务器数据库的相关信息，这样直接暴露在外面不好。

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia
    username: root
    password: root123456
    driver-class-name: com.mysql.cj.jdbc.Driver
```

最好是把url、username、password进行加密

### 添加依赖

```xml
            <!-- Jasypt Spring Boot Starter -->
            <dependency>
                <groupId>com.github.ulisesbocchio</groupId>
                <artifactId>jasypt-spring-boot-starter</artifactId>
                <version>2.1.2</version>
            </dependency>
```

### 配置jasypt

```yaml
jasypt:
  encryptor:
    # 加密算法
    algorithm: PBEWithMD5AndDES
    # 加密密钥
    password: pwd # ${JASYPT_PASSWORD} 不要把密钥直接写在这里，可以通过配置环境变量，或者命令行参数等等传入 --jasypt.encryptor.password=pwd
```

**注意：** 为了待会儿生成密文测试使用，所以这里把password写死在这里了。如果是发到正式环境，就不要这样干了，等于把密码告诉别人了，别人就可以解密了。


* 加密算法

    *　　BasicTextEncryptor   ->  PBEWithMD5AndDES
    
    *　　StrongTextEncryptor  ->  PBEWithMD5AndTripleDES

    *　　AES256TextEncryptor  ->  PBEWithHMACSHA512AndAES_256

### 生成密文测试

```java
@Slf4j
@SpringBootTest
public class AppTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testLoadContext() {
        log.info("【username: {}】", stringEncryptor.encrypt("root"));
        log.info("【password: {}】", stringEncryptor.encrypt("root123456"));
        log.info("【driver-class-name: {}】", stringEncryptor.encrypt("com.mysql.cj.jdbc.Driver"));
        log.info("【url: {}】", stringEncryptor.encrypt("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia"));
    }
}
```

生成结果

```log
com.example.lewjun.AppTest               : 【username: y9BNwZRLsLofuUXE2QL2Xg==】
com.example.lewjun.AppTest               : 【password: r1PqBEazfhzHaqvxBU7yPR+QlNUgf0oR】
com.example.lewjun.AppTest               : 【driver-class-name: rhAkOpda4L6wT9vyKyfhY7otS3hxscTdBVhCSWIGtFkb+hP5skcxZg==】
com.example.lewjun.AppTest               : 【url: lwPI/fQ+94O+QnWwdxIyJMZviR0ixSpk1EjILFKRQQvuJ2jy6SnX6mbsIKEpci02t0zc+sV70h4ZsjypClMZM52vvFDpn+bZMWdHrVbGLTnM7FYZ4MnCgPxBv6ckAkGOgClWJkJW2KNBYCtiWfZKmoVVgKks4t3pC3B++pAJ0yv0iqPK509E6kcJ4SbvXzeuwmQxFb7m8XHD1Fwd/jD5mg==】
```

根据如上得到的加密结果，设置到application.yml中
```yaml
spring:
  datasource:
    url: ENC(lwPI/fQ+94O+QnWwdxIyJMZviR0ixSpk1EjILFKRQQvuJ2jy6SnX6mbsIKEpci02t0zc+sV70h4ZsjypClMZM52vvFDpn+bZMWdHrVbGLTnM7FYZ4MnCgPxBv6ckAkGOgClWJkJW2KNBYCtiWfZKmoVVgKks4t3pC3B++pAJ0yv0iqPK509E6kcJ4SbvXzeuwmQxFb7m8XHD1Fwd/jD5mg==)
    username: ENC(y9BNwZRLsLofuUXE2QL2Xg==)
    password: ENC(r1PqBEazfhzHaqvxBU7yPR+QlNUgf0oR)
    driver-class-name: ENC(rhAkOpda4L6wT9vyKyfhY7otS3hxscTdBVhCSWIGtFkb+hP5skcxZg==)
```
注意，密文要用`ENC(xxx)`包裹

再次测试，获取解密后数据
```java
    @Autowired
    private DatasourceProperties datasourceProperties;

    @Test public void testDesDataSource() {
        log.info("【datasourceProperties: {}】", datasourceProperties);
    }
```

```log
com.example.lewjun.AppTest               : 【datasourceProperties: DatasourceProperties(url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia, username=root, password=root123456, driverClassName=com.mysql.cj.jdbc.Driver)】
```
数据已经是明文了。

其实密钥的安全完全在于password是否安全，password泄露了，就麻烦了。如果系统被破解，就会得到命令行历史纪录，得到启动参数，也可以从环境变量中获取变量。

所以不论是系统的password，还是应用或者个人的password，都应该常常更替。

## 命令行加解密

* 加密
```shell script
>>> java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="root" password=pwd algorithm=PBEWithMD5AndDES

----ENVIRONMENT-----------------

Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.65-b01



----ARGUMENTS-------------------

algorithm: PBEWithMD5AndDES
input: root
password: pwd



----OUTPUT----------------------

SWPf3paOIRqNCucJFy3bEg==

```

* 解密
```shell script
>>> java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="SWPf3paOIRqNCucJFy3bEg==" password=pwd algorithm=PBEWithMD5AndDES

----ENVIRONMENT-----------------

Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.65-b01



----ARGUMENTS-------------------

algorithm: PBEWithMD5AndDES
input: SWPf3paOIRqNCucJFy3bEg==
password: pwd



----OUTPUT----------------------

root

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

## 访问

curl http://localhost:1234/demo

ApplicationProperties(port=1234, contextPath=/demo)
