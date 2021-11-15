import { 
  FETCH_USER_INFO, 
  FETCH_SESSION,
  FETCH_MEMBER_LIST,

  //보호소 리스트, 개별 정보
  FETCH_FACILITY_LIST,
  FETCH_FACILITY_INFO,

  FETCH_ANIMAL_LIST,
  FETCH_ANIMAL_INFO,
  FETCH_ORDER_ANIMAL_LIST,

  FETCH_LIKED_ANIMAL_LIST,

  FETCH_REPORT_LIST,

  FETCH_VOLUNTARYBOARD_LIST,
  FETCH_VOLUNTARYBOARD,

  FETCH_MY_LIKED_ANIMAL_LIST,
  FETCH_MY_BOARD_LIST,

  FETCH_VOL_COMMENT


} from "./mutation-types";

import axios from 'axios'
// import router from '../router'
import cookies from 'vue-cookies'

export default {
  async TodayPopUp(){
      if(cookies.isKey("TodayPopUpClose") == false){
        cookies.set("TodayPopUpClose", "todayClose","1d")
        console.log(cookies.get("TodayPopUpClose"))
      }
    },

  fetchFacilityList({ commit }) {
    return axios.get('http://localhost:8888/petto/facility/getFacilityList')
      .then(res => {
        commit(FETCH_FACILITY_LIST, res.data);
      });
  },

  fetchFacilityInfo({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/facility/getFacilityInfo/${payload}`)
      .then(res => {
        commit(FETCH_FACILITY_INFO, res.data);
      });
  },
  
  // Session
  fetchSession({ commit }) {
    commit(FETCH_SESSION);
  },
  
  fetchUserInfo({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/member/getUserInfo/${payload}`)
      .then((res) => {
        commit(FETCH_USER_INFO, res.data)
      })
  },

  fetchAnimalList ({ commit }) {
    return axios.get('http://localhost:8888/petto/animals/lists')
            .then((res) => {
                commit(FETCH_ANIMAL_LIST, res.data) //
      })
  },

  fetchLikedAnimalList ({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/member/likedAnimalList/${payload}`)
            .then((res) => {
                //alert(JSON.stringify(res.data))
                commit(FETCH_LIKED_ANIMAL_LIST, res.data)
      })
  },

  fetchAnimalInfo({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/animals/getAnimalsInfo/${payload}`)
      .then(res => {
        commit(FETCH_ANIMAL_INFO, res.data);
      })
  },


  fetchOlderAnimalList({ commit }) {
    return axios.get('http://localhost:8888/petto/animals/orderLists')
      .then(res => {
        commit(FETCH_ORDER_ANIMAL_LIST, res.data)
      })
  },

  fetchReportList({ commit }) {
    return axios.get('http://localhost:8888/petto/report/reportList')
      .then((res) => {
        commit(FETCH_REPORT_LIST, res.data)
      })
  },

    
  fetchVoluntaryBoardList ({ commit }) {
    return axios.get('http://localhost:8888/petto/voluntaryBoard/lists')
            .then((res) => {
                commit(FETCH_VOLUNTARYBOARD_LIST, res.data)
            })
  },
  
  fetchVoluntaryBoard ({ commit }, volunteerNo) {
      return axios.get(`http://localhost:8888/petto/voluntaryBoard/${volunteerNo}`)
              .then((res) => {
                  commit(FETCH_VOLUNTARYBOARD, res.data)
              })
   },
  fetchVolCommentList ({ commit }, volunteerNo) {
    return axios.get(`http://localhost:8888/petto/comments/lists/${volunteerNo}`)
            .then((res) => {
                commit(FETCH_VOL_COMMENT, res.data)
                console.log(res.data)
            })
  },
<<<<<<<<< Temporary merge branch 1

};
=========
  
      fetchMemberList ({ commit }) {
        return axios.get('http://localhost:8888/petto/member/memberlists')
                .then((res) => {
                    commit(FETCH_MEMBER_LIST, res.data)
                })
      }

  };
>>>>>>>>> Temporary merge branch 2
  