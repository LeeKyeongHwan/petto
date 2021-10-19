import { FETCH_USER_INFO, FETCH_SESSION } from "./mutation-types";

export default {
  [FETCH_USER_INFO](state, payload) {
    state.userInfo = payload;
  },
  //세션
  [FETCH_SESSION](state) {
    state.session = this.$cookies.get();
  },
};
