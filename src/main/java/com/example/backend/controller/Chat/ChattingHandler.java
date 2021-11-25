package com.example.backend.controller.Chat;

import com.example.backend.controller.Component;
import com.example.backend.domain.Chat.ChatRoomDto;
import com.example.backend.entity.chat.ChatRoom;
import com.example.backend.model.ChatMessage;
import com.example.backend.service.ChatService;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChattingHandler extends TextWebSocketHandler {
    private ChatMessage ChatMessage;
    private ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        ChatMessage chatMessage = payload.readValue(payload, ChatMessage.class);
        ChatRoomDto chatRoomDto = chatService.findChatRoomById(chatMessage.getChatRoomId());
        chatRoomDto.handleAction(session, chatMessage, chatService);
    }
}