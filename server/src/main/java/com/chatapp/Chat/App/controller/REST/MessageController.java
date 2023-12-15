package com.chatapp.Chat.App.controller.REST;


import com.chatapp.Chat.App.model.Message;

import com.chatapp.Chat.App.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping(path = "api/messages")
@PreAuthorize("hasAnyRole('USER')")
public class MessageController {

    @Autowired
    MessageService messageService;

    //TODO WIP
    @PatchMapping
    public Message editMessage(@RequestBody Message editedMessage, Principal principal) {
        return messageService.editMessage(editedMessage, principal);
    }

    //TODO
    public void deleteMessage(Long channelId, Principal principal) {
        messageService.deleteMessage();
    }


}
