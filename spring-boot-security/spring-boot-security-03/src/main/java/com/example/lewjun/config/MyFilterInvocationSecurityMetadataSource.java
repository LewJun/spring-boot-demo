package com.example.lewjun.config;

import com.example.lewjun.repository.SysRoleRepository;
import com.example.lewjun.util.RequestUrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Map;

/**
 * FilterInvocationSecurityMetadataSource ，这个类主要是用来获取当前访问的地址需要哪些权限的，这是一个接口，我们可以实现它，动态的到数据源中获取。
 */
@Slf4j
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Override
    public Collection<ConfigAttribute> getAttributes(final Object o) {
        final FilterInvocation filterInvocation = (FilterInvocation) o;

        final String rawRequestUrl = filterInvocation.getRequestUrl();
        log.info("【rawRequestUrl: {}】", rawRequestUrl);

        final String requestUrl = RequestUrlUtils.getRequestUrlWithoutQueryParams(rawRequestUrl);
        log.info("【requestUrl: {}】", requestUrl);

        for (final String s : sysRoleRepository.getPermitAllList()) {
            if (ANT_PATH_MATCHER.match(s, requestUrl)) {
                log.info("【请求地址{}和忽略规则{}匹配，直接放行】", s, requestUrl);
                return createList();
            }
        }

        for (final String s : sysRoleRepository.getAnonymousList()) {
            if (ANT_PATH_MATCHER.match(s, requestUrl)) {
                log.info("【请求地址{}和忽略规则{}匹配，直接放行】", s, requestUrl);
                return createList();
            }
        }

        // 根据路径查询，该路径需要什么权限才能访问？
        final Map<String, String[]> map = sysRoleRepository.findRolesByRequestUrl(requestUrl);
        if (map != null && !map.isEmpty()) {
            for (final Map.Entry<String, String[]> me : map.entrySet()) {
                if (ANT_PATH_MATCHER.match(me.getKey(), requestUrl)) {
                    return createList(me.getValue());
                }
            }
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
