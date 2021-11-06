<template>
  <div>
    <div>
      <v-layout class="search-btn">
            <v-dialog v-model="searchDialog" persistent max-width="400px">
                <template v-slot:activator="{ on }">
                    <v-btn icon x-large v-on="on"><v-icon>search</v-icon></v-btn>        
                </template>
                <v-card>
                  <v-toolbar color="white darken-3" flat height="50">
                              <v-btn icon x-large @click="cancle()" style="positoin:absolute; left:23em;">
                                  <v-icon>clear</v-icon>
                              </v-btn>
                  </v-toolbar>
                  <v-card-text >
                      <v-select
                        v-model="selectPlace"
                        :items="areas"
                        label="모든 지역"
                        multiple
                        attach
                        chips
                        style="width: 300px; display: inline-block; margin-right: 40px;"
                        persistent-hint>
                      </v-select>
                      <v-select
                          v-model="selectKinds"
                          :items="kinds"
                          label="모든 품종"
                          multiple 
                          attach
                          chips
                          style="width: 300px; display: inline-block; margin-right: 40px;"
                          persistent-hint>
                      </v-select>
                  </v-card-text>
                  <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="orange lighten-3 white--text" @click="selectSearch()">
                          검색
                      </v-btn>
                  </v-card-actions>
                </v-card>
            </v-dialog>
          </v-layout>
    </div>
    <v-container style="width:100%;">
    <v-row>
      <v-card v-for="animal in paginatedData" :key="animal.notice_no" class="list-card"> 
        <figure class="snip1477">
            <img :src="animal.image" width="350" height="300"/>
            <div class="title">
                <div>
                <h4 @click="toDetailPage(animal.id)">상세보기 <v-icon color="white">keyboard_arrow_right</v-icon></h4>
                </div>
            </div>
            <figcaption>
                <p>
                {{animal.notice_no}}<br/>
                공고기간 : {{animal.notice_sd}} - {{animal.notice_ed}}<br/>
                상태 : {{ animal.state }}<br/>
                품종 : {{animal.kind}}<br/>
                발견장소 : {{animal.happenplace}}</p>
            </figcaption>

        </figure>

        <div style="float: right; margin-right: 30px; margin-bottom: 10px;">

          <p class="normalText" style="display: inline-block; font-size: 15px;">{{ animal.numberOfLiked }}</p>
          &ensp;
          <p class="normalText" style="display: inline-block; font-size: 12px; color: black;">명이 이 동물을 찜했어요!</p>

          <v-tooltip bottom>

            <template v-slot:activator="{ on, attrs }">
          
            <v-btn text v-on="on" v-bind="attrs" @click="toDetailPage(animal.id)" style="margin-bottom: 5px; margin-right: 10px;">

              <v-icon color="#42b8d4">
                assessment
              </v-icon>

            </v-btn>
            </template>

            <span>상세 정보 보기</span>

          </v-tooltip>
          
          <v-tooltip bottom>

            <template v-slot:activator="{ on, attrs }">
          
            <font-awesome-icon v-show="chkLikedOrNot(animal.notice_no)" :icon="['fas','heart']" size="lg" :style="{ color: '#42b8d4' }" v-on="on" v-bind="attrs"
            @click="deleteLikedAnimal(animal.notice_no)"/>

            <font-awesome-icon v-show="!chkLikedOrNot(animal.notice_no)" :icon="['far','heart']" size="lg" :style="{ color: '#42b8d4' }" v-on="on" v-bind="attrs"
            @click="addLikedAnimal(animal.notice_no)"/>

            </template>

            <span v-show="chkLikedOrNot(animal.notice_no)">찜해제</span>

            <span v-show="!chkLikedOrNot(animal.notice_no)">찜하기</span>

          </v-tooltip>

        </div>

      </v-card>
    </v-row>
    </v-container>
    <v-container>
      <div class="btn-cover">
        <v-btn :disabled="pageNum === 0" @click="prevPage" icon text>
          <v-icon>chevron_left</v-icon>
        </v-btn>
        <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <v-btn  :disabled="pageNum >= pageCount - 1" @click="nextPage" icon text>
          <v-icon>chevron_right</v-icon>
        </v-btn >
      </div>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'
