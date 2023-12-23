import authService from '@/services/AuthService';
import { userSchema, type User } from '@/types';
import axios from 'axios';
import { defineStore } from 'pinia';
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { jwtDecode } from 'jwt-decode';

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User>(JSON.parse(localStorage.getItem('user') ?? '{}'));
  const router = useRouter();

  watch(user, (user) => {
    axios.defaults.headers.common.Authorization = `Bearer ${user.accessToken}`;

    try {
      const decodedToken = jwtDecode(user.accessToken);
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
    alert('You have been logged out');
    user.value = {} as User;
    localStorage.removeItem('user');
    router.push({ name: 'login' });
  }

  function autoLogout(expirationDate: number) {
    const currentDateInSeconds = Math.floor(Date.now() / 1000);
    const timeUntilExpiration = expirationDate - currentDateInSeconds;

    if (timeUntilExpiration <= 0) {
      logout();
    } else {
      setTimeout(() => {
        logout();
      }, 5000);
    }
  }

  return {
    user,
    login,
    logout,
  };
});
