package com.chatapp.Chat.App.service;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.model.auth.User;
import com.chatapp.Chat.App.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    public void testGetAllMessagesForChannel() {
        // Mock data
        Channel channelId1 = new Channel(1l);
        Channel channelId2 = new Channel(2l);
        User newUser = new User("testUser", "test.email.com", "password");

        Message message1 = new Message(channelId1, newUser, "test message one");
        Message message2 = new Message(channelId1, newUser, "test message two");

        Message message3 = new Message(channelId1, newUser, "test message three");
        Message message4 = new Message(channelId2, newUser, "test message four");


        List<Message> messageFrom1 = Arrays.asList(message1, message2);
        List<Message> messageFrom2 = Arrays.asList(message3, message4);

        // Mock repository behavior
        when(messageRepository.getMessagesByChannel(channelId1)).thenReturn(messageFrom1);
        when(messageRepository.getMessagesByChannel(channelId2)).thenReturn(messageFrom2);

        // Call the service method
        List<Message> result1 = messageService.getAllMessagesInChannel(channelId1);
        List<Message> result2 = messageService.getAllMessagesInChannel(channelId2);

        // Verify the result
        assertEquals(messageFrom1, result1);
        System.out.println("Channel 1 messages: " + messageFrom1);
        assertEquals(messageFrom2, result2);
        System.out.println("Channel 2 messages: " + messageFrom2);
    }
}
