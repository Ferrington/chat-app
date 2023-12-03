package com.chatapp.Chat.App.service;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    ChannelRepository channelRepository;

    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    public Channel getFirstChannel() {
        Optional<Channel> optChannel = channelRepository.findFirstByOrderById();

        if (optChannel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No channels exist!");
        }
        return optChannel.get();
    }
}
