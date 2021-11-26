package com.example.backend.config;

import com.example.backend.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;


@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandler webSocketHandler;

    public WebSocketConfig(WebSocketHandler webSocketHandler){
        this.webSocketHandler=webSocketHandler;
    }
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(webSocketHandler, "/chat")
                .setAllowedOrigins("*");
    }
}