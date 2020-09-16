package com.example.lewjun.config;

import com.example.lewjun.common.ApiResult;
import com.example.lewjun.utils.JwtTokenUtils;
import com.example.lewjun.utils.PrintWriterUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 *
 * @author huiye
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest httpServletRequest,
                                        final HttpServletResponse httpServletResponse,
                                        final Authentication authentication) throws IOException {
        final String token = JwtTokenUtils.createToken(authentication.getName(), authentication.getAuthorities().toString());
        PrintWriterUtils.printlnAndFlush(httpServletResponse, ApiResult.ok(token));
    }
}
