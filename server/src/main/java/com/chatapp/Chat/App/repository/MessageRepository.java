package com.chatapp.Chat.App.repository;


import com.chatapp.Chat.App.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> getAllMessagesByChannelId(Long channelId, Pageable pageable);





}



