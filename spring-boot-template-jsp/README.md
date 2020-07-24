# spring-boot-template-jsp

> 一个spring boot集成jsp的例子

[TOC]

## [pom.xml](pom.xml)

```xml
    <dependencies>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
        </dependency>
    </dependencies>

```

## [App.java](src/main/java/com/example/lewjun/App.java)

```java
@SpringBootApplication
@Controller
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("time", new Date());
        return "index";
    }
}
```

## [application.yml](src/main/resources/application.yml)

```yaml
server:
  port: 1234
  servlet:
    context-path: /demo

spring:
  mvc:
    view:
      prefix: /WEB-INF/jsp/
      suffix: .jsp

```

## index.jsp

[index.jsp](src/main/webapp/WEB-INF/jsp/index.jsp)

```jsp
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>index.jsp</title>
</head>
<body>
${time}
</body>
</html>
```

## 设置webapp为websource

Project Structure > Modules > Web > Web Resource Directories > + 

选中 src/main/webapp

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

