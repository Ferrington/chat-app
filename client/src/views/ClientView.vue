<script setup lang="ts">
import SideNav from '@/components/sideNav/SideNav.vue';
import { useSocketStore } from '@/stores/socket';
import { onUnmounted } from 'vue';

const { connect, disconnect } = useSocketStore();

connect();

onUnmounted(() => {
  disconnect();
});
</script>

<template>
  <div class="container">
    <aside>
      <Suspense>
        <SideNav />
        <template #fallback> Loading... </template>
      </Suspense>
    </aside>
    <main>
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
.container {
  display: grid;
  position: absolute;
  inset: 0;
  grid-template-columns: 250px 1fr;
}

aside {
  border-right: 1px solid #9c9c9c;
}
</style>
