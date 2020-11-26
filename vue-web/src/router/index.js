import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'
import Login from '../views/Login.vue'
import Home from '../views/bm/Home.vue'
import User from '../views/user/User'
import Role from '../views/role/Role'

Vue.use(VueRouter)

const routes = [
  {
    path: '/Login',
    name: 'Login',
    component: Login
  },
  //-------------------------
  {
    path: '/',
    redirect: '/Login',
    component: Layout,
    children: [
      {
        path: '/home',
        name: 'Home',
        component: Home
      },
      {
        path: '/User',
        name: 'User',
        component: User
      },
      {
        path: '/Role',
        name: 'Role',
        component: Role
      }
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router


