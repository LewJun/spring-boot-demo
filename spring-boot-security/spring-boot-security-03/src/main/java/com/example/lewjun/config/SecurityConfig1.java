package com.example.lewjun.config;

import com.example.lewjun.service.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * 使用动态权限校验
 * 在 Security中，我们可以在配置认证和授权的策略中配置 对象后处理器 ObjectPostProcessor ,通过它我们可以自定义的判断每次请求url应该如何处理。
 */
@Slf4j
@Configuration
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Autowired
    private LoginUserService loginUserService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginUserService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(final O o) {
                        // 配置url元数据
                        o.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
                        // 配置url权限的决策器
                        o.setAccessDecisionManager(accessDecisionManager());
                        return o;
                    }
                })
                .anyRequest().authenticated()
                .and()
                // 设置form表单登录 登录路径 所有用户都可以访问
                .formLogin()/*.loginPage("/login")*/.permitAll()
                .and()
                // 设置登出 所有用户都可以访问
                .logout()/*.logoutUrl("/logout")*/.permitAll()
        ;
    }

    /**
     * 决策管理器，判断请求的url是通过还是阻断.
     */
    @Bean
    AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManager() {
            @Override
            public void decide(final Authentication authentication, final Object o, final Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
                log.info("【当前路径需要的授权信息: {}】", configAttributes);
                for (final ConfigAttribute configAttribute : configAttributes) {
                    final String needRole = configAttribute.getAttribute();
                    // 需要登录权限，但是已经登录的，直接通过
                    if ("login".equals(needRole)) {
                        if (authentication instanceof AnonymousAuthenticationToken) {
                            throw new AccessDeniedException("未登录或者登录失效");
                        } else {
                            return;
                        }
                    }

                    // 得到用户已有的授权信息，如果用户已有的授权信息在该路径需要的授权范围内，则放行。
                    if (authentication.getAuthorities().stream()
                            .map((Function<GrantedAuthority, String>) GrantedAuthority::getAuthority)
                            .anyMatch(s -> Objects.equals(s, needRole))
                    ) {
                        return;
                    }
                }

                throw new AccessDeniedException("没有访问权限");
            }

            @Override
            public boolean supports(final ConfigAttribute configAttribute) {
                return true;
            }

            @Override
            public boolean supports(final Class<?> aClass) {
                return true;
            }
        };
    }

    /**
     * FilterInvocationSecurityMetadataSource ，这个类主要是用来获取当前访问的地址需要哪些权限的，这是一个接口，我们可以实现它，动态的到数据源中获取。
     */
    @Bean
    FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource() {
        final String[] anonymousList = {
                "/login",
                "/captchaImage",
                "/profile/**",
                "/webjars/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/druid/**"
        };
        final String[] permitAllList = {
                "/permitAll",
                "/*.html",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        };

        return new FilterInvocationSecurityMetadataSource() {
            @Override
            public Collection<ConfigAttribute> getAttributes(final Object o) throws IllegalArgumentException {
                final FilterInvocation filterInvocation = (FilterInvocation) o;
                final String requestUrl = filterInvocation.getRequestUrl();
                log.info("【requestUrl: {}】", requestUrl);

                for (final String s : permitAllList) {
                    if (ANT_PATH_MATCHER.match(s, requestUrl)) {
                        log.info("【请求地址{}和忽略规则{}匹配，直接放行】", s, requestUrl);
                        return null;
                    }
                }

                for (final String s : anonymousList) {
                    if (ANT_PATH_MATCHER.match(s, requestUrl)) {
                        log.info("【请求地址{}和忽略规则{}匹配，直接放行】", s, requestUrl);
                        return null;
                    }
                }
                // 根据路径查询，该路径需要什么权限才能访问？
                final String[] permissions = findByPath(requestUrl);
                if (permissions != null && permissions.length > 0) {
                    return SecurityConfig.createList(permissions);
                }

                // 没有匹配上的资源，都是登录访问
                // 这里直接给一个权限login，用于标识登录后才能访问，此处做不处理。
                // 是否应该放行是决策管理器AccessDecisionManager需要做的事情。
                return SecurityConfig.createList("login");
            }

            private String[] findByPath(final String requestUrl) {
                final Map<String, String[]> map = new HashMap<>(2);
                map.put("/admin", new String[]{"ADMIN"});
                map.put("/normal", new String[]{"NORMAL", "USER"});
                for (final Map.Entry<String, String[]> me : map.entrySet()) {
                    if (ANT_PATH_MATCHER.match(me.getKey(), requestUrl)) {
                        return me.getValue();
                    }
                }

                return new String[0];
            }

            @Override
            public Collection<ConfigAttribute> getAllConfigAttributes() {
                return null;
            }

            @Override
            public boolean supports(final Class<?> aClass) {
                return FilterInvocation.class.isAssignableFrom(aClass);
            }
        };
    }
}
