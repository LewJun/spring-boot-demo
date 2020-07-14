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
