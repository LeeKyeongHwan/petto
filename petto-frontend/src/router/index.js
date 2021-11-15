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

//import AnimalVoluntary from "../views/voluntary/AnimalVoluntary.vue";

import ExceptionPage from "../views/ExceptionPage.vue";

import AnimalVoluntaryRegister from "../views/voluntary/AnimalVoluntaryRegister.vue"
import AnimalVoluntaryListPage from "../views/voluntary/AnimalVoluntaryListPage.vue"
import AnimalVoluntaryReadPage from "../views/voluntary/AnimalVoluntaryReadPage.vue"
import AnimalVoluntaryModifyPage from "../views/voluntary/AnimalVoluntaryModifyPage.vue"

import ReportBoardPage from "../views/report/ReportBoardPage.vue";
import ReportBoardListPage from "../views/report/ReportBoardListPage.vue";
import ReportWritePage from "../views/report/ReportWritePage.vue";

import MyLikedAnimals from "../views/member/MyLikedAnimals.vue";
import PasswordCheckPage from "../views/member/PasswordCheckPage.vue";
import MyProfile from "../views/member/MyProfile.vue";
import MyboardPage from "../views/member/MyboardPage.vue";

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
    path: "/exception/:facilityName",
    name: "ExceptionPage",
    components: {
      default: ExceptionPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/voluntaryBoard',
    name: 'AnimalVoluntaryListPage',
    components: {
      default: AnimalVoluntaryListPage
    }
  },
  {
    path: '/voluntaryBoard/create',
    name: 'AnimalVoluntaryRegister',
    components: {
      default: AnimalVoluntaryRegister
    }
  },
  {
    path: '/voluntaryBoard/:volunteerNo',
    name: 'AnimalVoluntaryReadPage',
    components: {
      default: AnimalVoluntaryReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/voluntaryBoard/:volunteerNo/edit',
    name: 'AnimalVoluntaryModifyPage',
    components: {
      default: AnimalVoluntaryModifyPage
    },
    props: {
      default: true
    }
  },
  {
    path: "/reportBoard",
    name: "ReportBoardPage",
    component: ReportBoardPage,
  },
  {
    path: "/reportBoard/list/keyword=:keyword",
    name: "ReportBoardListPage",
    components: {
      default: ReportBoardListPage
    },
    props: {
      default: true
    }
  },
  {
    path: "/reportBoard/write",
    name: "ReportWritePage",
    components: {
      default: ReportWritePage
    },
    props: {
      default: true
    }
  },
  {
    path: "/myLikedAnimals",
    name: "MyLikedAnimals",
    components: {
      default: MyLikedAnimals
    },
  },
  {
    path: "/passwordCheckPage",
    name: "PasswordCheckPage",
    component: PasswordCheckPage,
  },
  {
    path: "/myProfile",
    name: "MyProfile",
    component: MyProfile,
  },
  {
    path: "/myBoard",
    name: "MyboardPage",
    component: MyboardPage,
  },


];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;