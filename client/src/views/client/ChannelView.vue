<script setup lang="ts">
import ChannelMessages from '@/components/channel/ChannelMessages.vue';
import MessageBox from '@/components/channel/MessageBox.vue';
import { useSocketStore } from '@/stores/socket';
import type { Message } from '@/types';
import { storeToRefs } from 'pinia';
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const socketStore = useSocketStore();
const { setChannel } = socketStore;
const { connected } = storeToRefs(socketStore);
const messages = ref<Message[]>([]);

const channelId = ref<number>();

watch(
  [() => route.params.channelId, connected],
  async ([idStr, connected]) => {
    const id = Number(idStr);
    channelId.value = id;
    if (connected) {
      setChannel(id, messages);
    }
  },
  { immediate: true },
);
</script>

<template>
  <div class="container">
    <ChannelMessages :messages="messages" />
    <MessageBox />
  </div>
</template>

<style scoped>
.container {
  height: 100%;
  position: relative;
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr auto;
}
</style>
