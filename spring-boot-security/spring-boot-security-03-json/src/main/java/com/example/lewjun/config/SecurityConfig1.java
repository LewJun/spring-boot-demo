package com.example.lewjun.config;

import com.example.lewjun.exception.ValidateCodeException;
import com.example.lewjun.service.LoginUserService;
import com.example.lewjun.util.PrintWriterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义登录成功，登录失败，无权限，登出成功的Handler。
 */
@Configuration
@Slf4j
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginUserService);
        return provider;
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
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

                // 在【用户名密码认证过滤器】前设置一层【验证码过滤器】用于校验登录时输入验证码是否正确
                .and().addFilterBefore(validateCodeFilter(), UsernamePasswordAuthenticationFilter.class)

        // 配置请求地址的权限 结束
        ;
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (httpServletRequest, resp, e) -> {
            final Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "Login is required before access this resource");
            map.put("data", e.getMessage());

            PrintWriterUtils.printlnAndFlush(resp, map);
        };
    }

    @Bean
    LogoutSuccessHandler logoutSuccessHandler() {
        return (httpServletRequest, resp, authentication) -> {

            final Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("msg", "logout success");
            map.put("data", authentication);

            PrintWriterUtils.printlnAndFlush(resp, map);

        };
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, ex) -> {
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());

            final Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "access denied");
            map.put("data", ex.getMessage());

            PrintWriterUtils.printlnAndFlush(httpServletResponse, map);
        };
    }

    @Bean
    AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, ex) -> {
            final Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", ex.getMessage());

            final String data;
            if (ex instanceof UsernameNotFoundException) {
                data = "用户名不存在";
            } else if (ex instanceof LockedException) {
                data = "账号被锁定";
            } else if (ex instanceof DisabledException) {
                data = "账号被禁用";
            } else if (ex instanceof CredentialsExpiredException) {
                data = "密码过期";
            } else if (ex instanceof AccountExpiredException) {
                data = "账号过期";
            } else if (ex instanceof BadCredentialsException) {
                data = "账号密码输入有误";
            } else if (ex instanceof ValidateCodeException) {
                data = "验证码输入有误";
            } else {
                data = ex.getMessage();
            }

            map.put("data", data);

            PrintWriterUtils.printlnAndFlush(httpServletResponse, map);
        };
    }

    @Bean
    AuthenticationSuccessHandler successHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            final Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("msg", "login success");
            map.put("data", authentication);

            PrintWriterUtils.printlnAndFlush(httpServletResponse, map);
        };
    }


    @Bean
    ValidateCodeFilter validateCodeFilter() {
        return new ValidateCodeFilter();
    }

    private static class ValidateCodeFilter extends OncePerRequestFilter implements Filter {

        @Autowired
        private AuthenticationFailureHandler authenticationFailureHandler;

        @Override
        protected void doFilterInternal(final HttpServletRequest req, final HttpServletResponse resp, final FilterChain chain) throws ServletException, IOException {
            log.info("【ValidateCodeFilter doFilterInternal】");
            // req.getRequestURI() -> /demo/doLogin
            // req.getServletPath() -> /doLogin
            if ("/doLogin".equals(req.getServletPath()) && HttpMethod.POST.matches(req.getMethod())) {
                try {
                    validateCode(req);
                } catch (final AuthenticationException ex) {
                    authenticationFailureHandler.onAuthenticationFailure(req, resp, ex);
                }
            }
            chain.doFilter(req, resp);
        }

        private void validateCode(final HttpServletRequest req) {
            final String code = req.getParameter("code");
            final String username = req.getParameter("username");

            // 根据username和code查询是否存在
            if (Objects.isNull(code) || Objects.isNull(username) /*或其它原因*/) {
                throw new ValidateCodeException();
            }
            // queryByUsernameAndCode()
        }
    }
}
