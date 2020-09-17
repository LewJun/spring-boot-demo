package com.example.lewjun.config;

import com.example.lewjun.common.ApiResult;
import com.example.lewjun.common.EnumApiResultStatus;
import com.example.lewjun.utils.PrintWriterUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 在我们访问接口时常常会遇到登录失效的问题，例如：登录超时、服务器重启这些都会导致登录失效。
 * Spring Security 在用户登录时效后，会自动跳转到了登录页面去，在前后端分离的情况下，我们希望能像登录授权那样，登录失效也能返回 JSON 字符串。
 * 如果用户未登录，就访问资源，则提示需要登录。
 *
 * @author huiye
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest httpServletRequest, final HttpServletResponse resp, final AuthenticationException e) throws IOException {
        PrintWriterUtils.printlnAndFlush(resp, ApiResult.fail(EnumApiResultStatus.AUTHENTICATION_INVALID));
    }
}
