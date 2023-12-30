import authService from '@/services/AuthService';
import { userSchema, type User, type UserDTO } from '@/types';
import axios from 'axios';
import { defineStore } from 'pinia';
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User>(JSON.parse(localStorage.getItem('user') ?? '{}'));
  const router = useRouter();
  const loginFailed = ref(false);

  watch(user, (user) => {
    axios.defaults.headers.common.Authorization = `Bearer ${user.accessToken}`;
  });

  async function login(userDTO: UserDTO) {
    const response = await authService.login(userDTO);
    const newUser: User = userSchema.parse(response.data);
    user.value = newUser;
    if (response) {
      router.push({ name: 'channel', params: { channelId: 1 } });
    } else {
      loginFailed.value = true;
    }
  }

  return {
    user,
    login,
    loginFailed,
  };
});
