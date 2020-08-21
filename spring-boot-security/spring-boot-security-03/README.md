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

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

