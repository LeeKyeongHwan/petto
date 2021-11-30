<template>
  <div>
    <v-container class="justify center">
                <a href="/abandonedAnimal/list/page=0&place=none&kind=none" style="color:black;">
                <v-btn outlined style="margin:5%;" width="10vw">
                유기동물
                </v-btn>
                </a>
            <v-btn outlined route :to="{name: 'FacilityListPage'}" width="10vw">보호소 </v-btn>
        </v-container>
    <h1>유기동물 리스트</h1>

    <!-- <paginated-list v-if="animals" :animals="animals" :pageNum="pageNum" :place="place" :kind="kind" style="position relative;"/> -->
    <paginated-list v-if="animals" :animals="animals" :initPageNum="parseInt(pageNum)" :place="place" :kind="kind" style="position relative;"/>
    <p v-else-if="!animals">???</p>

    <div id="latestSeenShower">
      <br>

      <p class="normalText" style="color: black;">최근에 본 유기동물</p>

      <v-btn text class="upBtn" :disabled="listNum === 0" @click="goListUp">
        <v-icon>
          expand_less
        </v-icon>
      </v-btn>

      <br>

      <div v-for="(latestSeenAni, index) in latestSeen" :key="index">

        <img :src="latestSeenAni.imgSrc" class="lateSeenThumbnail" @click="toDetailPage(latestSeenAni.noticeNo)"/>

        <v-btn text x-small color="grey" class="delBtn" @click="delLatestSeen(latestSeenAni.noticeNo)">
          <v-icon>
            cancel
          </v-icon>
        </v-btn>

      </div>

      <span class="listCnt">{{ listNum + 1 }} / {{ listCount }}</span>

      &emsp; &ensp;

      <br>

      <v-btn text class="downBtn" :disabled="listNum >= listCount - 1"  @click="goListDown">
        <v-icon>
          expand_more
        </v-icon>
      </v-btn>

      <span style="display: inline-block; width: 60px;"/>

    </div>

  </div>
</template>

<script>
import PaginatedList from '@/components/abandon/PaginatedList'
import {mapActions, mapState} from 'vuex'
export default {
  name: 'AbandoneAnimal',
  components: {
    PaginatedList
  },
  props: {
    pageNum: {
      type: Number,
      required: false,
      default: 0
    },
    place: {
      type: String,
      required: false,
      default: 'none'
    },
    kind: {
      type: String,
      required: false,
      default: 'none'
    }
  },
  data() {
    return {
      pageArray: [],
      listNum: 0,
      LATEST_SEEN_SIZE: 3,
      tmpLatestSeen: [],
      latestSeenDeleteCnt: 0
    }
  },
  computed: {
      ...mapState(['animals', 'latestSeenAnimals', 'isLoggedIn', 'session']),
      latestSeen() {
        console.log(this.latestSeenDeleteCnt)
        if(this.$cookies.get("latestSeen")) {
          const start = this.listNum * this.LATEST_SEEN_SIZE
          const end = start + this.LATEST_SEEN_SIZE;
          const seenAnimalsArr = JSON.parse(this.$cookies.get("latestSeen"))
          return seenAnimalsArr.reverse().slice(start, end)
        } else return null
      },
      listCount() {
        console.log(this.latestSeenDeleteCnt)
    
        let listLeng = JSON.parse(this.$cookies.get('latestSeen')).length
        let page = Math.floor(listLeng / this.LATEST_SEEN_SIZE)
        if(listLeng % this.LATEST_SEEN_SIZE > 0) page += 1;
        return page;
      }
  },
  mounted() {
    let formData = new FormData()
    formData.append('selectedPlace', this.place)
    formData.append('selectedKinds', this.kind)

    //if(this.$store.state.animals == '')
    this.fetchFIlteredAniList(formData)

    if(this.$cookies.isKey("user")) {

      this.$store.state.session = this.$cookies.get("user");

      if(this.$store.state.session != null) {
        this.$store.dispatch('fetchAlarmList', this.session.id)
        this.fetchLikedAnimalList(this.$cookies.get("user").memberNo)
        this.$store.state.isLoggedIn = true;
      }
    }
  },
  methods: {
      ...mapActions(['fetchAnimalList', 'fetchFIlteredAniList', 'fetchLikedAnimalList']),
      goListUp() {
        this.listNum -= 1
      },
      goListDown() {
        this.listNum += 1
      },
      toDetailPage(noticeNo) {
        // let routeData = this.$router.resolve({
        // name: 'AnimalDetailPage',
        // params: { id: noticeNo }
        // });window.open(routeData.href, '_blank')
        this.$router.push({
          name: 'AnimalDetailPage',
          params: { id: noticeNo }
        })
      },
      delLatestSeen(noticeNo) {
        this.latestSeenDeleteCnt++
        this.tmpLatestSeen = JSON.parse(this.$cookies.get('latestSeen'))
        const targetIndex = this.tmpLatestSeen.findIndex(v => v.noticeNo === noticeNo)
        this.tmpLatestSeen.splice(targetIndex, 1)
        this.$cookies.set('latestSeen', JSON.stringify(this.tmpLatestSeen), '12h')
      }
  }
}
</script>

<style scoped>
@font-face {
    font-family: 'GowunDodum-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
h1 {
    font-family: 'GowunDodum-Regular';
    font-size: 30px;
    text-align: center;
    padding: 0% 0% 5% 0%;
}
</style>

<style>
#latestSeenShower {
  position: fixed; 
  width: 150px; 
  height: 550px;
  text-align: center;
  margin-right: 6%;
  margin-top: 60px;
  background-color: white;
  border: 2px solid #42b8d4;
  border-radius: 7px;
  top: 300px; /* 창에서 위에서 부터의 높이 */ 
  right: 0;
}
.lateSeenThumbnail {
  width: 120px; 
  height: 120px; 
  display: block; 
  margin-left: 9%;
  margin-bottom: 10px;
  border-radius: 5px;
}
.lateSeenThumbnail:hover {
  width: 120px; 
  height: 120px; 
  display: block; 
  margin-left: 9%;
  margin-bottom: 10px;
  border-radius: 5px;
  filter: grayscale(100%);
  opacity: 0.7;
}
.upBtn {
  position: relative;
  margin-top: -12px;
  margin-bottom: 5px;
}
.downBtn {
  position: fixed; 
  top: 865px;
}
.listCnt {
  position: fixed; 
  top: 845px;
}
.delBtn {
  position: absolute;
  margin-top: -125px;
  left: 95px; 
}
</style>