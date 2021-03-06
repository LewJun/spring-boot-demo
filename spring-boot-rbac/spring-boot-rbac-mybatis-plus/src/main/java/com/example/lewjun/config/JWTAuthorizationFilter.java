package com.example.lewjun.config;

import com.example.lewjun.utils.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * jwt 授权过滤器
 *
 * @author huiye
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        final Authentication authentication = getAuthentication(request, tokenHeader);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        super.doFilterInternal(request, response, chain);
    }

    private Authentication getAuthentication(final HttpServletRequest request, final String tokenHeader) {
        final String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiredOut(token)) {
            return null;
        }

        final String username = JwtTokenUtils.getUsername(token);

        if (username != null) {
            // 将[ROLE_XXX,ROLE_YYY]格式的角色字符串转换为数组
            final String role = JwtTokenUtils.getRoles(token);
            final String[] roles = StringUtils.strip(role, "[]").split(", ");
            final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (final String s : roles) {
                authorities.add(new SimpleGrantedAuthority(s));
            }

            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            return usernamePasswordAuthenticationToken;
        }
        return null;
    }
}
