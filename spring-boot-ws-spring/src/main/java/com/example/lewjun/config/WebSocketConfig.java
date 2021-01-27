package com.example.lewjun.config;

import com.example.lewjun.ws.MyHandshakeInterceptor;
import com.example.lewjun.ws.MyTextWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyTextWebSocketHandler myTextWebSocketHandler;

    @Autowired
    private MyHandshakeInterceptor myHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        registry.addHandler(myTextWebSocketHandler, "ws/test")
                .addInterceptors(myHandshakeInterceptor)
                .setAllowedOrigins("*")
        ;
    }
}
