package com.example.lewjun.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
public class MyHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(final ServerHttpRequest req, final ServerHttpResponse resp, final WebSocketHandler handler, final Map<String, Object> attr) throws Exception {
        log.info("【beforeHandshake: {}】", attr);
        return true;
    }

    @Override
    public void afterHandshake(final ServerHttpRequest req, final ServerHttpResponse resp, final WebSocketHandler handler, final Exception e) {
        log.info("afterHandshake");
    }
}
