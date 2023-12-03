<script setup lang="ts">
const props = defineProps(['messages']);
</script>

<template>
  <div>
    <div v-for="(message, index) in props.messages" :key="index">{{ message }}</div>
  </div>
</template>

<style scoped></style>
