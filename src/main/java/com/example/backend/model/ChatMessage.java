package com.example.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {

    private String id;
    private String chatRoomId;
    private String sender;
    private String message;
    private MessageType messageType;
}