package com.example.lewjun.config;

import com.example.lewjun.common.ApiResult;
import com.example.lewjun.utils.PrintWriterUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败处理器
 *
 * @author huiye
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AccessDeniedException e) throws IOException {
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        PrintWriterUtils.printlnAndFlush(httpServletResponse, ApiResult.fail(e.getMessage()));
    }
}
