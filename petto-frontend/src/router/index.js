import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import SignupPage from '../views/member/SignupPage.vue'
import FindIdAndPwPage from '../views/member/FindIdAndPwPage.vue'
import PettoHome from '../views/PettoHome.vue'
import MemberLoginPage from '../views/member/MemberLoginPage.vue'
import InfoModifyPage from '../views/member/InfoModifyPage.vue'

import FacilityListPage from '../views/facility/FacilityListPage.vue'
import FacilityReadPage from '../views/facility/FacilityReadPage.vue'

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
    path: '/pettoHome',
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
    path: '/facility/read/:facilityNo', // *뒤로가기가 안됨
    name: 'FacilityReadPage',
    components: {
      default: FacilityReadPage
    },
    props: {
      default: true
    }
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
