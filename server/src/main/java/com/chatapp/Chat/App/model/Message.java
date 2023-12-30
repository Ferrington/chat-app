package com.chatapp.Chat.App.model;

import com.chatapp.Chat.App.model.auth.User;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    private LocalDateTime updated;

    @Transient
    private Long userId;

    @Transient
    private String username;

    @Transient
    private Long channelId;

    public Message(Channel channel, User user, String content) {
        this.channel = channel;
        this.user = user;
        this.content = content;
    }

    @JsonGetter("userId")
    public Long getUserId() {
        if (user != null) {
            userId = user.getId();
        }
        return userId;
    }

    @JsonGetter("username")
    public String getUsername() {
        if (user != null) {
            username = user.getUsername();
        }
        return username;
    }

    @JsonGetter("channelId")
    public Long getChannelId() {
        if (channel != null) {
            channelId = channel.getId();
        }
        return channelId;
    }
}
