import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

import SignupPage from "../views/member/SignupPage.vue";
import FindIdAndPwPage from "../views/member/FindIdAndPwPage.vue";
import PettoHome from "../views/PettoHome.vue";
import MemberLoginPage from "../views/member/MemberLoginPage.vue";
import InfoModifyPage from "../views/member/InfoModifyPage.vue";

import FacilityListPage from "../views/facility/FacilityListPage.vue";
import FacilityReadPage from "../views/facility/FacilityReadPage.vue";

import AbandonedAnimal from "../views/abandon/AbandonedAnimal.vue";
import AnimalDetailPage from "../views/abandon/AnimalDetailPage.vue";

import KakaoMap from "../views/map/KakaoMap.vue";

import AnimalVoluntary from "../views/voluntary/AnimalVoluntary.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/signupPage",
    name: "SignupPage",
    component: SignupPage,
  },
  {
    path: "/findIdAndPwPage",
    name: "FindIdAndPwPage",
    component: FindIdAndPwPage,
  },
  {
    path: "/pettohome",
    name: "PettoHome",
    component: PettoHome,
  },
  {
    path: "/memberLoginPage",
    name: "MemberLoginPage",
    component: MemberLoginPage,
  },
  {
    path: "/infoModify/modify",
    name: "InfoModifyPage",
    component: InfoModifyPage,
  },
  {
    path: "/facility/list",
    name: "FacilityListPage",
    component: FacilityListPage,
  },
  {
    path: "/facility/read/:facilityNo", 
    name: "FacilityReadPage",
    components: {
      default: FacilityReadPage,
    },
    props: {
      default: true,
    },
  },
  {
    path: "/abandonedAnimal",
    name: "AbandonedAnimal",
    component: AbandonedAnimal,
  },
  {
    path: "/animalDetail/read/:id",
    name: "AnimalDetailPage",
    components: {
      default: AnimalDetailPage
    },
    props: {
      default: true
    }
  },
  {
    path: "/map",
    name: "KakaoMap",
    component: KakaoMap,
  },
  {
    path: "/animalVoluntary",
    name: "AnimalVoluntary",
    component: AnimalVoluntary,

  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;