import { FETCH_ANIMAL_LIST } from '@/store/mutation-types'
import { mapActions, mapState } from 'vuex';

export default {
  name: 'paginated-list',
  data () {
    return {
      pageNum: 0,
      searchDialog: false,
      areas: [ '서울', '경기', '인천', '강원', '충청', '대전', '전라북도', '전라남도', '경상북도', '경상남도', '부산', '대구', '제주' ],
      selectPlace: [],
      selectKinds: [],
      kinds: [ '개', '고양이', '기타' ],
    }
  },
  props: {
    animals: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 12
    }
  },
  methods: {
    ...mapActions(['fetchLikedAnimalList','fetchLikedAnimalCnt']),

    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },
    cancle() {
      this.searchDialog = false
    },
    toDetailPage(id) {
      this.$router.push({
        name: 'AnimalDetailPage',
        params: { "id": id }
      })
    },
    selectSearch() {
      const { selectPlace, selectKinds } = this
      return axios.get('http://localhost:8888/petto/animals/lists')
              .then((res) => {
                var ani = []
                if(selectPlace.length > 0 && selectKinds.length > 0) {
                  var len = selectKinds.length + selectPlace.length
                  for(var i=0; i<res.data.length; i++) {
                    for(var j=0; j< len; j++) {
                      for(var o=0; o< len; o++)
                      if((res.data[i].notice_no.includes(selectPlace[j]) && res.data[i].kind.includes(selectKinds[o]))) {
                        ani.push(res.data[i])
                        this.$store.commit(FETCH_ANIMAL_LIST, ani)
                      }
                    }
                  }
                } else if(selectPlace.length > 0) {
                    for(var k=0; k<res.data.length; k++) {
                      for(var l=0; l< selectPlace.length; l++) {
                        if(res.data[k].notice_no.includes(selectPlace[l])) {
                          ani.push(res.data[k])
                          this.$store.commit(FETCH_ANIMAL_LIST, ani)
                        }
                      }
                    }
                } else if(selectKinds.length > 0) {
                    for(var m=0; m<res.data.length; m++) {
                      for(var n=0; n< selectKinds.length; n++) {
                        if(res.data[m].kind.includes(selectKinds[n])) {
                          ani.push(res.data[m])
                          this.$store.commit(FETCH_ANIMAL_LIST, ani)
                        }
                      }
                    }
                }
              this.searchDialog = false
              this.pageNum = 0
          })
    },

    addLikedAnimal(notice_no) {
      
      const memberNo = this.$store.state.session.memberNo
      const noticeNo = notice_no

      axios.post('http://localhost:8888/petto/member/addLikedAnimal', { memberNo, noticeNo })
        .then(() => {

          this.$store.state.likedAnimalList.push({ 'memberNo': memberNo, 'noticeNo': noticeNo })

          const targetIndex = this.$store.state.animals.findIndex(v => v.notice_no === notice_no)
          this.$store.state.animals[targetIndex].numberOfLiked ++

        })
        
        .catch(() => {
          alert('잠시후에 다시 시도해주세요.')
        })
    },

    chkLikedOrNot(notice_no) {
      for(var i=0; i<this.$store.state.likedAnimalList.length; i++) {
        if(notice_no == this.$store.state.likedAnimalList[i].noticeNo) {
          return true
        }
      }
      return false
    },

    deleteLikedAnimal(notice_no) {
      
      const memberNo = this.$store.state.session.memberNo
      const noticeNo = notice_no
      
      axios.put('http://localhost:8888/petto/member/deleteLikedAnimal', { memberNo, noticeNo }, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(() => {
          const targetIndex = this.$store.state.likedAnimalList.findIndex(v => v.notice_no === notice_no)
          this.$store.state.likedAnimalList.splice(targetIndex, 1)

          const targetIndex2 = this.$store.state.animals.findIndex(v => v.notice_no === notice_no)
          this.$store.state.animals[targetIndex2].numberOfLiked --
        })
        .catch(() => {
          alert('잠시후에 다시 시도해주세요.')
        })
    }

  },
  computed: {
    ...mapState(['session', 'likedAnimalList','likedAnimalCnt']),

    pageCount () {
      let listLeng = this.animals.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.animals.slice(start, end);
    }
  },
  mounted() {
    if(this.$cookies.get("user").id) {
      this.$store.state.session = this.$cookies.get("user")
      this.fetchLikedAnimalList(this.$cookies.get("user").memberNo)
    }
  }
}
</script>

