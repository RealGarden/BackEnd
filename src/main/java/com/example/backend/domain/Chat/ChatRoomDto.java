package com.example.backend.domain.Chat;


import com.example.backend.model.ChatMessage;
import com.example.backend.model.MessageType;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

public class ChatRoomDto {

    private String id;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public void handleAction(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getMessageType().equals(MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + " 님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    private void sendMessage(ChatMessage chatMessage, ChatService chatService) {
        sessions
                .parallelStream()
                .forEach(session -> chatService.sendMessage(session, chatMessage));
    }

}