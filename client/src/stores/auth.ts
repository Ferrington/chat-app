import authService from '@/services/AuthService';
import { userSchema, type User } from '@/types';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { defineStore } from 'pinia';
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User>(JSON.parse(localStorage.getItem('user') ?? '{}'));
  const router = useRouter();
  const logoutTimer = ref<number>(0);

  watch(user, (user) => {
    axios.defaults.headers.common.Authorization = `Bearer ${user.accessToken}`;

    if (!('accessToken' in user)) {
      return;
    }

    try {
      const decodedToken = jwtDecode(user.accessToken);
      if (decodedToken.exp == null) {
        return;
      }
      autoLogout(decodedToken.exp);
    } catch (error) {
      console.error('Error decoding token:', error);
    }
  });

  async function login() {
    const response = await authService.login();
    const newUser: User = userSchema.parse(response.data);

    user.value = newUser;

    router.push({ name: 'channel', params: { channelId: 1 } });
  }

  function logout() {
    user.value = {} as User;
    localStorage.removeItem('user');
    router.push({ name: 'login' });
  }

  function autoLogout(expirationDate: number) {
    const currentDateInSeconds = Math.floor(Date.now() / 1000);
    const timeUntilExpiration = expirationDate - currentDateInSeconds;

    window.clearTimeout(logoutTimer.value);

    logoutTimer.value = window.setTimeout(() => {
      logout();
    }, timeUntilExpiration * 1000);
  }

  return {
    user,
    login,
    logout,
  };
});
