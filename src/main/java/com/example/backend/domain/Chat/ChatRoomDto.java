package com.example.backend.domain.Chat;


import com.example.backend.model.ChatMessage;
import com.example.backend.model.MessageType;
import com.example.backend.service.ChatService;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

public class ChatRoomDto {
    private String id;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoomDto(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public ChatRoomDto handleAction(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getMessageType().equals(MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + " 님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
        return null;
    }

    private void sendMessage(ChatMessage chatMessage, ChatService chatService) {
        sessions
                .parallelStream()
                .forEach(session -> chatService.sendMessage(session, chatMessage));
    }

}