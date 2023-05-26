package com.example.SpringBoot_webSocketChatting.Handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    public enum MessageType {
        NEW,
        ROLE,
        HISTORY,
    }

    @JsonProperty
    private MessageType type;
    @JsonProperty
    private int senderNo;
    @JsonProperty
    private Object data;
}
