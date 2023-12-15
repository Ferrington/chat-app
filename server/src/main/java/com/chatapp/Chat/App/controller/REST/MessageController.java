package com.chatapp.Chat.App.controller.REST;

import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping(path = "api/messages")
@PreAuthorize("hasAnyRole('USER')")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/{channelId}")
    public List<Message> getMessagesFromChannel(@PathVariable Long channelId,
                                                @RequestParam(required = false) Long messageId,
                                                @RequestParam(defaultValue = "20") int size,
                                                @RequestParam(defaultValue = "before") String direction) {
        return messageService.getMessagesFromChannel(channelId, messageId, size, direction);

    @PatchMapping
    public Message editMessage(@RequestBody Message editedMessage, Principal principal) {
        return messageService.editMessage(editedMessage, principal);
    }


    @DeleteMapping(path = "/{messageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable Long messageId, Principal principal) {
        messageService.deleteMessage(messageId, principal);
    }


}
