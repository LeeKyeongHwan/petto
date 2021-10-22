import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import SignupPage from '../views/member/SignupPage.vue'
import FindIdAndPwPage from '../views/member/FindIdAndPwPage.vue'
import PettoHome from '../views/PettoHome.vue'
import MemberLoginPage from '../views/member/MemberLoginPage.vue'
import InfoModifyPage from '../views/member/InfoModifyPage.vue'

import FacilityListPage from '../views/facility/FacilityListPage.vue'
import AbandonedAnimal from '../views/abandon/AbandonedAnimal.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/signupPage',
    name: 'SignupPage',
    component: SignupPage
  },
  {
    path: '/findIdAndPwPage',
    name: 'FindIdAndPwPage',
    component: FindIdAndPwPage
  },
  {
    path: '/pettohome',
    name: 'PettoHome',
    component: PettoHome
  },
  {
    path: '/memberLoginPage',
    name: 'MemberLoginPage',
    component: MemberLoginPage
  },
  {
    path: '/infoModify/modify',
    name: 'InfoModifyPage',
    component: InfoModifyPage
  },
  {
    path: '/facility/list',
    name: 'FacilityListPage',
    component: FacilityListPage
  },
  {
    path: '/abandonedAnimal',
    name: 'AbandonedAnimal',
    component: AbandonedAnimal
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
