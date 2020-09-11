package com.example.lewjun;

import com.example.lewjun.util.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class JwtTokenUtilsTest {
    private static final String NEW_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTk4MTg4MDMsImlhdCI6MTU5OTgxODY4MywidXNlcm5hbWUiOiJhZG1pbiJ9.Qa0C9-_X7ofdLtugSD9NBGk-YKW5CvOgkm1KhtrkiKU";

    @Test
    public void testCreateToken() {
        final String token = JwtTokenUtils.createToken("admin");
        log.info("【token: {}】", token);
    }

    @Test
    public void testCheckToken() {
        final Claims claims = JwtTokenUtils.getClaims(NEW_TOKEN);
        log.info("【claims: {}】", claims);
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
