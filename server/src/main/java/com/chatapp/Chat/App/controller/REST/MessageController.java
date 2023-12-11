package com.chatapp.Chat.App.controller.REST;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path = "api/messages")
@PreAuthorize("hasAnyRole('USER')")

public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/{channelId}")
    public Page<Message> getAllMessagesInChannel(@PathVariable Channel channelId,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "20") int size) {
        return messageService.getAllMessagesInChannel(channelId, page, size);
    }



}
