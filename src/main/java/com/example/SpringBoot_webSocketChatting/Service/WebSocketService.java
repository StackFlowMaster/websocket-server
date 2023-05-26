package com.example.SpringBoot_webSocketChatting.Service;

import com.example.SpringBoot_webSocketChatting.Event.WebSocketMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class WebSocketService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TblConnectionService tblConnectionService;

    private	final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    public void messageProcessing(String sessionId, String message) {
        publisher.publishEvent(new WebSocketMessageEvent(this, sessionId, message));
    }

    public void addConnection(String sessionId, WebSocketSession webSocketSession) {
        sessionMap.put(sessionId, webSocketSession);
    }

    public void removeConnection(String sessionId) {
        sessionMap.remove(sessionId);
    }

    public boolean sendMessage(String sessionId, String message) throws IOException {
        if (sessionMap.get(sessionId) != null) {
            WebSocketSession session = sessionMap.get(sessionId);
            session.sendMessage(new TextMessage(message));
            return true;
        } else {
            var tblConnection = tblConnectionService.findBySessionId(sessionId);

            if (tblConnection != null) {
                tblConnection.setConnectionStatus(0);
                tblConnection.setUpdatedAt(new Date());
                tblConnectionService.saveTblConnection(tblConnection);
            }
        }

        return false;
    }
}
