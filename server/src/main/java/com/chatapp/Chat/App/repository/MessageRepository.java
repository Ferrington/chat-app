package com.chatapp.Chat.App.repository;


import com.chatapp.Chat.App.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
//    Page<Message> getAllMessagesByChannelId(Long channelId, Pageable pageable);

//    @Query(value = "SELECT m FROM messages m " +
//            "JOIN channels c ON m.channel_id = c.id " +
//            "WHERE c.id = ?1 AND m.created < (SELECT created FROM messages WHERE id = ?2)" +
//            "ORDER BY m.created DESC LIMIT ?3", nativeQuery = true)
//    List<Message> findMessagesBeforeId(Long channelId, Long messageId, int size);

    @Query(value = "SELECT m.id, m.user_id, m.channel_id, m.created, m.updated, m.content FROM messages m " +
            "JOIN channels c ON m.channel_id = c.id " +
            "WHERE c.id = ?1 AND m.created < (SELECT created FROM messages WHERE id = ?2)" +
            "ORDER BY m.created DESC LIMIT ?3", nativeQuery = true)
    List<Message> findMessagesBeforeId(Long channelId, Long messageId, int size);

    @Query(value = "SELECT m.id, m.user_id, m.channel_id, m.created, m.updated, m.content FROM messages m " +
            "JOIN channels c ON m.channel_id = c.id " +
            "WHERE c.id = ?1 AND m.created > (SELECT created FROM messages WHERE id = ?2)" +
            "ORDER BY m.created DESC LIMIT ?3", nativeQuery = true)
    List<Message> findMessagesAfterId(Long channelId, Long messageId, int size);

    @Query(value = "SELECT m.id, m.user_id, m.channel_id, m.created, m.updated, m.content FROM messages m " +
            "JOIN channels c ON m.channel_id = c.id " +
            "WHERE c.id = ?1 " +
            "ORDER BY m.created DESC LIMIT ?2", nativeQuery = true)
    List<Message> findMostRecentMessages(Long channelId, int size);
}



