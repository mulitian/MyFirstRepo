import { createRouter, createWebHistory } from 'vue-router'
import MyLogin from '../views/MyLogin.vue'
import ForgetPw from '../views/ForgetPw.vue'
import OC from '../views/OrdersContent.vue'
import OL from '../views/OrdersList.vue'
import ChartView from '../views/ChartsView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: MyLogin
    },
    // {
    //   path: '/forgetpw',
    //   name: 'forgetpw',
    //   component: ForgetPw
    // },
    {
      path: '/ordersList',
      name: 'ordersList',
      component: OL
    },
    {
      path: '/ordersContent',
      name: 'ordersContent',
      component: OC
    },
    {
      path: '/charts',
      name: 'Charts',
      component: ChartView
    }
  ]
})

export default router
