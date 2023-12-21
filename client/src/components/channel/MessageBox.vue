<script setup lang="ts">
import { useSocketStore } from '@/stores/socket';
import { ref } from 'vue';

const { sendMessage } = useSocketStore();
const text = ref('');
const textArea = ref<HTMLTextAreaElement>();

function submit() {
  sendMessage(text.value);
  clear();
  textArea.value?.focus();
}

function clear() {
  text.value = '';
}

function resizeTextArea() {
  if (textArea.value == null) return;
  const styles = window.getComputedStyle(textArea.value);
  const paddingBlock = Number(styles.paddingBlock.replace('px', '')) * 2;
  textArea.value.style.height = styles.minHeight;
  textArea.value.style.height = Math.min(textArea.value.scrollHeight - paddingBlock, 200) + 'px';
}
</script>

<template>
  <div class="message-div">
    <form @submit.prevent="submit" class="message-form">
      <!--add variable to change channel to what ever or whoever youre messaging-->
      <div class="message-box-wrapper">
        <textarea
          ref="textArea"
          class="message-box"
          placeholder="Message Channel..."
          v-model="text"
          @keydown.enter.exact.prevent="submit"
          @input="resizeTextArea"
        ></textarea>
      </div>
      <button class="submit-button" type="submit" :disabled="text.length === 0">
        <FontAwesomeIcon :icon="['fas', 'paper-plane']" />
      </button>
    </form>
  </div>
</template>

<style scoped>
.message-div {
  background-color: white;
  padding-left: 10px;
  padding-right: 10px;
  padding-bottom: 10px;
}

.message-form {
  display: flex;
  flex-direction: row;
  border: 1px solid lightgray;
  background-color: lightgray;
  border-radius: 5px;
}

.message-box-wrapper {
  display: flex;
  flex-grow: 1;
}

.message-box {
  /* height: scrollHeight; */
  min-height: 40px;
  overflow-wrap: normal;
  resize: none;
  border-style: none;
  outline: none;
  background-color: inherit;
  border-right: none;
  border-radius: 5px;
  padding-block: 10px;
  padding-left: 15px;
  padding-right: 50px;
  overflow: hidden;
  align-self: flex-end;
}

.submit-button {
  position: sticky;
  right: 25px;
  background-color: inherit;
  border: none;
  display: inline-flex;
  align-items: flex-end;
  padding-bottom: 15px;
}

.submit-button:hover {
  cursor: pointer;
}
</style>
