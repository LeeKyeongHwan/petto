
import SignupPage from '../views/member/SignupPage.vue'
import FindIdAndPwPage from '../views/member/FindIdAndPwPage.vue'


import Vue from "vue";
import VueRouter from "vue-router";
import MemberLoginPage from "@/views/member/MemberLoginPage.vue";
import PettoHome from '../views/PettoHome.vue'
import Home from '../views/Home.vue'


Vue.use(VueRouter);


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
    path: "/member/login",
    name: "MemberLoginPage",
    components: {
      default: MemberLoginPage,
    },
  },

]


const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
