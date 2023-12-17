package com.chatapp.Chat.App.repository;

import com.chatapp.Chat.App.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findFirstByOrderById();
}
