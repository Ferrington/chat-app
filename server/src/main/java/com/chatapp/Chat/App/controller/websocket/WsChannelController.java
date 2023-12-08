package com.chatapp.Chat.App.controller.websocket;

import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.payload.request.MessageDTO;
import com.chatapp.Chat.App.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WsChannelController {
    @Autowired
    MessageService messageService;
    @MessageMapping("/channels/{channelId}")
    @SendTo("/topic/channels/{channelId}")
    public Message message(@DestinationVariable Long channelId, MessageDTO message, Principal principal) {
         return messageService.save(channelId, message, principal);
    }
}
