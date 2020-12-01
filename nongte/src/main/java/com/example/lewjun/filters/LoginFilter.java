package com.example.lewjun.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/prod/list"}, filterName = "loginFilter")
public class LoginFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final String uri = req.getRequestURI();
        log.info("doFilter {}", uri);

        final HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
//            resp.sendRedirect("");
            req.getRequestDispatcher("/signin").forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }
}
