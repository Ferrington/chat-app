import { Client, type StompSubscription } from '@stomp/stompjs';
import { defineStore } from 'pinia';
import { ref, type Ref } from 'vue';

export const useSocketStore = defineStore('socket', () => {
  const client = new Client({ brokerURL: 'ws://localhost:8080/ws' });
  const channel = ref<number>();
  const channelSubscription = ref<StompSubscription>();
  const connected = ref(false);

  client.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    connected.value = true;
  };

  client.onDisconnect = () => {
    console.log('Disconnected');
    connected.value = false;
  };

  client.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
  };

  client.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
  };

  async function connect() {
    if (!client.active) {
      client.activate();
    }
  }

  async function disconnect() {
    if (client.active) {
      await client.deactivate();
    }
  }

  function setChannel(channelId: number, messages: Ref<string[]>) {
    channel.value = channelId;
    subscribeToChannel(messages);
  }

  function subscribeToChannel(messages: Ref<string[]>) {
    if (!client.active) {
      console.error('Could not subscribe to channel. Client not active.');
      return;
    }

    messages.value = [];
    channelSubscription.value?.unsubscribe();
    channelSubscription.value = client.subscribe(`/topic/greetings/${channel.value}`, (message) => {
      messages.value.push(JSON.parse(message.body).content as string);
    });
    console.log('Subscribed to channel: ' + channel.value);
  }

  function sendMessage(message: string) {
    client.publish({
      destination: `/ws/hello/${channel.value}`,
      body: JSON.stringify({ content: message }),
    });
  }

  return {
    connected,
    connect,
    disconnect,
    setChannel,
    sendMessage,
  };
});
