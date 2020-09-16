package com.example.lewjun.config;

import com.example.lewjun.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * FilterInvocationSecurityMetadataSource ，这个类主要是用来获取当前访问的地址需要哪些权限的，这是一个接口，我们可以实现它，动态的到数据源中获取。
 *
 * @author huiye
 */
@Slf4j
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private JwtToken jwtToken;

    public String[] getAnonymousList() {
        return jwtToken.getAnonymousList();
    }

    public String[] getPermitAllList() {
        return jwtToken.getPermitAllList();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(final Object o) {
        final FilterInvocation filterInvocation = (FilterInvocation) o;

        // 包含了查询参数 /admin?x=1&y=2
//        final String rawRequestUrl = filterInvocation.getRequestUrl();
//        log.info("【rawRequestUrl: {}】", rawRequestUrl);
        // RequestUrlUtils.getRequestUrlWithoutQueryParams(rawRequestUrl);
        // 无查询参数 /admin
        final String requestUrl = filterInvocation.getRequest().getServletPath();
        log.info("【requestUrl: {}】", requestUrl);

        for (final String s : getPermitAllList()) {
            if (ANT_PATH_MATCHER.match(s, requestUrl)) {
                log.info("【请求地址{}和忽略规则{}匹配，直接放行】", s, requestUrl);
                return createList();
            }
        }

        for (final String s : getAnonymousList()) {
            if (ANT_PATH_MATCHER.match(s, requestUrl)) {
                log.info("【请求地址{}和忽略规则{}匹配，直接放行】", s, requestUrl);
                return createList();
            }
        }

        // 根据路径查询，该路径需要什么权限才能访问？
        final List<String> roles = sysRoleService.findRolesByRequestUrl(requestUrl);
        if (roles != null && !roles.isEmpty()) {
            final String[] arr = {};
            return createList(roles.toArray(arr));
        }

        // 没有匹配上的资源，都是登录访问
        // 这里直接给一个权限login，用于标识登录后才能访问，此处做不处理。
        // 是否应该放行是决策管理器AccessDecisionManager需要做的事情。
        return createList("login");
    }

    private Collection<ConfigAttribute> createList(final String... attributeNames) {
        return org.springframework.security.access.SecurityConfig.createList(attributeNames);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return createList();
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
