package com.example.lewjun.config;

import com.example.lewjun.common.ApiResult;
import com.example.lewjun.exception.ValidateCodeException;
import com.example.lewjun.utils.PrintWriterUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author huiye
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AuthenticationException ex) throws IOException, ServletException {
        final String msg;
        if (ex instanceof UsernameNotFoundException) {
            msg = "用户名不存在";
        } else if (ex instanceof LockedException) {
            msg = "账号被锁定";
        } else if (ex instanceof DisabledException) {
            msg = "账号被禁用";
        } else if (ex instanceof CredentialsExpiredException) {
            msg = "密码过期";
        } else if (ex instanceof AccountExpiredException) {
            msg = "账号过期";
        } else if (ex instanceof BadCredentialsException) {
            msg = "账号密码输入有误";
        } else if (ex instanceof ValidateCodeException) {
            msg = "验证码输入有误";
        } else {
            msg = ex.getMessage();
        }


        PrintWriterUtils.printlnAndFlush(httpServletResponse, ApiResult.fail(msg));
    }
}
