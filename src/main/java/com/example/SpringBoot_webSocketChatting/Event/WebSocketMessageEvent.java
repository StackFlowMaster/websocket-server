package com.example.SpringBoot_webSocketChatting.Event;

import org.springframework.context.ApplicationEvent;

public class WebSocketMessageEvent extends ApplicationEvent {

    private String sessionId;
    private String message;

    public WebSocketMessageEvent(Object source, String sessionId, String message) {
        super(source);
        this.sessionId = sessionId;
        this.message = message;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public String getMessage() {
        return message;
    }
}
