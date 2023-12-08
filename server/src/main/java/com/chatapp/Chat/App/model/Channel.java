package com.chatapp.Chat.App.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "channels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String name;

    @Size(max = 255)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Message> messages;

    public Channel(Long id) {
        this.id = id;
    }
}
