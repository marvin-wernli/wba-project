import TourView from '@/views/TourView.vue'
import TourenListeView from '@/views/TourenListeView.vue'
import { createRouter, createWebHistory } from 'vue-router'
//import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/touren',
      name: 'TourenListeView',
      component: TourenListeView
    },
    {
      path: '/', redirect:'/touren'
    },
    {
      path: '/tour/:tourid',
      name: 'TourView',
      component: TourView,
      props: true
    },
  ],
});

export default router
