<template>
    <div align="center" class="report">

        <h1>여러분들의 작은 제보가 유기동물들에게 큰 힘이됩니다.</h1>

        <span @click="chooseCategory($event)">

            <v-btn text x-large>
                <p class="normalText" style="font-size: 25px; color:white;">
                    목격
                </p>
            </v-btn>
            
            &emsp; &emsp; &emsp;

            <v-btn text x-large>
                <p class="normalText" style="font-size: 25px; color:white;">
                    보호
                </p>
            </v-btn>

            &emsp; &emsp; &emsp;

            <v-btn text x-large>
                <p class="normalText" style="font-size: 25px; color:white;">
                    실종
                </p>
            </v-btn>

        </span>
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
    methods: {
        ...mapActions(['fetchAlarmList']),

        chooseCategory(event) {
            const keyword = event.target.innerText
            this.$router.push({ name: 'ReportBoardListPage', params: { 'keyword': keyword } })
        }
    },

    mounted() {
        if(this.$cookies.isKey("user")) {
  
            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)

                this.$store.state.isLoggedIn = true;
            }
        }
    },
    computed: {
        ...mapState(['session', 'isLoggedIn', 'updateAlarmList'])
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
    font-size: 20px;
    text-align: center;
    padding: 5% 0% 5% 0%;
    color: white;
}

.report{
    background-color:#42b8d4;
    padding-bottom: 5%;
    margin-top: 10%;
    margin-bottom:15%;
}
</style>