package com.example.lewjun.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// ws://127.0.0.1:1234/demo/ws/test
@Slf4j
@ServerEndpoint("/ws/test")
@Component
public class WsServerEndpoint {
    private static final Map<String, Session> SESSIONMAP = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(final Session session) {
        log.info("onOpen: {}", session.getId());
        SESSIONMAP.put(session.getId(), session);
    }

    @OnClose
    public void onClose(final Session session) {
        log.info("onClose: {}", session.getId());
        SESSIONMAP.remove(session.getId());
    }

    @OnError
    public void onError(final Session session, final Throwable throwable) {
        log.info("onError: {}", session.getId());
    }

    @OnMessage
    public void onMessage(final String msg, final Session session) throws IOException {
        log.info("onMessage: {}, msg: {}", session.getId(), msg);
        // 发消息给自己
        session.getAsyncRemote().sendText("回声: " + session.getId() + " :: " + msg);

        // 群发
        for (final Map.Entry<String, Session> me : SESSIONMAP.entrySet()) {
            final String sessionId = me.getKey();
            if (sessionId.equals(session.getId())) {
                continue;
            }
            me.getValue().getAsyncRemote().sendText(session.getId() + "发来消息：" + msg);
        }
    }
}
