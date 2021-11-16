<template>
    <div align="center">

        <h1 style="margin-bottom: -80px;">{{ keyword }}게시판</h1>

        <v-container style="display: inline-block;">

            <span>

                <v-btn text class="normalText" style="float: left;" @click="toWritePage">
                    <v-icon>
                        mode_edit
                    </v-icon>
                    &emsp; 게시글 쓰기
                </v-btn>

                <div v-for="(btn, idx) in btnList" :key="idx" style="display: inline-block; float: right;">

                    <v-btn v-if="keyword == btn" disabled class="normalText" style="font-size: 15px;" text large>
                        {{ btn }}
                    </v-btn>

                    <v-btn v-else class="normalText" style="font-size: 15px;" text large @click="chooseCategory($event)">
                        {{ btn }}
                    </v-btn>
                </div>

            </span>

            <br>
            <br>
            <br>
            <br>

            <v-simple-table v-if="filterReportList" style="height: 250px;">

                <tr v-for="(report, index) in filterReportList" :key="index" class="normalText" style="color: black;" @click="toReadPage(report.reportNo)">

                    <td v-if="!report.expired" style="width: 30%; font-size: 12px;">{{ report.title }}</td>
                    <td v-else-if="report.expired" style="width: 30%; font-size: 12px;">만료된 공고</td>

                    <td>
                        <span style="font-size:10px;">종:&ensp;</span>{{ report.breed }}</td>

                    <td style="width: 20%; font-size: 12px;">{{ report.whereHappened }}</td>

                    <td style="width: 12%;">
                        <span style="font-size:10px;">게시자:&ensp;</span> {{ report.writer }}</td>

                    <td style="width: 12a%; text-align: right;">
                        <span style="font-size:10px;">발생일:&ensp;</span>{{ report.whenHappened }}</td>
                    
                </tr>

                <tr v-if="filterReportList.length == 0" class="normalText" style="color: black;">
                    올라온 공고가 아직 없습니다!
                </tr>

            </v-simple-table>

            <br>
            <br>
            <br>

            <v-pagination
                v-model="page"
                :length="reportListLength"
                circle
                color="#42b8d4"
                style="background-color: white; box-shadow: 0px 0px white;"
            ></v-pagination>

        </v-container>

        <div class="searchBox">

            <v-select
                v-model="choosenBreed"
                :items="animals"
                attach
                chips
                label="종"
                style="width: 100px; float: left;"
                class="normalText"
            ></v-select>

            <v-select
                v-model="choosenArea"
                :items="areas"
                attach
                chips
                label="지역"
                style="width: 130px; float: left;"
                class="normalText"
            ></v-select>

            <br>
            <br>
            <br>
            <br>

            <label class="normalText" style="color: grey;">
                기준 시작일
                <input type="date" v-model="startDate" style="width: 140px; color: black;" required class="normalText"/>
            </label>

            <label class="normalText" style="color: grey;">
                기준 만료일
                <input type="date" v-model="endDate" style="width: 140px; color: black;" required class="normalText"/>
            </label>

            <br>
            <br>

            <span>
                <v-btn text small outlined class="normalText" @click="sortList" style="margin-right: 10px;">
                    검색
                </v-btn>
                
                <v-btn text small outlined class="normalText" @click="initList">
                    초기화
                </v-btn>
            </span>

        </div>

    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
    props: {
        keyword: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            btnList: [ '실종', '보호', '목격' ],
            animals: [ '전체', '개', '고양이', '기타' ],
            page: 1,
            category: this.keyword,
            choosenBreed: '',
            areas: [ '전국', '서울', '경기', '인천', '강원', '충청', '대전', '전라북도', '전라남도', '경상북도', '경상남도', '부산', '대구', '제주' ],
            choosenArea: '',
            startDate: '',
            endDate: '',

            reportListRepository: this.$store.state.reportList
        }
    },
    methods: {
        ...mapActions(['fetchReportList']),

        toReadPage(reportNo) {
            let routeData = this.$router.resolve({
                name: 'ReportReadPage',
                params: { 'reportNo': reportNo }
            });window.open(routeData.href, '_blank')
        },

        toWritePage() {
            if(this.$store.state.session) {
                this.$router.push({ name: 'ReportWritePage' })

            } else alert('로그인이 필요해요!')
        },

        chooseCategory(event) {
            const keyword = event.target.innerText
            
            this.$router.push({ name: 'ReportBoardListPage', params: { 'keyword': keyword } })
        },

        initList() {

            this.$store.state.reportList = this.reportListRepository
        },

        sortList() { 
            this.page = 1

            this.$store.state.reportList = this.reportListRepository

            var tmpList = []

            let isAreaChoosen = false
            let isBreedChoosen = false
            let isDateChoosen = false 

            if(this.choosenArea != '' && this.choosenArea != '전국') isAreaChoosen = true
            if(this.choosenBreed != '' && this.choosenBreed != '전체') isBreedChoosen = true
            if(this.startDate != '' || this.endDate != '') isDateChoosen = true

            for(var i=0; i<this.$store.state.reportList.length; i++) {

                if(isAreaChoosen) {

                    if(!this.reportList[i].whereHappened.includes(this.choosenArea)) continue;
                }

                if(isBreedChoosen) {

                    if(this.choosenBreed == '기타' && this.reportList[i].breed != '개' && this.reportList[i].breed != '고양이');

                    else if(this.reportList[i].breed != this.choosenBreed) continue;
                }

                if(isDateChoosen) {

                    if(this.startDate == '') {

                        if(this.endDate < this.reportList[i].whenHappened) continue;

                    } else if(this.endDate == '') {

                        alert('검색 기준 만료 날짜를 선택해주세요.')
                        return false;

                    } else
         
                    if(this.startDate > this.reportList[i].whenHappened || this.endDate < this.reportList[i].whenHappened) continue;
                }

                tmpList.push(this.reportList[i]) 
            }

            this.$store.state.reportList = tmpList
        }
    },
    mounted() {
        this.$store.dispatch("fetchReportList")

        if(this.$cookies.get("user").id) {
            this.$store.state.session = this.$cookies.get("user")
        }
    },

    computed: {
        ...mapState(['reportList', 'session']),

        filterReportList() {

            const start = (this.page - 1) * 5
            const end = start + 5;
            
            var reportList = []

            for(var i=0; i<this.reportList.length; i++) {

                if(this.reportList[i].category == this.category) reportList.push(this.reportList[i])
            }
            return reportList.slice(start, end);
        },

        reportListLength() {

            var length = 0

            for(var i=0; i<this.reportList.length; i++) {

                const objArr = this.reportList[i]

                if(objArr.category == this.category) length ++
            }
            
            var listLength = Math.floor(length / 5)

            if (length % 5 > 0) listLength += 1;

            return listLength
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
    font-size: 25px;
    text-align: center;
    padding: 5% 0% 5% 0%;
}

td:hover {
    color: #42b8d4;
}
</style>

<style>

.searchBox {
    display: inline-block; 
    position: fixed; 
    right: 3%; 
    border: 1px solid #42b8d4;
    width: 200px;
    background-color: white;
    padding: 20px 30px;
    border-radius: 20px;
}
</style>
