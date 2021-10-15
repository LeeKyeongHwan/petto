import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import MemberLoginPage from "@/views/member/MemberLoginPage.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/member/login",
    name: "MemberLoginPage",
    components: {
      default: MemberLoginPage,
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
