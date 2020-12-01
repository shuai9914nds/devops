import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'
import Login from '../views/Login.vue'
import Home from '../views/bm/Home.vue'
import User from '../views/user/User'
import Role from '../views/perm/Role'
import UserPerm from '../views/perm/UserPerm'

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
        path: '/Home',
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
      },
      {
        path: '/UserPerm',
        name: 'UserPerm',
        component: UserPerm
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


