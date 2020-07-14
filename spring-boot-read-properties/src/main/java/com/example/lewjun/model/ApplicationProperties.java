package com.example.lewjun.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApplicationProperties {
    @Value("${server.port}")
    private int port;

    @Value("${server.servlet.context-path}")
    private String contextPath;
}
