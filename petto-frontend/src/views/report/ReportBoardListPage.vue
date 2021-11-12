<template>
    <div align="center">

        <h1 style="margin-bottom: -80px;">{{ keyword }}게시판</h1>

        <v-container>

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

            <v-simple-table v-if="filterReportList">

                <tr v-for="(report, index) in filterReportList" :key="index" class="normalText" style="color: black;">

                    <td style="width: 30%;">{{ report.title }}</td>
                    <td>{{ report.breed }}</td>
                    <td style="width: 50%;">{{ report.whereHappened }}</td>
                    <td style="text-align: right;">게시자 &ensp; {{ report.writer }}</td>
                    
                </tr>

                <tr v-if="filterReportList.length == ''" class="normalText" style="color: black;">
                    올라온 공고가 아직 없습니다!
                </tr>

            </v-simple-table>

            <br>
            <br>
            <br>

            <v-pagination
                v-model="page"
                :length="4"
                circle
                color="#42b8d4"
                style="background-color: white; box-shadow: 0px 0px white;"
            ></v-pagination>

        </v-container>

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
            page: 1
        }
    },
    methods: {
        ...mapActions(['fetchReportList']),

        toWritePage() {
            if(this.$store.state.session) {
                this.$router.push({ name: 'ReportWritePage' })

            } else alert('로그인이 필요해요!')
        },

        chooseCategory(event) {
            const keyword = event.target.innerText
            
            this.$router.push({ name: 'ReportBoardListPage', params: { 'keyword': keyword } })
        }
    },
    mounted() {
        this.$store.dispatch("fetchReportList")

        if(this.$cookies.get("user").id) {
            this.$store.state.session = this.$cookies.get("user")
        }
    },

    computed: {
        ...mapState(['reportList']),

        filterReportList() {

            var reportList = []

            if(this.keyword == '목격') {

                for(var i=0; i<this.reportList.length; i++) {

                    if(this.reportList[i].category == '목격') reportList.push(this.reportList[i])
                }
                return reportList

            } else if(this.keyword == '보호') {

                for(var j=0; j<this.reportList.length; j++) {

                    if(this.reportList[j].category == '보호') reportList.push(this.reportList[j])
                }
                return reportList

            } else {

                for(var k=0; k<this.reportList.length; k++) {

                    if(this.reportList[k].category == '실종') reportList.push(this.reportList[k])
                }
                return reportList
            }
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