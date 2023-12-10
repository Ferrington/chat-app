package com.chatapp.Chat.App.controller.REST;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/messages")
@PreAuthorize("hasAnyRole('USER')")

public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping(path= "/{channelId}")
    public List<Message> getAllMessagesInChannel(@PathVariable Channel channelId) {
        List<Message> channelMessages = new ArrayList<>();

        try {
            channelMessages = messageService.getAllMessagesInChannel(channelId);
        } catch (Error e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return channelMessages;
    }

}
