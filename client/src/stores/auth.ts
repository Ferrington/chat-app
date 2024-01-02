import authService from '@/services/AuthService';
import { userSchema, type User, type UserDTO } from '@/types';
import { objectsHaveSameKeys } from '@/utils/objectsHaveSameKeys';
import axios, { AxiosError, type AxiosResponse } from 'axios';
import { jwtDecode } from 'jwt-decode';
import { defineStore } from 'pinia';
import { ref, watch } from 'vue';
import { useRouter } from 'vue-router';

export const useAuthStore = defineStore('auth', () => {
  const emptyUser: User = {
    id: 0,
    username: '',
    accessToken: '',
    tokenType: '',
    roles: [],
  };
  const user = ref<User>(emptyUser);
  loadUserFromLocalStorage();
  const router = useRouter();
  const loginFailed = ref(false);
  const logoutTimer = ref<number>(0);
  const authError = ref('');

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

  async function login(userDTO: UserDTO) {
    try {
      const response = await authService.login(userDTO);

      if (response.status === 200) {
        successfulLoginActions(response);
      }
    } catch (error) {
      if (!(error instanceof Error)) return;
      handleLoginError(error);
    }
  }

  function successfulLoginActions(response: AxiosResponse) {
    const newUser: User = userSchema.parse(response.data);
    user.value = newUser;

    router.push({ name: 'channel', params: { channelId: 1 } });
  }

  function handleLoginError(error: Error) {
    loginFailed.value = true;
    if (!axios.isAxiosError(error)) {
      console.error('Non-axios error:', error);
      return;
    }

    if (error.response?.status === 400) {
      authError.value = 'Username and Password cannot be blank.';
    } else if (error.response?.status === 401) {
      authError.value = 'Your login attempt failed. Please try again.';
    } else {
      authError.value = 'Something went wrong on our end. Try again later.';
    }
  }

  function clearAuthError() {
    authError.value = '';
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

  async function loadUserFromLocalStorage() {
    const localUser = localStorage.getItem('user');
    if (localUser == null) return;

    try {
      const parsedUser = JSON.parse(localUser);
      if (objectsHaveSameKeys(parsedUser, emptyUser)) {
        user.value = parsedUser;
        return;
      }
      logout();
    } catch (error) {
      logout();
    }
  }

  return {
    user,
    login,
    loginFailed,
    logout,
    authError,
    clearAuthError,
  };
});
