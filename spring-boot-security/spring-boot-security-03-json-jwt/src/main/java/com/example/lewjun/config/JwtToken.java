package com.example.lewjun.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class JwtToken {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("jwt.tokenPrefix")
    private String tokenPrefix;

    @Value("jwt.expiration")
    private String expiration;

    @Value("${ss.loginProcessingUrl}")
    private String loginProcessingUrl;

    @Value("${ss.logoutUrl}")
    private String logoutUrl;

    @Value("${ss.usernameParameter}")
    private String usernameParameter;

    @Value("${ss.passwordParameter}")
    private String passwordParameter;

    @Value("${ss.permitAllList}")
    private String[] permitAllList;

    @Value("${ss.anonymousList}")
    private String[] anonymousList;
}
