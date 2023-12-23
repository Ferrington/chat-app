import authService from '@/services/AuthService';
import { userSchema, type User } from '@/types';
import axios from 'axios';
import { defineStore } from 'pinia';
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import { jwtDecode, type JwtPayload } from 'jwt-decode';

export const useAuthStore = defineStore('auth', () => {
  const user = ref<User>(JSON.parse(localStorage.getItem('user') ?? '{}'));
  const router = useRouter();

  // watch(user, (user) => {
  //   axios.defaults.headers.common.Authorization = `Bearer ${user.accessToken}`;

  //   if (isTokenExpired(user.accessToken)) {
  //     logout();
  //   }
  // });

  // Add a request interceptor
  axios.interceptors.request.use(
    (config) => {
      const authStore = useAuthStore();
      const accessToken = authStore.user.accessToken;

      // Check if the token is expired
      if (isTokenExpired(accessToken)) {
        // If expired, refresh the token or perform a logout
        // You can implement your logic for refreshing the token here
        // For simplicity, let's assume there is a refresh method in your auth store
        logout(); // Implement this method in your auth store
      }

      // Set the Authorization header with the updated token
      config.headers.Authorization = `Bearer ${authStore.user.accessToken}`;

      return config;
    },
    (error) => {
      return Promise.reject(error);
    },
  );

  // Add a response interceptor
  axios.interceptors.response.use(
    (response) => {
      return response;
    },
    (error) => {
      // Handle response errors here
      return Promise.reject(error);
    },
  );
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

  function isTokenExpired(accessToken: string) {
    try {
      const decodedToken = jwtDecode(accessToken) as JwtPayload;

      if (decodedToken && decodedToken.exp) {
        const currentDateInSeconds = Math.floor(Date.now() / 1000);
        return decodedToken.exp < currentDateInSeconds;
      }
    } catch (error) {
      return true;
    }
  }

  return {
    user,
    login,
    logout,
  };
});
