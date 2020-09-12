package com.example.lewjun.config;

import com.example.lewjun.util.PrintWriterUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权失败处理器
 *
 * @author huiye
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AccessDeniedException e) throws IOException, ServletException {

        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());

        final Map<String, Object> map = new HashMap<>(3);
        map.put("code", 0);
        map.put("msg", "access denied");
        map.put("data", e.getMessage());

        PrintWriterUtils.printlnAndFlush(httpServletResponse, map);
    }
}
