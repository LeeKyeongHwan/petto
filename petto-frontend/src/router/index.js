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
import ReportReadPage from "../views/report/ReportReadPage.vue";
import ReportModifyPage from "../views/report/ReportModifyPage.vue";

import MyLikedAnimals from "../views/member/MyLikedAnimals.vue";
import PasswordCheckPage from "../views/member/PasswordCheckPage.vue";
import PasswordCheckWithdrawalPage from "../views/member/PasswordCheckWithdrawalPage.vue";
import MyProfile from "../views/member/MyProfile.vue";
import MyboardPage from "../views/member/MyboardPage.vue";

import AdminMemberListPage from '@/views/admin/AdminMemberListPage.vue'
import AdminMemberReadPage from '@/views/admin/AdminMemberReadPage.vue'

import QnaListPage from "../views/admin/qna/QnaListPage.vue";

import QnaModifyPage from "../views/member/qna/QnaModifyPage.vue";
import QnaReadPage from "../views/member/qna/QnaReadPage.vue";
import QnaWritePage from "../views/member/qna/QnaWritePage.vue";
import MyQna from "../views/member/qna/MyQna.vue";


import AdminQnaReadPage from "../views/admin/qna/AdminQnaReadPage.vue";


import AboutPage from '@/views/about/AboutPage.vue';


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
    path: "/abandonedAnimal/list/page=:pageNum&place=:place&kind=:kind",
    name: "AbandonedAnimal",
    components: {
      default: AbandonedAnimal
    },
    props: {
      default: true
    }
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

  // {
  //   path: "/animalVoluntary",
  //   name: "AnimalVoluntary",
  //   component: AnimalVoluntary,
  // },

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
    path: "/voluntaryBoard/list/page=:pageNum",
    name: "AnimalVoluntaryListPage",
    components: {
      default: AnimalVoluntaryListPage
    },
    props: {
      default: true
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
    path: "/reportBoard/read/:reportNo",
    name: "ReportReadPage",
    components: {
      default: ReportReadPage
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
    path: "/passwordCheck",
    name: "PasswordCheckWithdrawalPage",
    component: PasswordCheckWithdrawalPage,
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
  {
    path: '/admin',
    name: 'AdminMemberListPage',
    components: {
      default: AdminMemberListPage
    }
  },
  {
    path: '/admin/:memberNo',
    name: 'AdminMemberReadPage',
    components: {
      default: AdminMemberReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: "/reportBoard/modify/:reportNo",
    name: "ReportModifyPage",
    components: {
      default: ReportModifyPage
    },
    props: {
      default: true
    }
  },
  {

    path: "/aboutPage",
    name: "AboutPage",
    component: AboutPage,
  },
  {

    path: "/qnalist/:qnaNo/edit",
    name: "QnaModifyPage",
    components: {
      default: QnaModifyPage,
    },
    props: {
      default: true,
    },
  },
  {
    path: "/qna/:qnaNo",
    name: "QnaReadPage",
    components: {
      default: QnaReadPage,
    },
    props: {
      default: true,
    },
  },
  {
    path: "/qna/Write",
    name: "QnaWritePage",
    component: QnaWritePage,
  },
  {
    path: "/myqna",
    name: "MyQna",
    component: MyQna,
  },
  // 관리자 qna
  {
    path: "/qnalist",
    name: "QnaListPage",
    component: QnaListPage,
  },
  {
    path: '/admin/qna/:qnaNo',
    name: 'AdminQnaReadPage',
    components: {
      default: AdminQnaReadPage
    },
    props: {
      default: true
    }
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;