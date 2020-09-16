package com.example.lewjun.config;

import com.example.lewjun.exception.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 校验验证码是否正确
 *
 * @author huiye
 */
@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements Filter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Value("${ss.loginProcessingUrl}")
    private String loginProcessingUrl;

    @Value("${ss.usernameParameter}")
    private String usernameParameter;

    @Value("${ss.codeParameter}")
    private String codeParameter;

    @Override
    protected void doFilterInternal(final HttpServletRequest req, final HttpServletResponse resp, final FilterChain chain) throws ServletException, IOException {
        log.info("【ValidateCodeFilter doFilterInternal】");
        // req.getRequestURI() -> /demo/doLogin
        // req.getServletPath() -> /doLogin
        if (loginProcessingUrl.equals(req.getServletPath()) && HttpMethod.POST.matches(req.getMethod())) {
            try {
                validateCode(req);
            } catch (final AuthenticationException ex) {
                authenticationFailureHandler.onAuthenticationFailure(req, resp, ex);
            }
        }
        chain.doFilter(req, resp);
    }

    private void validateCode(final HttpServletRequest req) {
        final String code = req.getParameter(codeParameter);
        final String username = req.getParameter(usernameParameter);

        // 根据username和code查询是否存在
        if (Objects.isNull(code) || Objects.isNull(username) /*或其它原因*/) {
            throw new ValidateCodeException();
        }
        // queryByUsernameAndCode()
    }

}
