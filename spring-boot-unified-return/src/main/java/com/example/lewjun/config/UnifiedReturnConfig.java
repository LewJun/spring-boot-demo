package com.example.lewjun.config;

import com.example.lewjun.common.ApiResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@EnableWebMvc
@Configuration
public class UnifiedReturnConfig {
    @RestControllerAdvice("com.example.lewjun.web")
    static class UnifiedReturnAdvice implements ResponseBodyAdvice<Object> {

        @Override
        public boolean supports(final MethodParameter methodParameter, final Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }

        @Override
        public Object beforeBodyWrite(final Object obj, final MethodParameter methodParameter, final MediaType mediaType, final Class<? extends HttpMessageConverter<?>> aClass, final ServerHttpRequest serverHttpRequest, final ServerHttpResponse serverHttpResponse) {
            if (obj instanceof ApiResult) {
                return obj;
            }
            return new ApiResult<>(obj);
        }
    }
}
