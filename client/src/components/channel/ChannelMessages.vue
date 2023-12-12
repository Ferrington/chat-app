<script setup lang="ts">
import type { Message } from '@/types';
import TheMessage from './TheMessage.vue';

const props = defineProps<{ messages: Message[] }>();
</script>

<template>
  <div>
    <TheMessage v-for="message in props.messages" :key="message.id" :message="message" />
  </div>
</template>

<style scoped></style>
