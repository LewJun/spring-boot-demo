package com.example.lewjun.config;

import com.example.lewjun.utils.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义登录成功，登录失败，无权限，登出成功的Handler。
 */
@Configuration
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                // 使用内存中的InMemoryUserDetailsManager
                .inMemoryAuthentication()
//                .jdbcAuthentication()
//                .userDetailsService(userDetailsService)
                // 不使用PasswordEncoder密码编码器
                // .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .passwordEncoder(passwordEncoder())
                // 配置admin用户 拥有ADMIN角色
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                // 配置normal用户 拥有NORMAL角色
                .withUser("normal").password(passwordEncoder().encode("normal")).roles("NORMAL")
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 禁用csrf
                .cors() // 设置可以跨域
                .and()
                // 配置请求地址的权限 开始
                .authorizeRequests()
                // 所有用户都可以访问
                .antMatchers("/doLogin", "/doLogout", "/permitAll").permitAll()
                // 有ADMIN角色
                .antMatchers("/admin").hasRole("ADMIN")
                // 需要NORMAL角色
                .antMatchers("/normal").hasRole("NORMAL")//.access("hasRole('ROLE_NORMAL')")// 需要NORMAL角色
                // 任何请求，访问的用户都需要经过认证（登录）
                .anyRequest().authenticated()

                .and()
                // 设置form表单登录 登录路径 所有用户都可以访问
                .formLogin()
                // 访问/doLogin接口，传入username和password参数过来即可。
                .loginProcessingUrl("/doLogin")
                // 登陆表单的字段名
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录成功处理器
                .successHandler(successHandler())
                // 登录失败处理器
                .failureHandler(failureHandler())

                // 在前后端分离的情况下，我们希望能像登录授权那样，登出成功后也能返回 JSON 字符串。
                // 注销处理器
                .and().logout().logoutUrl("/doLogout").logoutSuccessHandler(logoutSuccessHandler())

                // 设置没有权限访问的处理程序
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())

                // 在我们访问接口时常常会遇到登录失效的问题，例如：登录超时、服务器重启这些都会导致登录失效。
                // Spring Security 在用户登录时效后，会自动跳转到了登录页面去，在前后端分离的情况下，我们希望能像登录授权那样，登录失效也能返回 JSON 字符串。
                // 如果用户未登录，就访问资源，则提示需要登录。
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
        // 配置请求地址的权限 结束
        ;
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, resp, e) -> {
            resp.setContentType("application/json;charset=utf-8");

            final Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "Login is required before access this resource");
            map.put("data", e.getMessage());
            final PrintWriter writer = resp.getWriter();
            writer.println(JsonUtils.object2String(map));
            writer.flush();
        };
    }

    @Bean
    LogoutSuccessHandler logoutSuccessHandler() {
        return (httpServletRequest, resp, authentication) -> {
            resp.setContentType("application/json;charset=utf-8");

            final Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("msg", "logout success");
            map.put("data", authentication);
            final PrintWriter writer = resp.getWriter();
            writer.println(JsonUtils.object2String(map));
            writer.flush();
        };
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, ex) -> {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());

            final Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "access denied");
            map.put("data", ex.getMessage());
            final PrintWriter writer = httpServletResponse.getWriter();
            writer.println(JsonUtils.object2String(map));
            writer.flush();
        };
    }

    @Bean
    AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, ex) -> {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            final Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "login fail");
            map.put("data", ex.getMessage());
            final PrintWriter writer = httpServletResponse.getWriter();
            writer.println(JsonUtils.object2String(map));
            writer.flush();
        };
    }

    @Bean
    AuthenticationSuccessHandler successHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            final Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("msg", "login success");
            map.put("data", authentication);
            final PrintWriter writer = httpServletResponse.getWriter();
            writer.println(JsonUtils.object2String(map));
            writer.flush();
        };
    }

}
