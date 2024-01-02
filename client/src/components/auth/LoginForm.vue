<script setup lang="ts">
import { storeToRefs } from 'pinia';
import { useAuthStore } from '@/stores/auth';
import type { UserDTO } from '@/types';

import { ref, computed } from 'vue';

const { login, clearAuthError } = useAuthStore();
const { authError } = storeToRefs(useAuthStore());

const user = ref<UserDTO>({
  username: '',
  password: '',
});
const loginError = ref('');
const displayError = computed(() => authError.value || loginError.value);

const showPassword = ref(false);

function submit() {
  if (!validateFormData()) return;

  login(user.value);
}

function validateFormData() {
  let validated = true;
  if (user.value.username.length === 0) {
    loginError.value = 'Please enter your username.';
  } else if (user.value.password.length === 0) {
    loginError.value = 'Please enter your password.';
  }
  return validated;
}

function clearError() {
  loginError.value = '';
  clearAuthError();
}
</script>

<template>
  <div id="container">
    <div id="login">
      <form v-on:submit.prevent="submit">
        <h1>Please Sign In</h1>
        <div id="fields">
          <div class="input-container">
            <label for="username">Username</label>
            <input
              type="text"
              id="username"
              @input="clearError"
              v-model="user.username"
              required
              autofocus
            />
          </div>
          <div class="input-container">
            <label for="password">Password</label>&nbsp;
            <input
              :type="showPassword ? 'text' : 'password'"
              id="password"
              @input="clearError"
              v-model="user.password"
              required
            />
          </div>
          <input type="checkbox" id="checkbox" v-model="showPassword" />
          <label for="checkbox">Show Password</label>
        </div>
        <input type="submit" class="submit" />
        <p v-if="displayError.length > 0" class="login-error">{{ displayError }}</p>
      </form>
    </div>
  </div>
</template>

<style scoped>
* {
  font-family: sans-serif;
}

#container {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f5f5f5;
  height: 100vh;
}

#login {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 20px 40px 60px;
  margin-bottom: -1px;
  border: 1px solid black;
  border-radius: 10px;
}

h1 {
  text-align: center;
}

input[type='text'],
input[type='password'] {
  float: left;
  width: calc(100% - 20px);
  height: 35px;
  padding: 0px 10px;
  border: 1px solid rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  margin-top: 2.5px;
  outline: none;
  margin-bottom: 10px;
}

.submit {
  width: 100%;
  height: 35px;
  background: #00adf3;
  color: white;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  transition: all 0.5s;
  margin-top: 20px;
}

button:hover {
  background: #0799d4;
}

.login-error {
  color: rgb(194, 20, 20);
}
</style>
