package com.example.lewjun.utils;

import com.example.lewjun.common.BussException;
import com.example.lewjun.common.EnumApiResultStatus;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于进行Token的加密和解密
 *
 * @author huiye
 */
@Slf4j
public class JwtTokenUtils {
    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String USERNAME = "USERNAME";

    public static final String ROLES = "ROLES";
    /**
     * 过期时间 秒
     */
    public static final long EXPIRATION = 5 * 60;
    /**
     * 密钥
     */
    private static final String APP_SECRET_KEY = "010101";

    private JwtTokenUtils() {
    }

    /**
     * 校验Token
     *
     * @return
     */
    public static Claims getClaims(final String token) {
        try {
            final Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (final ExpiredJwtException ex) {
            log.error("【出现异常：】", ex);
        }

        throw BussException.of(EnumApiResultStatus.TOKEN_INVALID);
    }

    /**
     * 生成Token
     */
    public static String createToken(final String username, final String roles) {
        final Map<String, Object> claims = new HashMap<>(1);
        claims.put(ROLES, roles);

        return Jwts
                .builder()
                .setSubject(username)
                .setClaims(claims)
                .claim(USERNAME, username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS256, APP_SECRET_KEY).compact();
    }

    public static String getUsername(final String token) {
        return getClaims(token).get(USERNAME, String.class);
    }

    public static String getRoles(final String token) {
        return getClaims(token).get(ROLES, String.class);
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
