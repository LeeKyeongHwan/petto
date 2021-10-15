import Vue from 'vue'
import VueRouter from 'vue-router'
import PettoHome from '../views/PettoHome.vue'
import signup from '../views/member/signup.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'PettoHome',
    component: PettoHome
  },
  {
    path: '/signup',
    name: 'signup',
    component: signup
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
