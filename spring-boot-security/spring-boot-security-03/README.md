# spring-boot-security-03

> 进阶使用 自定义Spring Security 的配置，使用UserDetailsService提供用户

[TOC]

代码copy from [../spring-boot-security-02](../spring-boot-security-02)

## 直接提供一个User对象

[SecurityConfig.java](src/main/java/com/example/lewjun/config/SecurityConfig.java)

```java
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());

        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return s -> User.withUsername("admin")// 用户名
                .password("admin")// 密码
                .authorities("ROLE_ADMIN") // 权限
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
```

## 使用BCryptPasswordEncoder对数据加密

```java
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
```


## 使用动态权限校验
> 通过在 SecurityConfig 配置文件 中配置对应路径所需要的角色，然后在设置用户拥有的角色，以此来判断用户是否能访问路径。
>  在我们实际的项目开发中，随着系统升级和迭代，我们开发出的接口越来越多，我们就不得不在配置文件中追加很多类似的代码，这不仅是费时费力，而且还对系统原有的代码造成一定的破坏，这明显是有大问题的。
>  如果我们可以像加载动态中账号那样，也动态加载权限，那就好了。
>  下面我们就来讨论如何动态加载权限吧
>  在 Security中，我们可以在配置认证和授权的策略中配置 对象后处理器 ObjectPostProcessor ,通过它我们可以自定义的判断每次请求url应该如何处理。

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

