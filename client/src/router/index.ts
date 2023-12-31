import { useAuthStore } from '@/stores/auth';
import ClientView from '@/views/ClientView.vue';
import LoginView from '@/views/LoginView.vue';
import ChannelView from '@/views/client/ChannelView.vue';
import { storeToRefs } from 'pinia';
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: { name: 'login' },
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: '/client',
      component: ClientView,
      meta: {
        requiresAuth: true,
      },
      children: [
        {
          path: ':channelId',
          name: 'channel',
          component: ChannelView,
        },
      ],
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
      meta: {
        requiresAuth: false,
      },
    },
  ],
});

router.beforeEach((to) => {
  const authStore = useAuthStore();
  const { user } = storeToRefs(authStore);

  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);

  if (requiresAuth && !('accessToken' in user.value)) {
    return { name: 'login' };
  }
});

export default router;
