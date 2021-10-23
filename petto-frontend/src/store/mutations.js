import { 
  FETCH_USER_INFO, 
  FETCH_SESSION,
  FETCH_FACILITY_LIST,
  FETCH_FACILITY_INFO

} from "./mutation-types";

export default {
  [FETCH_USER_INFO](state, payload) {
    state.userInfo = payload;
  },
  //세션
  [FETCH_SESSION](state) {
    state.session = this.$cookies.get();
  },

  [FETCH_FACILITY_LIST](state, payload) {
    state.facilityList = payload
  },

  [FETCH_FACILITY_INFO](state, payload) {
    state.facilityInfo = payload
  }
};

