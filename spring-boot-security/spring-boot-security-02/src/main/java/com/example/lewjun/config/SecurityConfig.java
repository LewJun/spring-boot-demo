package com.example.lewjun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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

}
