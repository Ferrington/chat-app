<script setup lang="ts">
import { useSocketStore } from '@/stores/socket';
import { ref } from 'vue';

const { sendMessage } = useSocketStore();
const text = ref('');

//clears the message box.
function clear(){
  text.value = '';
};

// function boxHeight(){
//   const messageHeight = 
// }
</script>

<template>
  <!--have to figure out a way to vertically dynamically change the height of the message box....-->
  <div>
      <!-- changed input type text to text box. made it so that you can press enter to send message (not native on text box) as well as you
      can use shift enter to make a line break old code below also commented out-->
      <!-- <input type="text" v-model="text" id="messageBox" placeholder="Message Channel..."/> -->
      <form @submit.prevent="sendMessage(text)" @submit="clear" id="messageArea">
        <!--add variable to change channel to what ever or whoever youre messaging-->
      <textarea name="messageBox" id="messageBox" cols="30" rows="1" placeholder="Message Channel..." auto-grow
      v-model="text" v-on:keydown.enter.exact.prevent="{sendMessage(text);clear();}"></textarea>
      <!--find a way to incorparate the button? make it look cooler? -->
      <button type="submit"><img src="/Users/jamesrichardson/Desktop/Chat App/chat-app/client/src/assets/images/testicon.png" alt="Send Message" id="sendImg"></button>
    </form>
  </div>
</template>

<style scoped>

/* styled where the message and button make a flexbox so that they are always displayed on the same line.
 have to figure out the styling on the main page to get this functional at smaller sizes though*/

#messageBox {
  /* height: scrollHeight; */
  text-align: left;
  flex-grow: 1;
  overflow-wrap: normal;
  resize: none;
  max-height: 200px; 
  border-style: none;
  outline: none;
}

#messageArea{
  display: flex;
  flex-direction: row;
  height: 100%;
  border-top: 2px solid black;
}

button{
  border-style: none;
  background-color: white;
  display: inline-flex;
  align-items: flex-end;
  padding-bottom: 15px;
  padding-right: 15px;
}
#sendImg{
  width: 25px;
}

</style>
