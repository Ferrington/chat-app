package com.chatapp.Chat.App.model;

import com.chatapp.Chat.App.model.auth.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"messages"})
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @JsonIncludeProperties({"id", "username"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, insertable = false)
    private LocalDateTime created;
}
