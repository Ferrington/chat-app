<script setup lang="ts">
    import { ref } from 'vue';

    function deleteMessage(){
        // this.$parent.message
        //emit event
    }

    function editMessage(){

    }

</script>

<template>
    <div id="contextMenu">
        <button class="contextItem"><font-awesome-icon :icon="['fas', 'pen-to-square']" /></button>
        <button class="contextItem"><font-awesome-icon :icon="['fas', 'trash-can']" /></button>
    </div>
</template>

<style scoped>
    #contextMenu{
        background-color: lightgray;
        display: flex;
        justify-content: flex-end;
        gap: 15px;
    }

    .contextItem {
        background-color: lightgrey;
        padding: 5px;
        border-style: none;
        height: 25px;
    }
    button:hover{
        cursor: pointer;
    } 

</style>