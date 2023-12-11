package com.chatapp.Chat.App.controller.REST;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/channels")
//@PreAuthorize("hasAnyRole('USER')")
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @GetMapping
    public List<Channel> getAllChannels() {
        return channelService.getAllChannels();
    }

    @GetMapping(path = "/first")
    public Channel getFirstChannel() {
        return channelService.getFirstChannel();
    }
}
