package com.chatapp.Chat.App.controller;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.model.auth.User;
import com.chatapp.Chat.App.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTests {

    static private final Channel CHANNEL_ID_1 = new Channel(1L);
    static private final User USER_1 = new User("user", "user@user.com", "password");
    static private final String STRING_1 = "test content first";
    static private final String STRING_2 = "test content second";
    static private final String STRING_3 = "test content third";
    static private final String STRING_4 = "test content fourth";

    static private Message MESSAGE_1 = new Message(CHANNEL_ID_1, USER_1, STRING_1);
    static private Message MESSAGE_2 = new Message(CHANNEL_ID_1, USER_1, STRING_2);
    static private Message MESSAGE_3 = new Message(CHANNEL_ID_1, USER_1, STRING_3);
    static private Message MESSAGE_4 = new Message(CHANNEL_ID_1, USER_1, STRING_4);

    static private final int PAGE = 0;
    static private final int SIZE = 2;

    static private final LocalDateTime MESSAGE_1_TIME = LocalDateTime.of(2023, 10, 11, 6, 30, 5);
    static private final LocalDateTime MESSAGE_2_TIME = LocalDateTime.of(2023, 12, 25, 6, 30, 5);
    static private final LocalDateTime MESSAGE_3_TIME = LocalDateTime.of(2023, 9, 25, 6, 30, 5);
    static private final LocalDateTime MESSAGE_4_TIME = LocalDateTime.of(2023, 11, 25, 6, 30, 5);

    static {

        MESSAGE_1.setUpdated(MESSAGE_1_TIME);
        MESSAGE_2.setUpdated(MESSAGE_2_TIME);
        MESSAGE_3.setUpdated(MESSAGE_3_TIME);
        MESSAGE_4.setUpdated(MESSAGE_4_TIME);
    }

    @MockBean
    @Autowired
    private MessageService messageService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();



//    @Test
//    @WithMockUser
//    public void getAllMessagesInChannel_endpoint_mapped() throws Exception {
//        Page<Message> messages = messageService.getAllMessagesInChannel(CHANNEL_ID_1, PAGE, SIZE);
//
//        when(messageService.getAllMessagesInChannel(CHANNEL_ID_1, PAGE, SIZE)).thenReturn(messages);
//
//        mockMvc.perform(get("/api/messages/{channelId}", CHANNEL_ID_1.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].content").value("test content second"))
//                .andExpect(jsonPath("$.content[1].content").value("test content fourth"));
//
//    }

    @Test
    @WithMockUser
    public void getAllMessagesInChannel_endpoint_mapped() throws Exception {
        List<Message> messageList = Arrays.asList(MESSAGE_1, MESSAGE_2, MESSAGE_3, MESSAGE_4);
        messageList.sort(Comparator.comparing(Message::getUpdated).reversed());

        PageRequest pageRequest = PageRequest.of(PAGE, SIZE);
        Page<Message> messages = new PageImpl<>(messageList, pageRequest, messageList.size());
        System.out.println(messages.getContent());


        when(messageService.getAllMessagesInChannel(CHANNEL_ID_1, PAGE, SIZE)).thenReturn((messages));

        mockMvc.perform(get("/api/messages/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].content").value("test content second"))
                .andExpect(jsonPath("$.content[1].content").value("test content fourth"))
                .andExpect(jsonPath("$.content[2].content").value("test content one"))
                .andExpect(jsonPath("$.content[3].content").value("test content three"));

    }



}
