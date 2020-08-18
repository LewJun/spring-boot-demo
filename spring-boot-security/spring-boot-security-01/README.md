# spring-boot-security-01

> 入门使用

[TOC]
## 添加依赖

添加spring-boot-starter-security依赖
```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
    </dependencies>
```

## 认证

添加依赖后，启动项目，访问 [http://localhost:1234/demo/hello](http://localhost:1234/demo/hello) ，会跳转到 [http://localhost:1234/demo/login](http://localhost:1234/demo/login) 页面，需要输入用户名和密码认证后才能访问接口。

因为还从来没有配置spring security，所以，UserDetailsServiceAutoConfiguration会自动创建一个用户名为“user”，密码为uuid随机的用户User在内存中。

在启动日志中可以看见产生的随机uuid密码

```log
2020-08-17 14:37:04.535  INFO 11920 --- [  restartedMain] .s.s.UserDetailsServiceAutoConfiguration : 

Using generated security password: 11472b78-eae8-41ff-bcb3-40a434a478f1
```

所以输入user/11472b78-eae8-41ff-bcb3-40a434a478f1即可登录。

## 取消认证（登出）

在登录端，访问 [http://localhost:1234/demo/logout](http://localhost:1234/demo/logout) 即可登出

## 覆盖默认用户名和密码

```yaml
spring:
  # SecurityProperties配置项
  security:
    # 配置默认的 InMemoryUserDetailsManager 的用户账号与密码
    user:
      name: user        # 账号
      password: user    # 密码
```

注意：这里只能配置`一个`用户

## 注解授权

* 对user配置一个角色'ADMIN'

```yaml
spring:
  # SecurityProperties配置项
  security:
    # 配置默认的 InMemoryUserDetailsManager 的用户账号与密码
    user:
      name: user        # 账号
      password: user    # 密码
      roles:
        - ADMIN
```

* 增加 @EnableGlobalMethodSecurity 注解，开启对 Spring Security 注解的方法，进行权限验证

```java
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@RestController
public class App {
    
}
```

* @PreAuthorize

在需要授权的方法上使用@PreAuthorize指定需要的角色或权限

```java
    @PreAuthorize("hasRole('ROLE_NORMAL')")
    @GetMapping("/normal")
    public String normal() {
        return "/normal";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_NORMAL')")
    @GetMapping("/adminOrNormal")
    public String adminOrNormal() {
        return "/adminOrNormal";
    }

    @DenyAll// 无效，登录者都可以访问
    @GetMapping("/denyAll")
    public String denyAll() {
        return "nobody can access";
    }
```

@PreAuthorize 注解，当 Spring EL 表达式的执行结果为 true 时，可以访问。

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

