package com.chatapp.Chat.App.service;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.model.auth.User;
import com.chatapp.Chat.App.payload.request.MessageDTO;
import com.chatapp.Chat.App.repository.MessageRepository;
import com.chatapp.Chat.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.security.Principal;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;
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

    @Transactional
    public Page<Message> getAllMessagesInChannel(Channel channelId, int page, int size) {
        //Currently sorting by last message first
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.desc("updated")));
        Page<Message> messagesInChannel = null;
        try {
            messagesInChannel = messageRepository.getAllMessagesByChannelId(channelId.getId(), pageRequest);
            if (messagesInChannel.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found in channel: " + channelId);
            }
        } catch (Exception e) {
            //TODO Need to put better error message here
            System.err.println("Error getting messages for channel: " + channelId);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
        return messagesInChannel;
    }







}
