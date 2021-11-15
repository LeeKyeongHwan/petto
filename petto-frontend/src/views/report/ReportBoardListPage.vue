<template>
    <div align="center">

        <h1 style="margin-bottom: -80px;">{{ keyword }}게시판</h1>

        <span>

            <v-btn text class="normalText" style="float: left; margin-left: 120px;" @click="toWritePage">
                <v-icon>
                    mode_edit
                </v-icon>
                &emsp; 게시글 쓰기
            </v-btn>

            <div v-for="(btn, idx) in btnList" :key="idx" style="display: inline-block; margin-right: 30px; float: right;">

                <v-btn v-if="keyword == btn" disabled class="normalText" style="font-size: 15px;" text large>
                    {{ btn }}
                </v-btn>

                <v-btn v-else class="normalText" style="font-size: 15px;" text large @click="chooseCategory($event)">
                <v-btn v-else class="normalText" style="font-size: 15px;" text large>
                    {{ btn }}
                </v-btn>
            </div>

        </span>

        <div>

        </div>
        <v-container>


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
            btnList: [ '실종', '보호', '목격' ]
        }
    },
    methods: {
        toWritePage() {
            this.$router.push({ name: 'ReportWritePage' })
        }
    },
    mounted: {

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
        ...mapState(['reportList'])
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
</style>