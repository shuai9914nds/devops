import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../views/Layout.vue'
import Login from '../views/Login.vue'
import Home from '../views/bm/Home.vue'
import User from '../views/user/User'
import Role from '../views/perm/Role'
import UserPerm from '../views/perm/UserPerm'
import Monitor from '../views/monitor/Monitor'


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
      },
      {
        path: '/Monitor',
        name: 'Monitor',
        component: Monitor
      }
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  
})

router.beforeEach((to, from, next) => {
  const token = window.localStorage.getItem('token')
  if (to.path != '/Login' && !token) {
    next({path: '/Login',replace: true})
  } else {
    next()
  }
})

export default router


