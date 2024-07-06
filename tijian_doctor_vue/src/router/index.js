import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Logon from '../views/Logon.vue'
import OC from '../views/OrdersContent.vue'
import OL from '../views/OrdersList.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Logon
    },
    {
      path: '/ordersList',
      name: 'ordersList',
      component: OL
    },
    {
      path: '/ordersContent',
      name: 'ordersContent',
      component: OC
    }
  ]
})

export default router
