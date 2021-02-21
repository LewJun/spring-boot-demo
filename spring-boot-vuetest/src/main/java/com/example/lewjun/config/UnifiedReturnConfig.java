package com.example.lewjun.config;

import com.example.lewjun.common.ApiResult;
import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 设置统一返回和统一异常处理
 */
@Slf4j
@EnableWebMvc
@Configuration
public class UnifiedReturnConfig {
    @RestControllerAdvice
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
            return ApiResult.ok(obj);
        }
    }

    @ControllerAdvice
    static class ExceptionHandlerAdvice {

        @ExceptionHandler(BussException.class)
        @ResponseBody
        public ApiResult exceptionHandle(final BussException ex) {
            log.error("【出现异常BussException】", ex);
            return ApiResult.fail(ex.getStatus());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseBody
        public ApiResult exceptionHandle(final MethodArgumentNotValidException ex) {
            log.error("【出现异常MethodArgumentNotValidException】", ex);
            return ApiResult.fail(
                    ex.getBindingResult().getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining("],["))
            );
        }

        @ExceptionHandler(ConstraintViolationException.class)
        @ResponseBody
        public ApiResult exceptionHandle(final ConstraintViolationException ex) {
            log.error("【出现异常ConstraintViolationException】", ex);
            return ApiResult.fail(
                    ex.getConstraintViolations()
                            .stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining("],["))
            );
        }

        @ExceptionHandler(Throwable.class)
        @ResponseBody
        public ApiResult exceptionHandle(final Throwable throwable) {
            log.error("【出现异常】", throwable);
            return ApiResult.fail(EnumApiResultStatus.FAIL);
        }
    }
}
