package com.example.backend.controller;

import com.example.backend.entity.ChatRoom;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.io.IOException;
/*
@Controller
public class ChatController {



    @MessageMapping("/broadcast")
    public void send(ChatRoom chatRoom) throws IOException {

        chatRoom.setSendTime(TimeUtils.getCurrentTimeStamp());
        //append message to txtFile
        chatRoomService.appendMessage(chatRoom);

        Long id = chatRoom.getChatRoomIdx();
        String url = "/user/" + id + "/queue/messages";
        simpMessage.convertAndSend(url, new ChatRoom(chatRoom.getContent(), chatRoom.getSenderName(), chatRoom.getSendTime()));
    }


}*/