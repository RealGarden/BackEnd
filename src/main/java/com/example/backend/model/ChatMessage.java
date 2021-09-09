package com.example.backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessage {

    private MessageType type;
    private String content;


}