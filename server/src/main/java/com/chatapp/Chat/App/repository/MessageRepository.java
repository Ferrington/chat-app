package com.chatapp.Chat.App.repository;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Fetches all messages in the channel
    List<Message> getMessageByChannel(Channel channel);
}
