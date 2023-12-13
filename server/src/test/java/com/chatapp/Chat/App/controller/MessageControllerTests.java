package com.chatapp.Chat.App.controller;

import com.chatapp.Chat.App.model.Channel;
import com.chatapp.Chat.App.model.Message;
import com.chatapp.Chat.App.service.MessageService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTests {
    static private final Message MESSAGE_1 = new Message();
    static private final Message MESSAGE_2 = new Message();
    static private final Message MESSAGE_3 = new Message();
    static private final Message MESSAGE_4 = new Message();
    static private final Channel CHANNEL_ID_1 = new Channel(1L);
    static private final int PAGE = 0;
    static private final int SIZE = 2;

    static private final LocalDateTime MESSAGE_1_TIME = LocalDateTime.of(2023, 10, 11, 6, 30, 5);
    static private final LocalDateTime MESSAGE_2_TIME = LocalDateTime.of(2023, 12, 25, 6, 30, 5);
    static private final LocalDateTime MESSAGE_3_TIME = LocalDateTime.of(2023, 9, 25, 6, 30, 5);
    static private final LocalDateTime MESSAGE_4_TIME = LocalDateTime.of(2023, 11, 25, 6, 30, 5);

    static {
        MESSAGE_1.setChannel(CHANNEL_ID_1);
        MESSAGE_1.setContent("test content first");
        MESSAGE_1.setUpdated(MESSAGE_1_TIME);

        MESSAGE_2.setChannel(CHANNEL_ID_1);
        MESSAGE_2.setContent("test content second");
        MESSAGE_2.setUpdated(MESSAGE_2_TIME);

        MESSAGE_3.setChannel(CHANNEL_ID_1);
        MESSAGE_3.setContent("test content third");
        MESSAGE_3.setUpdated(MESSAGE_3_TIME);

        MESSAGE_4.setChannel(CHANNEL_ID_1);
        MESSAGE_4.setContent("test content fourth");
        MESSAGE_4.setUpdated(MESSAGE_4_TIME);
    }

    @MockBean
    private MessageService messageService;

    @Autowired
    private MockMvc mockMvc;

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

}
