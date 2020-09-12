package com.example.lewjun.config;

import com.example.lewjun.exception.ValidateCodeException;
import com.example.lewjun.util.PrintWriterUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证失败处理器
 *
 * @author huiye
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AuthenticationException ex) throws IOException, ServletException {

        final Map<String, Object> map = new HashMap<>(3);
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
    }
}
