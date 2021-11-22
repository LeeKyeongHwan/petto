import {

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
  FETCH_REPORT,

  FETCH_REPLY_LIST,

  FETCH_VOLUNTARYBOARD_LIST,
  FETCH_VOLUNTARYBOARD,

  FETCH_MY_LIKED_ANIMAL_LIST,

  FETCH_MY_BOARD_LIST,
  FETCH_VOL_COMMENT,

  FETCH_ADMIN_MEMBER_LIST,
  FETCH_ADMIN_MEMBER,

  FETCH_QNA_LIST,
  FETCH_QNA,
  FETCH_MY_QNA_LIST,

  FETCH_ADMIN_QNA_LIST,
  FETCH_ADMIN_QNA

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

  fetchUserInfo({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/member/getUserInfo/${payload}`)
      .then((res) => {
        commit(FETCH_USER_INFO, res.data)
      })
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
  
  fetchAnimalList ({ commit }) {
    return axios.get('http://localhost:8888/petto/animals/lists')  
            .then((res) => {
              commit(FETCH_ANIMAL_LIST, res.data) //
      })
  },

  fetchFIlteredAniList ({ commit }, payload) {
    return axios.post('http://localhost:8888/petto/animals/filterList', payload)  
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

  fetchReport({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/report/reportRead/${payload}`)
      .then((res) => {
        if(!res.data) {

          alert('이미 삭제되었거나 없는 공고입니다!')
          window.close()
        }
        else {
          //alert(JSON.stringify(res.data))
          commit(FETCH_REPORT, res.data)
        }
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

  fetchMyLikedAnimalList({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/animals/myLikedAnimals/${payload}`)
      .then(res => {
        commit(FETCH_MY_LIKED_ANIMAL_LIST, res.data);
      })
  },

  fetchMyBoardList({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/report/myBoardLists/${payload}`)
      .then(res => {
        commit(FETCH_MY_BOARD_LIST, res.data);
      })
  },

  fetchVolCommentList ({ commit }, volunteerNo) {
    return axios.get(`http://localhost:8888/petto/comments/lists/${volunteerNo}`)
      .then((res) => {
          commit(FETCH_VOL_COMMENT, res.data)
      })
  },
  
  fetchAdminMemberList ({ commit }) {
    return axios.get('http://localhost:8888/petto/admin/lists')
            .then((res) => {
                commit(FETCH_ADMIN_MEMBER_LIST, res.data)
            })
  },

  fetchAdminMember ({ commit }, memberNo) {
    return axios.get(`http://localhost:8888/petto/admin/${memberNo}`)
            .then((res) => {
                commit(FETCH_ADMIN_MEMBER, res.data)
            })
  },

  fetchReplyList ({ commit }, reportNo) {
    return axios.get(`http://localhost:8888/petto/report/getReplies/${reportNo}`)
            .then((res) => {
                commit(FETCH_REPLY_LIST, res.data)
            })
  },

  fetchQnAList ({ commit }) {
    return axios.get('http://localhost:8888/petto/qna/lists')
            .then((res) => {
                commit(FETCH_QNA_LIST, res.data)
            })
  },

  fetchQnA ({ commit }, qnaNo) {
      return axios.get(`http://localhost:8888/petto/qna/${qnaNo}`)
              .then((res) => {
                  commit(FETCH_QNA, res.data)
              })
  },

  fetchMyQnAList({ commit }, payload) {
    return axios.get(`http://localhost:8888/petto/qna/myQnaList/${payload}`)
      .then(res => {
        commit(FETCH_MY_QNA_LIST, res.data);
      })
  },
  //
  fetchAdminQnAList ({ commit }) {
    return axios.get('http://localhost:8888/petto/admin/qna/lists')
            .then((res) => {
                commit(FETCH_ADMIN_QNA_LIST, res.data)
            })
  },

  fetchAdminQnA ({ commit }, qnaNo) {
    return axios.get(`http://localhost:8888/petto/admin/qna/${qnaNo}`)
            .then((res) => {
                commit(FETCH_ADMIN_QNA, res.data)
            })
  },

  fetchMemberList ({ commit }) {
    return axios.get('http://localhost:8888/petto/member/memberlists')
            .then((res) => {
                commit(FETCH_MEMBER_LIST, res.data)
            })
  },

};


