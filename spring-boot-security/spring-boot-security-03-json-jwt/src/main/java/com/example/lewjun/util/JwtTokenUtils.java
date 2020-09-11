package com.example.lewjun.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于进行Token的加密和解密
 */
@Slf4j
public class JwtTokenUtils {
    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Tiger ";

    // 过期时间 秒
    public static final long EXPIRITION = 2 * 60;
    // 应用密钥
    public static final String APPSECRET_KEY = "APPSECRET_KEY";

    /**
     * 校验Token
     *
     * @return
     */
    public static Claims getClaims(final String token) {
        try {
            final Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (final ExpiredJwtException ex) {
            log.error("【出现异常：】", ex);
        }

        throw new RuntimeException("校验token失败。");
    }

    /**
     * 生成Token
     */
    public static String createToken(final String username) {
        final Map<String, Object> claims = new HashMap<>();

        return Jwts
                .builder()
                .setSubject(username)
                .setClaims(claims)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION * 1000))
                .signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
    }

    public static String getUsername(final String token) {
        return getClaims(token).get("username", String.class);
    }

    /**
     * 是否过期了
     *
     * @param token token
     * @return false 未过期
     */
    public static boolean isExpiredOut(final String token) {
        return getExpiration(token).before(new Date());
    }

    /**
     * 是否有效
     *
     * @param token token
     * @return true 有效
     */
    public static boolean isExpiredIn(final String token) {
        return !isExpiredOut(token);
    }

    public static Date getExpiration(final String token) {
        return getClaims(token).getExpiration();
    }

}
