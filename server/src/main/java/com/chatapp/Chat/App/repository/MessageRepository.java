package com.chatapp.Chat.App.repository;


import com.chatapp.Chat.App.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chatapp.Chat.App.model.Channel;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Fetches all messages in the channel
    // Currently is returning all columns from messages entity and not just content column
    List<Message> getMessagesByChannel(Channel channelId);
}
