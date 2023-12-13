package com.chatapp.Chat.App.service;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.model.auth.User;
import com.chatapp.Chat.App.payload.request.MessageDTO;
import com.chatapp.Chat.App.repository.MessageRepository;
import com.chatapp.Chat.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesFromChannel(Long channelId, Long messageId, int size, String direction) {
        if (messageId == null) {
            return messageRepository.findMostRecentMessages(channelId, size);
        }

        if (direction.equals("before")) {
            return messageRepository.findMessagesBeforeId(channelId, messageId, size);
        } else {
            return messageRepository.findMessagesAfterId(channelId, messageId, size);
        }
    }

    public Message save(Long channelId, MessageDTO message, Principal principal) {
        User currentUser = getUser(principal);

        Message newMessage = new Message(new Channel(channelId), currentUser, message.getContent());
        Message saveMessage = messageRepository.save(newMessage);
        Optional<Message> retrieveMessage = messageRepository.findById(saveMessage.getId());
        if (retrieveMessage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Message could not be found");
        }
        return retrieveMessage.get();
    }

    private User getUser(Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Logged in user does not exist");

        }
        return user.get();
    }
}