<style lang="scss">

.search-btn{
  position: absolute;
  top:11em;
  right: 10em;
}

table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 1.2rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}
.btn-cover {
  margin-top: 2rem;
  margin-bottom: 5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}

.list-card{
  float: left;
  margin: 0% 3% 2% 3%;
  align-items: center;
}


@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,700);
@import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
figure.snip1477 {
    font-family: 'Raleway', Arial, sans-serif;
    position: relative;
    overflow: hidden;
    margin: 10px;
    margin-bottom: 10px; //여기
    min-width: 230px;
    max-width: 315px;
    width: 100%;
    color: #ffffff;
    text-align: center;
    font-size: 16px;
    background-color: #000000;
}
figure.snip1477 *,
figure.snip1477 *:before,
figure.snip1477 *:after {
-webkit-box-sizing: border-box;
  box-sizing: border-box;
-webkit-transition: all 0.55s ease;
  transition: all 0.55s ease;
}
figure.snip1477 img {
  max-width: 100%;
  backface-visibility: hidden;
  vertical-align: top;
  opacity: 0.9;
}
figure.snip1477 .title {
  position: absolute;
  top: 58%;
  left: 25px;
  padding: 5px 10px 10px;
}
figure.snip1477 .title:before,
figure.snip1477 .title:after {
  height: 2px;
  width: 400px;
  position: absolute;
  content: '';
  background-color: #ffffff;
}
figure.snip1477 .title:before {
  top: 0;
  left: 10px;
-webkit-transform: translateX(100%);
  transform: translateX(100%);
}
figure.snip1477 .title:after {
  bottom: 0;
  right: 10px;
-webkit-transform: translateX(-100%);
  transform: translateX(-100%);
}
figure.snip1477 .title div:before,
figure.snip1477 .title div:after {
  width: 2px;
  height: 300px;
  position: absolute;
  content: '';
  background-color: #ffffff;
}
figure.snip1477 .title div:before {
  top: 10px;
  right: 0;
-webkit-transform: translateY(100%);
  transform: translateY(100%);
}
figure.snip1477 .title div:after {
  bottom: 10px;
  left: 0;
-webkit-transform: translateY(-100%);
  transform: translateY(-100%);
}
figure.snip1477 h2 {
  font-weight: 400;
}

@font-face {
  font-family: 'SangSangRock';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/SangSangRockOTF.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

figure.snip1477 h4 {
  font-family: 'SangSangRock';
  font-size: 20px;
  display: block;
  font-weight: 500;
  // background-color: rgba(0, 0, 0, 0.3);
  padding: 1px 1px;
  color: #fff;
}
figure.snip1477 figcaption {
  position: absolute;
  bottom: 42%;
  left: 25px;
  text-align: left;
  opacity: 0;
  padding: 5px 60px 5px 10px;
  font-size: 0.8em;
  font-weight: 500;
  letter-spacing: 1.5px;
}
figure.snip1477 figcaption p {
  margin: 0;
}
figure.snip1477 a {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
figure.snip1477:hover img,
figure.snip1477.hover img {
  zoom: 1;
  filter: alpha(opacity=35);
-webkit-opacity: 0.35;
  opacity: 0.35;
}
figure.snip1477:hover .title:before,
figure.snip1477.hover .title:before,
figure.snip1477:hover .title:after,
figure.snip1477.hover .title:after,
figure.snip1477:hover .title div:before,
figure.snip1477.hover .title div:before,
figure.snip1477:hover .title div:after,
figure.snip1477.hover .title div:after {
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0);
}
figure.snip1477:hover .title:before,
figure.snip1477.hover .title:before,
figure.snip1477:hover .title:after,
figure.snip1477.hover .title:after {
    -webkit-transition-delay: 0.15s;
    transition-delay: 0.15s;
}
figure.snip1477:hover figcaption,
figure.snip1477.hover figcaption {
    opacity: 1;
    -webkit-transition-delay: 0.2s;
    transition-delay: 0.2s;
}

</style>