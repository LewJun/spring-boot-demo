package com.example.lewjun.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * 决策管理器，判断请求的url是通过还是阻断.
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(final Authentication authentication, final Object o, final Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("【当前路径需要的角色信息: {}】", configAttributes);
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
                    .map(GrantedAuthority::getAuthority)
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

}
