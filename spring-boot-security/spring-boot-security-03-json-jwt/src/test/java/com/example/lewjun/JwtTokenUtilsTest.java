package com.example.lewjun;

import com.example.lewjun.util.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JwtTokenUtilsTest {
    private static final String NEW_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTk4Mjg5MTAsImlhdCI6MTU5OTgyODYxMCwicm9sZXMiOiJbUk9MRV9BRE1JTl0iLCJ1c2VybmFtZSI6ImFkbWluIn0.ob43poMBzk07-r5-NdxywkJLvLkzyMI2OakOTRfmw_g";

    @Test
    public void testCreateToken() {
        final List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        final String token = JwtTokenUtils.createToken("admin", roles.toString());
        log.info("【token: {}】", token);
    }

    @Test
    public void testCheckToken() {
        final Claims claims = JwtTokenUtils.getClaims(NEW_TOKEN);
        log.info("【claims: {}】", claims);
    }

    @Test
    public void testGetUsername() {
        log.info("【: {}】", JwtTokenUtils.getUsername(NEW_TOKEN));
    }


    @Test
    public void testGetRoles() {
        log.info("【: {}】", JwtTokenUtils.getRoles(NEW_TOKEN));
    }

    @Test
    public void testIsExpiredOut() {
        log.info("【isExpiredOut: {}】", JwtTokenUtils.isExpiredOut(NEW_TOKEN));
    }

    @Test
    public void testIsExpiredIn() {
        log.info("【isExpiredIn: {}】", JwtTokenUtils.isExpiredIn(NEW_TOKEN));
    }
}
