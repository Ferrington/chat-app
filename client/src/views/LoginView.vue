<script setup lang="ts">
import { useAuthStore } from '@/stores/auth';

const { login } = useAuthStore();
</script>

<template>
  <div><button type="button" @click="login">Login</button></div>
</template>

<style scoped></style>
