import TourenListeView from '@/views/TourenListeView.vue'
import { createRouter, createWebHistory } from 'vue-router'
//import HomeView from '../views/HomeView.vue'

const routes = [
  { path: '/', redirect:'/touren'},
  { path: '/touren', component: TourenListeView},
]

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
    }
  ]
})

export default router
