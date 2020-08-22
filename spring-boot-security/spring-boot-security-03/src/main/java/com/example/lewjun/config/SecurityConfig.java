package com.example.lewjun.config;

import com.example.lewjun.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] anonymousList = {
            "/login",
            "/captchaImage",
            "/profile/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/druid/**"
    };

    private final String[] permitAllList = {
            "/permitAll",
            "/*.html",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"
    };
    @Autowired
    private LoginUserService loginUserService;

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
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = http.authorizeRequests();

        expressionInterceptUrlRegistry.antMatchers(permitAllList).permitAll();

        expressionInterceptUrlRegistry.antMatchers(anonymousList).anonymous();

        configHasRole(expressionInterceptUrlRegistry);

        expressionInterceptUrlRegistry.anyRequest().authenticated()
                .and()
                // 设置form表单登录 登录路径 所有用户都可以访问
                .formLogin()/*.loginPage("/login")*/.permitAll()
                .and()
                // 设置登出 所有用户都可以访问
                .logout()/*.logoutUrl("/logout")*/.permitAll()
        ;
    }

    private void configHasRole(final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {
        final String[] strs = {};

        // 对应的角色可以访问
        final Map<String, List<String>> hasRoleMap = new HashMap<>();
        hasRoleMap.put("ADMIN", Arrays.asList("/admin"));
        hasRoleMap.put("NORMAL", Arrays.asList("/normal"));
        hasRoleMap.forEach(new BiConsumer<String, List<String>>() {
            @Override
            public void accept(final String s, final List<String> strings) {
                expressionInterceptUrlRegistry.antMatchers(strings.toArray(strs)).hasRole(s);
            }
        });
    }

}
