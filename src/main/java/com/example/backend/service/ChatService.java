package com.example.backend.service;

import com.example.backend.domain.Chat.ChatRoomDto;
import com.example.backend.entity.chat.ChatRoom;
import com.example.backend.model.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDto> chatRooms;

    @Autowired
    public ChatService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllChatRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDto findChatRoomById(String id) {
        return chatRooms.get(id);
    }

    public ChatRoomDto createChatRoom(String name) {
        String id = UUID.randomUUID().toString();
        ChatRoomDto newChatRoom = ChatRoomDto.handleAction(id, name);
        chatRooms.put(id, newChatRoom);
        return newChatRoom;
    }

    public void sendMessage(WebSocketSession session, ChatMessage chatMessage) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
