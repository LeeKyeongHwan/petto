import { 
  FETCH_USER_INFO, 
  FETCH_SESSION,
  FETCH_FACILITY_LIST,
  FETCH_ANIMAL_LIST

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

  [FETCH_ANIMAL_LIST] (state, animals) {
    state.animals = animals;
  }
};

