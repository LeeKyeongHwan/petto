<template>
    <div align="center">

        <h1>여러분들의 작은 제보가 유기동물들에게 큰 힘이됩니다.</h1>

        <span @click="chooseCategory($event)">

            <v-btn text x-large>
                <p class="normalText" style="font-size: 25px;">
                    목격
                </p>
            </v-btn>
            
            &emsp; &emsp; &emsp;

            <v-btn text x-large>
                <p class="normalText" style="font-size: 25px;">
                    보호
                </p>
            </v-btn>

            &emsp; &emsp; &emsp;

            <v-btn text x-large>
                <p class="normalText" style="font-size: 25px;">
                    실종
                </p>
            </v-btn>

        </span>
    </div>
</template>

<script>

export default {
    methods: {
        chooseCategory(event) {
            const keyword = event.target.innerText
            
            this.$router.push({ name: 'ReportBoardListPage', params: { 'keyword': keyword } })
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
}
</style>