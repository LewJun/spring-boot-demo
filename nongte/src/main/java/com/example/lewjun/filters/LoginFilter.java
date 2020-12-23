package com.example.lewjun.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "loginFilter",
        urlPatterns = {
                "/prod/list",
                "/prod/query",
                "/prod/edit/*",
                "/prod/delete/*",
                "/prod/save",
                "/prod/create",
                "/prod/changeStatus",
                "/regions/select",
        }
)
public class LoginFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        final HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
//            resp.sendRedirect("");
            req.getRequestDispatcher("/signin").forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }
}
