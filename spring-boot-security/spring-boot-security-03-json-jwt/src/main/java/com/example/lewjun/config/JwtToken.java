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

}
