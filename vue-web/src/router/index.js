import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import BMHome from '../views/bm/Home.vue'
import User from '../views/user/User';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/BM/Home',
    name: 'BMHome',
    component: BMHome
  },
  {
    path: '/user/User',
    name: 'User',
    component: User
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
