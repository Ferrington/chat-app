package com.chatapp.Chat.App.controller.websocket;

import com.chatapp.Chat.App.payload.MessageDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    @MessageMapping("/hello/{channelId}")
    @SendTo("/topic/greetings/{channelId}")
    public MessageDTO message(@DestinationVariable Long channelId, MessageDTO message) {
        return message;
    }
}
