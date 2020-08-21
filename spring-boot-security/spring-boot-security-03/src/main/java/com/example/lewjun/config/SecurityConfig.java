package com.example.lewjun.config;

import com.example.lewjun.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginUserService loginUserService;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return s -> User.withUsername("admin")// 用户名
//                .password("admin")// 密码
//                .authorities("ROLE_ADMIN") // 权限
//                .build();
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());

//        provider.setUserDetailsService(userDetailsService());
        provider.setUserDetailsService(loginUserService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
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
