import authService from '@/services/AuthService';
import type { User } from '@/types';
import axios from 'axios';
import { defineStore } from 'pinia';
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User>(JSON.parse(localStorage.getItem('user') ?? '{}'));
  const router = useRouter();

  watch(user, (user) => {
    axios.defaults.headers.common.Authorization = `Bearer ${user.accessToken}`;
  });

  async function login() {
    const response = await authService.login();
    const newUser: User = response.data;
    user.value = newUser;

    router.push({ name: 'channel', params: { channelId: 1 } });
    console.log(user.value);
  }

  return {
    user,
    login,
  };
});
