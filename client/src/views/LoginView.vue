<script setup lang="ts">
import { useAuthStore } from '@/stores/auth';
import type { UserDTO } from '@/types';

import { ref } from 'vue';

const { login } = useAuthStore();

const user = ref<UserDTO>({
  username: '',
  password: '',
});
</script>

<template>
  <div id="container">
    <div id="login">
      <form v-on:submit.prevent="login(user)">
        <h1>Please Sign In</h1>
        <div id="fields">
          <div class="input-container">
            <label>Username</label>
            <input
              type="text"
              id="username"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            />
          </div>
          <div class="input-container">
            <label>Password</label>
            <input
              type="password"
              id="password"
              placeholder="Password"
              v-model="user.password"
              required
            />
          </div>
        </div>
        <button type="button">Login</button>
      </form>
    </div>
  </div>
</template>

<style scoped>
#container {
  text-align: center;
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

label {
  text-align: left;
  font-size: 14px;
  text-transform: none;
  margin-bottom: 2px;
  margin-top: 10px;
}

input {
  float: left;
  width: calc(100% - 20px);
  height: 35px;
  padding: 0px 10px;
  border: 1px solid rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  margin-top: 2.5px;
  outline: none;
}

button {
  width: 100%;
  height: 35px;
  background: #00adf3;
  color: white;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  transition: all 0.5s;
}

button:hover {
  background: #0799d4;
}
</style>
