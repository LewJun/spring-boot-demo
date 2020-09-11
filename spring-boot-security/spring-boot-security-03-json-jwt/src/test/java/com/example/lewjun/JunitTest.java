package com.example.lewjun;

import com.example.lewjun.util.JwtTokenUtils;
import com.example.lewjun.util.RequestUrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class JunitTest {
    @Test
    public void testSomething() {
        System.out.println(RequestUrlUtils.getRequestUrlWithoutQueryParams("/index/?"));
        final String tokenHeader = "Tiger eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTk4MjkzMDUsImlhdCI6MTU5OTgyOTAwNSwicm9sZXMiOiJbQURNSU5dIiwidXNlcm5hbWUiOiJhZG1pbiJ9.sp186nFaEy6Ff4NDEJ2FM3deFJaY7MhrQKCRG7kZK1k";
        final String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        System.out.println(token);
    }
}
