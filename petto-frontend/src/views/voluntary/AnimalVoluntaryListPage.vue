<template>
    <div id="voluntaryboard">   
        <v-container class="justify center" style="margin-top:5%;">
            <h1>자원봉사</h1>
        </v-container>

        <v-container class="justify" style="text-align:right; margin-top:1%;">
            <v-btn color="orange" outlined route :to="{ name: 'AnimalVoluntaryRegister' }"
                style="margin:3% 0% 3%; 0%"
                v-if="this.access == '관리자'">봉사등록</v-btn>
        </v-container>

        <animal-voluntary-list :voluntaryboards="voluntaryboards" :initPageNum="parseInt(pageNum)"/>
    </div>  
</template>

<script>
import { mapActions,mapState } from 'vuex'
import AnimalVoluntaryList from '@/components/voluntary/AnimalVoluntaryList.vue'

export default {
    name: 'AnimalVoluntaryListPage',
    components: {
        AnimalVoluntaryList
    },
    data() {
        return {
            volTitle: '',
            pageArray: [],
            access:''
        }
    },
    props: {
        pageNum: {
            type:  [Number, String],
            required: false,
            default: 0
        }
    },
    computed: {
        ...mapState(['voluntaryboards', 'session'])
    },
    mounted() {
        if(this.$cookies.isKey("user")) {
            this.$store.state.session = this.$cookies.get("user");

            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)
                this.access = this.$cookies.get("user").auth
            }
        } else this.access = '비회원'
    },
    methods: {
        ...mapActions(['fetchVoluntaryBoardList', 'fetchAlarmList'])
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
}
</style>
