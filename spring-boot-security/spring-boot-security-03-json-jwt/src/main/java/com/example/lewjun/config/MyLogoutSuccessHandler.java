package com.example.lewjun.config;

import com.example.lewjun.util.PrintWriterUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登出成功处理器
 *
 * @author huiye
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(final HttpServletRequest httpServletRequest, final HttpServletResponse resp, final Authentication authentication) throws IOException, ServletException {


        final Map<String, Object> map = new HashMap<>(3);
        map.put("code", 1);
        map.put("msg", "logout success");
        map.put("data", authentication);

        PrintWriterUtils.printlnAndFlush(resp, map);
    }
}
