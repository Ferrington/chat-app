import channelService from '@/services/ChannelService';
import { isNumeric } from '@/utils/isNumeric';
import ChannelView from '@/views/ChannelView.vue';
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      beforeEnter: async () => {
        const response = await channelService.getFirst();
        const channelId = response.data.id;

        return {
          name: 'channel',
          params: {
            channelId,
          },
        };
      },
      component: ChannelView,
    },
    {
      path: '/:channelId',
      name: 'channel',
      beforeEnter: async (to) => {
        if (!isNumeric(to.params.channelId)) {
          return { name: 'home', params: {} };
        }
      },
      component: ChannelView,
    },
  ],
});

export default router;
