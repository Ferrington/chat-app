<script setup lang="ts">
import channelService from '@/services/ChannelService';
import { ref } from 'vue';
import { RouterLink } from 'vue-router';
import { useAuthStore } from '@/stores/auth';

const response = await channelService.getAll();
const channels = ref(response.data);
const { logout } = useAuthStore();
</script>

<template>
  <div class="side-nav">
    <ul>
      <li v-for="channel in channels" :key="channel.id">
        <RouterLink :to="{ name: 'channel', params: { channelId: channel.id } }">
          {{ channel.name }}
        </RouterLink>
      </li>
    </ul>

    <div class="logout-btn">
      <div><button id="" type="button" @click="logout">Logout</button></div>
    </div>
  </div>
</template>

<style scoped>
.side-nav {
  display: flex;
  flex-direction: column;
}
</style>
