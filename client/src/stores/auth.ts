import authService from '@/services/AuthService';
import { userSchema, type User } from '@/types';
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
    const newUser: User = userSchema.parse(response.data);
    user.value = newUser;

    router.push({ name: 'channel', params: { channelId: 1 } });
  }

  return {
    user,
    login,
  };
});
