package com.example.SpringBoot_webSocketChatting.Handler;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import antlr.Utils;
import com.example.SpringBoot_webSocketChatting.Entity.TblConnection;
import com.example.SpringBoot_webSocketChatting.Service.TblConnectionService;
import com.example.SpringBoot_webSocketChatting.Service.WebSocketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

	@Autowired
	TblConnectionService tblConnectionService;

	@Autowired
	WebSocketService webSocketService;

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {

		var sessionId = session.getId();
		String payload = textMessage.getPayload();

		System.out.println("payload >>> " + payload);

		webSocketService.messageProcessing(sessionId, payload);
	}

	// Client가 접속 시 호출되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {

		var sessionId = session.getId();
		webSocketService.addConnection(sessionId, session);

		var remoteAddress = session.getRemoteAddress();
		var ipaddress = remoteAddress.getAddress().toString().replaceAll("/", "");

		tblConnectionService.saveTblConnection(
				TblConnection.builder().sessionId(sessionId)
						.connectionDate(new Date())
						.connectionIp(ipaddress)
						.connectionStatus(1)
						.createdAt(new Date())
						.updatedAt(new Date())
						.build()
		);
		/*
		Message message = Message.builder().sender(sessionId).receiver("all").build();
		message.newConnect();

		sessionMap.values().forEach(s -> {
			try {
				if (!s.getId().equals(sessionId)) {
					ObjectMapper objectMapper = new ObjectMapper();

					String json = objectMapper.writeValueAsString(message);

					System.out.println(json);

					s.sendMessage(new TextMessage(json));
				}
			}
			catch (Exception e) {
				//TODO: throw
			}
		});
		 */
	}

	// Client가 접속 해제 시 호출되는 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		var sessionId = session.getId();

		webSocketService.removeConnection(sessionId);

		var tblConnection = tblConnectionService.findBySessionId(sessionId);

		if (tblConnection != null) {
			tblConnection.setConnectionStatus(0);
			tblConnection.setUpdatedAt(new Date());
			tblConnectionService.saveTblConnection(tblConnection);
		}

		/*
		final Message message = new Message();
		message.closeConnect();
		message.setSender(sessionId);

		sessionMap.values().forEach(s -> {
			try {
				ObjectMapper objectMapper = new ObjectMapper();

				String json = objectMapper.writeValueAsString(message);

				System.out.println(json);

				s.sendMessage(new TextMessage(json));
			} catch (Exception e) {
				//TODO: throw
			}
		});
		 */
	}

	// Client 소켓 통신 에러
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		var sessionId = session.getId();

		webSocketService.removeConnection(sessionId);

		var tblConnection = tblConnectionService.findBySessionId(sessionId);

		if (tblConnection != null) {
			tblConnection.setConnectionStatus(0);
			tblConnection.setUpdatedAt(new Date());
			tblConnectionService.saveTblConnection(tblConnection);
		}
	}
}
