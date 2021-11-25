import { 
  FETCH_USER_INFO,
  FETCH_SESSION,
  
  FETCH_FACILITY_LIST,
  FETCH_FACILITY_INFO,

  FETCH_ANIMAL_LIST,
  FETCH_ANIMAL_INFO,

  FETCH_LIKED_ANIMAL_LIST,
  FETCH_NUM_OF_ANIMALS,

  FETCH_ORDER_ANIMAL_LIST,

  FETCH_REPORT_LIST,
  FETCH_REPORT,

  FETCH_REPLY_LIST,

  FETCH_VOLUNTARYBOARD_LIST,
  FETCH_VOLUNTARYBOARD,

  FETCH_MY_LIKED_ANIMAL_LIST,

  FETCH_MY_BOARD_LIST,
  FETCH_MEMBER_LIST,

  FETCH_VOL_COMMENT,
  FETCH_ADMIN_MEMBER_LIST,
  FETCH_ADMIN_MEMBER,

  FETCH_QNA_LIST,
  FETCH_QNA,
  FETCH_MY_QNA_LIST,

  FETCH_ADMIN_QNA_LIST,
  FETCH_ADMIN_QNA,

  FETCH_ALARM_LIST

} from "./mutation-types";

export default {
  //세션
  [FETCH_SESSION] (state) {
    state.session = this.$cookies.get();
  },

  [FETCH_USER_INFO] (state, payload) {
    state.userInfo = payload
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

  [FETCH_NUM_OF_ANIMALS] (state, payload) {
    state.numOfAnimals = payload;
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

  [FETCH_REPORT] (state, payload) {
    state.report = payload
  },

  [FETCH_MY_LIKED_ANIMAL_LIST] (state, payload) {
    state.myLikedAnimals = payload;
  },
  [FETCH_MY_BOARD_LIST] (state, payload) {
    state.myBoardList = payload;
  },


  [FETCH_REPORT] (state, payload) {
    state.report = payload
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
  [FETCH_MEMBER_LIST] (state, members) {
    state.members = members;
  },
  [FETCH_ADMIN_MEMBER_LIST] (state, adminMembers) {
    state.adminMembers = adminMembers;
  },
  [FETCH_ADMIN_MEMBER] (state, adminMember) {
    state.adminMember = adminMember
  },

  [FETCH_REPLY_LIST] (state, payload) {
    state.replyList = payload
  },
  [FETCH_QNA_LIST] (state, qnaboards) {
    state.qnaboards = qnaboards;
  },
  //
  [FETCH_QNA] (state, qnaboard) {
    state.qnaboard = qnaboard
  },

  [FETCH_MY_QNA_LIST] (state, myQnaList) {
    state.myQnaList = myQnaList
  },
  //
  [FETCH_ADMIN_QNA_LIST] (state, adminQnAList) {
    state.adminQnAList = adminQnAList;
  },
  [FETCH_ADMIN_QNA] (state, adminQnA) {
    state.adminQnA = adminQnA
  },

  [FETCH_ALARM_LIST] (state, payload) {
    state.updateAlarmList = payload
  }

};
