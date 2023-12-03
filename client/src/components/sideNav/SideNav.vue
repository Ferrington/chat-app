<script setup lang="ts">
import channelService from '@/services/ChannelService';
import { ref } from 'vue';
import { RouterLink } from 'vue-router';

const response = await channelService.getAll();
const channels = ref(response.data);
</script>

<template>
  <ul>
    <li v-for="channel in channels" :key="channel.id">
      <RouterLink :to="{ name: 'channel', params: { channelId: channel.id } }">{{
        channel.name
      }}</RouterLink>
    </li>
  </ul>
</template>

<style scoped></style>
