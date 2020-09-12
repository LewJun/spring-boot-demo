package com.example.lewjun.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 自定义登录成功，登录失败，无权限，登出成功的Handler。
 *
 * @author huiye
 */
@Configuration
@Slf4j
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private MyDaoAuthenticationProvider myDaoAuthenticationProvider;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Bean
    JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JWTAuthorizationFilter(authenticationManager());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(myDaoAuthenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 禁用csrf
                .cors() // 设置可以跨域
                .and()
                // 配置请求地址的权限 开始
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(final O o) {
                        // 配置url元数据
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        // 配置url权限的决策器
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                // 任何请求，访问的用户都需要经过认证（登录）
                .anyRequest().authenticated()
                .and()
                // 设置form表单登录 登录路径 所有用户都可以访问
                .formLogin()
                // 访问/doLogin接口，传入username和password参数过来即可。
                .loginProcessingUrl(jwtToken.getLoginProcessingUrl())
                // 登陆表单的字段名
                .usernameParameter(jwtToken.getUsernameParameter())
                .passwordParameter(jwtToken.getPasswordParameter())
                // 登录成功处理器
                .successHandler(myAuthenticationSuccessHandler)
                // 登录失败处理器
                .failureHandler(myAuthenticationFailureHandler)

                // 在前后端分离的情况下，我们希望能像登录授权那样，登出成功后也能返回 JSON 字符串。
                // 注销处理器
                .and().logout().logoutUrl(jwtToken.getLogoutUrl()).logoutSuccessHandler(myLogoutSuccessHandler)

                // 设置没有权限访问的处理程序
                .and().exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)

                // 在我们访问接口时常常会遇到登录失效的问题，例如：登录超时、服务器重启这些都会导致登录失效。
                // Spring Security 在用户登录时效后，会自动跳转到了登录页面去，在前后端分离的情况下，我们希望能像登录授权那样，登录失效也能返回 JSON 字符串。
                // 如果用户未登录，就访问资源，则提示需要登录。
                .and().exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)

                // 在【用户名密码认证过滤器】前设置一层【验证码过滤器】用于校验登录时输入验证码是否正确
                .and().addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)

                // jwt 权限认证
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)

                // 设置Session的创建策略为：Spring Security永不创建HttpSession 不使用HttpSession来获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // 配置请求地址的权限 结束
        ;
    }
}
