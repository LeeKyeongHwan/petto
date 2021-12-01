<template>
    <div align="center">

        <div v-if="!paginatedData || (Array.isArray(paginatedData) && paginatedData.length === 0)">
                <span>
                    현재 등록된 자원봉사가 없습니다.
                </span>
        </div>

        <v-card v-for="voluntaryboard in paginatedData" :key="voluntaryboard.volunteerNo" width="800px" style=" margin-bottom:5%;">
            <section v-if="voluntaryboard.closingDate < todayDate">
                <router-link :to="{ name: 'AnimalVoluntaryReadPage',
                                    params: { volunteerNo: voluntaryboard.volunteerNo.toString() } }">
                    <div class="closedEvent">
                      <img :src="require(`@/assets/vol/title/${voluntaryboard.fileName}`)"
                      style="width:800px; height:200px;">
                    </div>
                </router-link>
            </section>

            <section v-else>
                <router-link :to="{ name: 'AnimalVoluntaryReadPage',
                                    params: { volunteerNo: voluntaryboard.volunteerNo.toString() } }">
                    <img :src="require(`@/assets/vol/title/${voluntaryboard.fileName}`)"
                        style="width:800px; height:200px;"/>
                </router-link>
            </section>

            <section><p style="text-align:right; font-size:14px;"> ( 봉사 모집기한 : ~ {{ voluntaryboard.closingDate }} )</p></section>

            <v-card-subtitle style="padding:0px 0px 10px 0px;"><b><h5>{{ voluntaryboard.volTitle }}</h5></b></v-card-subtitle>

        </v-card>

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
export default {
    name: 'AnimalVoluntaryList',
    props: {
        voluntaryboards: {
            type: Array
        },
        pageSize: {
        type: Number,
        required: false,
        default: 4
        },
        initPageNum: {
          type: Number,
          required: true
        }
    },
    data () {
        return {
        // pageNum: 0,
        todayDate: '',
        pageNum: this.initPageNum
        }
    },
    methods: {
        nextPage () {
            this.pageNum += 1;
            this.$router.push({
                name: 'AnimalVoluntaryListPage',
                params: { pageNum: this.pageNum }
            })
        },
        prevPage () {
            this.pageNum -= 1;
            this.$router.push({
                name: 'AnimalVoluntaryListPage',
                params: { pageNum: this.pageNum }
            })
        },
        changeDate () {
        const date = new Date()
        const today = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`
        this.todayDate = today
        },
    },
    computed: {
        pageCount () {
        let listLeng = this.voluntaryboards.length,
            listSize = this.pageSize,
            page = Math.floor(listLeng / listSize);
        if (listLeng % listSize > 0) page += 1;

        return page;
        },
        paginatedData () {
        const start = this.pageNum * this.pageSize,
                end = start + this.pageSize;
        return this.voluntaryboards.slice(start, end);
        }
    },
    mounted() {
        this.changeDate()
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
h5{
  font-family: 'GowunDodum-Regular';
  color: black;
  font-size: 20px;
}
p{
  font-family: 'GowunDodum-Regular';
}
.closedEvent {
  position:relative;
}

.closedEvent:before {
  content: "";
  position: absolute;
  height:200px;
  width:800px;
  background: rgba(0,0,0,0.4);
  background-image: url('https://img.apti.co.kr/apti/event/upfiles/end.png');
  background-size: 150px;
  background-repeat:no-repeat;
  background-position:right bottom;
}

.overlay {
  position: absolute;
  top: 0em;
  right: 0em;
}

</style>
