package com.example.lewjun.config;

import com.example.lewjun.util.JwtTokenUtils;
import com.example.lewjun.util.PrintWriterUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        final Map<String, Object> map = new HashMap<>(3);
        map.put("code", 1);
        map.put("msg", "login success");
        final String token = JwtTokenUtils.createToken(authentication.getName(), authentication.getAuthorities().toString());
        map.put("data", token);

        PrintWriterUtils.printlnAndFlush(httpServletResponse, map);
    }
}
