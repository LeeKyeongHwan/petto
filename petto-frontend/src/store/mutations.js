import { 
  FETCH_USER_INFO, 
  FETCH_SESSION,
  
  FETCH_FACILITY_LIST,
  FETCH_FACILITY_INFO,

  FETCH_ANIMAL_LIST,
  FETCH_ANIMAL_INFO,

  FETCH_LIKED_ANIMAL_LIST,

  FETCH_ORDER_ANIMAL_LIST,

  FETCH_REPORT_LIST,

  FETCH_VOLUNTARYBOARD_LIST,
  FETCH_VOLUNTARYBOARD,

  FETCH_MY_LIKED_ANIMAL_LIST,
  FETCH_MY_BOARD_LIST  

  FETCH_VOL_COMMENT

} from "./mutation-types";

export default {
  [FETCH_USER_INFO] (state, payload) {
    state.userInfo = payload;
  },
  //세션
  [FETCH_SESSION] (state) {
    state.session = this.$cookies.get();
  },

  [FETCH_FACILITY_LIST] (state, payload) {
    state.facilityList = payload
  },

  [FETCH_FACILITY_INFO] (state, payload) {
    state.facilityInfo = payload
  },
  
  [FETCH_ANIMAL_LIST] (state, animals) {
    //alert(JSON.stringify(animals[1]))
    state.animals = animals;
  },
  
  [FETCH_ANIMAL_INFO] (state, animals) {
    state.animalsInfo = animals;
  },

  [FETCH_LIKED_ANIMAL_LIST] (state, payload) {
    state.likedAnimalList = payload;
  },

  [FETCH_ORDER_ANIMAL_LIST](state, olderList) {
    state.olderList = olderList;
  },

  [FETCH_VOLUNTARYBOARD_LIST] (state, voluntaryboards) {
    state.voluntaryboards = voluntaryboards;
  },
  [FETCH_VOLUNTARYBOARD] (state, voluntaryboard) {
    state.voluntaryboard = voluntaryboard
  },

  [FETCH_REPORT_LIST] (state, payload) {
    state.reportList = payload
  },

  [FETCH_MY_LIKED_ANIMAL_LIST] (state, payload) {
    state.myLikedAnimals = payload;
  },
  [FETCH_MY_BOARD_LIST] (state, payload) {
    state.myBoardList = payload;
  },

  [FETCH_VOL_COMMENT] (state, payload) {
    state.volComments = payload;
  },

};
