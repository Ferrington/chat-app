<script setup lang="ts">
import type { Message } from '@/types';

const props = defineProps<{ message: Message }>();

function formatDate(date: Date) {
  return date.toLocaleDateString() + ' at ' + date.toLocaleTimeString();
}
</script>

<template>
  <div class="message">
    <div class="img">
      <div class="placeholder-img">{{ props.message.username[0] }}</div>
    </div>
    <div class="right-side">
      <div class="top-line">
        <div class="username">{{ props.message.username }}</div>
        <div class="time-stamp">{{ formatDate(props.message.created) }}</div>
      </div>
      <div class="message">{{ props.message.content }}</div>
    </div>
  </div>
</template>

<style scoped>
.message {
  display: grid;
  grid-template-columns: auto 1fr;
}

.right-side {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.img {
  grid-row: 1 / span 2;
  padding: 10px;
}

.placeholder-img {
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid black;
  border-radius: 25px;
}

.top-line {
  display: flex;
  gap: 20px;
  align-items: baseline;
}

.username {
  font-weight: bold;
  font-size: 1.2rem;
}
</style>
