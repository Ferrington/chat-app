package com.chatapp.Chat.App.controller.REST;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/messages")
@PreAuthorize("hasAnyRole('USER')")

public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping
    public List<Message> getAllMessagesInChannel(Channel channelId) {
        return messageService.getAllMessagesInChannel(channelId);
    }

}
