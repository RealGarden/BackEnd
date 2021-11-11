package com.example.backend.controller.Chat;

import com.example.backend.entity.chat.ChatRoom;
import com.example.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ChatRoom createChatRoom(@RequestParam String name) {
        return chatService.createChatRoom(name);
    }

    @GetMapping
    public List<ChatRoom> getAllChatRoom() {
        return chatService.findAllChatRoom();
    }
}