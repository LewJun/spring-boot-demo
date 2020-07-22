# spring-boot-multi-env

> 演示spring boot 项目多环境配置

[TOC]


## [application.yml](src/main/resources/application.yml)

```yaml
server:
  servlet:
    context-path: /demo

spring:
  profiles:
    active: dev # 使用dev环境
```

## [application-dev.yml](src/main/resources/application-dev.yml)
```yaml
# 开发环境
env:
  name: "开发环境"

server:
  port: 1234
```

## [application-pre.yml](src/main/resources/application-pre.yml)
```yaml
# 预发布环境
env:
  name: "预发布环境"

server:
  port: 8080
```

## [application-prod.yml](src/main/resources/application-prod.yml)
```yaml
# 生产环境
env:
  name: "生产环境"

server:
  port: 8888
```

## [App.java](src/main/java/com/example/lewjun/App.java)

```java

/**
 * spring boot 启动类
 */
@SpringBootApplication
@RestController
@Slf4j
public class App {
    @Value("${env.name}")
    private String envName;

    @Value("${server.port}")
    private int serverPort;

    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "World") final String name) {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
        return String.format("Hello %s!", name);
    }

    @GetMapping("/")
    public String index() {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
        return "/";
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

* 直接运行
> java -jar xxx.jar

此时，控制台输出的日志：
```log
2020-07-22 09:50:36.155  INFO 10112 --- [  restartedMain] com.example.lewjun.App                   : The following profiles are active: dev
2020-07-22 09:50:38.868  INFO 10112 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 1234 (http)


2020-07-22 10:15:11.277  INFO 900 --- [io-1234-exec-10] com.example.lewjun.App                   : envName: 开发环境
2020-07-22 10:15:11.279  INFO 900 --- [io-1234-exec-10] com.example.lewjun.App                   : serverPort: 1234
```


* 在运行的时候指定环境

`--spring.profiles.active=pre`

> java -jar xxx.jar --spring.profiles.active=pre

## 测试用例
[AppTest.java](src/test/java/com/example/lewjun/AppTest.java)

```java

/**
 * spring boot 测试类
 */
@Slf4j
@SpringBootTest
public class AppTest {
    @Value("${env.name}")
    private String envName;

    @Value("${server.port}")
    private int serverPort;

    @Test
    public void testLoadContext() {
        log.info("envName: {}", envName);
        log.info("serverPort: {}", serverPort);
    }
}
```

## spring boot 多环境打包时如何只打包当前环境的配置文件

如上方式，实现了环境的指定，但是把每一种环境`application-*.yml`都放到jar中classes目录了，会导致打出的jar在任何环境都可以指定环境参数，可能由于没有或错误的指定，而导致故障。
所以，最好的方式是多环境打包时，只打包具体环境的配置文件。

### Step 1 配置profiles

```xml
    <profiles>
        <profile>
            <id>dev</id>
            <properties>
                <profiles.active>dev</profiles.active>
                <modifier/>
            </properties>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
        </profile>

        <profile>
            <id>pre</id>
            <properties>
                <profiles.active>pre</profiles.active>
                <modifier>-pre</modifier>
            </properties>
        </profile>

        <profile>
            <id>prod</id>
            <properties>
                <profiles.active>prod</profiles.active>
                <modifier>-prod</modifier>
            </properties>
        </profile>
    </profiles>
```

### Step 2 排除和保留所有的application-*.yml

```xml
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
                <excludes>
                    <!-- 排除所有的application-*.yml -->
                    <exclude>application-*.yml</exclude>
                </excludes>
            </resource>

            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <!-- 只保留application-x.yml -->
                    <include>application-${profiles.active}.yml</include>
                </includes>
            </resource>
        </resources>
    </build>
```

### @profiles.active@

```yaml
spring:
  profiles:
    active: @profiles.active@
```

### 打包
> mvn clean package -Ppre

通过指定-P来指定环境参数

再次打开jar包，classes目录中就只保留了相应的环境了。
```log
+----classes
        |
        +----com
        +----application.yml
        +----application-pre.yml
```

测试同事打好相应的环境的包，发邮件给运维同事，运维同事只需要执行`java -jar xxx.jar`就可以了，
而不用添加参数`--spring.profiles.active=yyy`指定环境了。其实就算指定了，也可能报错，除非是`pre`。
