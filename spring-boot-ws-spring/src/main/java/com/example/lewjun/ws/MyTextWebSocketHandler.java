package com.example.lewjun.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;

@Slf4j
@Component
public class MyTextWebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        log.info("【afterConnectionEstablished】");

    }

    @Override
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("【afterConnectionClosed】");
    }

    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info("message: {}", message.getPayload());

        session.sendMessage(new TextMessage("Hello " + new Date()));
    }
}
