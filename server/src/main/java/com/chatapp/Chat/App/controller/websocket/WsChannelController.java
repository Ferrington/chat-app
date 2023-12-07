package com.chatapp.Chat.App.controller.websocket;

import com.chatapp.Chat.App.payload.request.MessageDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WsChannelController {
    @MessageMapping("/channels/{channelId}")
    @SendTo("/topic/channels/{channelId}")
    public MessageDTO message(@DestinationVariable Long channelId, MessageDTO message, Principal principal) {
        return message;
    }
}
