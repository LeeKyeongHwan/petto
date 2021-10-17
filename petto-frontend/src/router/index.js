
import SignupPage from '../views/member/SignupPage.vue'
import FindIdAndPwPage from '../views/member/FindIdAndPwPage.vue'

import Vue from "vue";
import VueRouter from "vue-router";
import MemberLoginPage from "@/views/member/MemberLoginPage.vue";
import PettoHome from '../views/PettoHome.vue'
<<<<<<< HEAD

=======
import Home from '../views/Home.vue'
>>>>>>> f9ccf9b1e400e744ce2c3d77034cd40f0fecfb17


Vue.use(VueRouter);


const routes = [
<<<<<<< HEAD
  
=======
  {
    path: '/',
    name: 'Home',
    component: Home
  },
>>>>>>> f9ccf9b1e400e744ce2c3d77034cd40f0fecfb17
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
<<<<<<< HEAD
    path: '/',
=======
    path: '/pettoHome',
>>>>>>> f9ccf9b1e400e744ce2c3d77034cd40f0fecfb17
    name: 'PettoHome',
    component: PettoHome
  },
  {
    path: "/member/login",
    name: "MemberLoginPage",
    components: {
      default: MemberLoginPage,
    }
  }
]


const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
