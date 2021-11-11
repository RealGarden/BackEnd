package com.example.backend.controller.Chat;

import com.example.backend.controller.Component;
import com.example.backend.domain.Chat.ChatService;
import com.example.backend.entity.chat.ChatRoom;
import com.example.backend.model.ChatMessage;
import com.example.backend.model.MessageType;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Component
public class ChattingHandler extends TextWebSocketHandler {

    private Object ChatMessage;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        ChatMessage chatMessage = payload.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom = chatService.findChatRoomById(chatMessage.getchatRoomIdx());
        chatRoom.handleAction(session, chatMessage, ChatMessage);
    }


}
