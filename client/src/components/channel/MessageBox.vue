<script setup lang="ts">
import { useSocketStore } from '@/stores/socket';
import { ref } from 'vue';

const { sendMessage } = useSocketStore();
const text = ref('');
</script>

<template>
  <div>
    <form @submit.prevent="sendMessage(text)">
      <input type="text" v-model="text" />
      <button type="submit">Send</button>
    </form>
  </div>
</template>

<style scoped></style>
