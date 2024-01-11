import axios from 'axios';

export const messageService = {
  getMessagesFromChannel(
    channelId: number,
    messageId: number | null = null,
    size: number = 20,
    direction: 'before' | 'after' = 'before',
  ) {
    const params = {
      messageId,
      size,
      direction,
    };

    return axios.get(`/messages/${channelId}`, { params });
  },
};
