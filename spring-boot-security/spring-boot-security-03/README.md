# spring-boot-security-02

> 进阶使用 自定义Spring Security 的配置，实现认证和授权控制

[TOC]

## SecurityConfig

[SecurityConfig.java](src/main/java/com/example/lewjun/config/SecurityConfig.java)

### 继承自WebSecurityConfigurerAdapter，重写configure方法

```java
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
}
```

### 配置认证（用户名/密码/角色）

配置多个用户，拥有不同的角色。

```java
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 使用内存中的InMemoryUserDetailsManager
                .inMemoryAuthentication()
//                .jdbcAuthentication()
//                .userDetailsService(userDetailsService)
                // 不使用PasswordEncoder密码编码器
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                // 配置admin用户 拥有ADMIN角色
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                // 配置normal用户 拥有NORMAL角色
                .withUser("normal").password("normal").roles("NORMAL")
        ;
    }
```

* .inMemoryAuthentication() 使用内存级别的 InMemoryUserDetailsManager Bean 对象，提供认证的用户信息
* Spring 内置了两种 UserDetailsManager 实现：
    * InMemoryUserDetailsManager，和「2. 快速入门」是一样的。
    * JdbcUserDetailsManager ，基于 JDBC的 JdbcUserDetailsManager 。
* 实际项目中，更多的是采用.userDetailsService，使用自定义实现的 UserDetailsService 实现类，更加灵活且自由的实现认证的用户信息的读取。
* .passwordEncoder，设置密码编译器，不设置会报错。
* 使用`NoOpPasswordEncoder`，也就是不使用密码编译器，生产环境下推荐使用`BCryptPasswordEncoder`
* .withUser用于配置用户，紧随其后的.roles用于配置该用户的角色名称

* 配置授权

配置`角色`所拥有的`授权`信息

```java
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                // 配置请求地址的权限 开始
                .authorizeRequests()
                // 所有用户都可以访问
                .antMatchers("/permitAll").permitAll()
                // 有ADMIN角色
                .antMatchers("/admin").hasRole("ADMIN")
                // 需要NORMAL角色
                .antMatchers("/normal").hasRole("NORMAL")//.access("hasRole('ROLE_NORMAL')")// 需要NORMAL角色
                // 任何请求，访问的用户都需要经过认证（登录）
                .anyRequest().authenticated()
                .and()
                // 设置form表单登录 登录路径 所有用户都可以访问
                .formLogin()/*.loginPage("/login")*/.permitAll()
                .and()
                // 设置登出 所有用户都可以访问
                .logout()/*.logoutUrl("/logout")*/.permitAll()
                // 配置请求地址的权限 结束
        ;
    }
```

调用authorizeRequests()方法，开始配置授权信息。下面是配置授权会使用到的方法：

* #(String... antPatterns) 方法，配置匹配的 URL 地址，基于 Ant 风格路径表达式 ，可传入多个。
* #permitAll() 方法，所有用户可访问。
* #denyAll() 方法，所有用户不可访问。
* #authenticated() 方法，登录用户可访问。
* #anonymous() 方法，无需登录，即匿名用户可访问。
* #rememberMe() 方法，通过 remember me 登录的用户可访问。
* #fullyAuthenticated() 方法，非 remember me 登录的用户可访问。
* #hasIpAddress(String ipaddressExpression) 方法，来自指定 IP 表达式的用户可访问。
* #hasRole(String role) 方法， 拥有指定角色的用户可访问。
* #hasAnyRole(String... roles) 方法，拥有指定任一角色的用户可访问。
* #hasAuthority(String authority) 方法，拥有指定权限(authority)的用户可访问。
* #hasAuthority(String... authorities) 方法，拥有指定任一权限(authority)的用户可访问。
* #access(String attribute) 方法，当 Spring EL 表达式的执行结果为 true 时，可以访问。

如果要自定义登录/登出界面，可以在`loginPage`和`logoutUrl`处设置，并实现相应的页面。

当授权和资源不匹配的时候，会出现403错误。

## Try it

* MacOS/Linux
    * ./mvnw spring-boot:run

* Windows
    * mvnw spring-boot:run

## package

mvn clean package -Dmaven.test.skip=true

## run jar

java -jar xxx.jar

