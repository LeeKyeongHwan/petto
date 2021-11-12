<template>
    <div align="center" class="board-box">
        <v-card v-for="voluntaryboard in voluntaryboards" :key="voluntaryboard.volunteerNo" width="500px" style=" margin-bottom:5%;">
            <section>
                <router-link :to="{ name: 'AnimalVoluntaryReadPage',
                                    params: { volunteerNo: voluntaryboard.volunteerNo.toString() } }">
                    <img :src="require(`@/assets/vol/title/${voluntaryboard.fileName}`)"
                        style="width:500px; height:200px;"/>
                </router-link>
            </section>

            <v-card-subtitle><b>{{ voluntaryboard.volTitle }}</b></v-card-subtitle>

        </v-card>
    </div>
</template>

<script>
export default {
    name: 'AnimalVoluntaryList',
    props: {
        voluntaryboards: {
            type: Array
        }
    },
}
</script>

<style scoped>

table{
    width: 55%;
    border: 1px solid #ebecec;
}

.top-tr {
    background-color: #f6f7f7;
}

.board-box {
    margin-top: 5%;
}

h4 {
    margin-top: 0px;
    font-family: 'Nanum Myeongjo';
    font-size: 18px;
    margin-left: 40%;
}

.member-box {
    margin-top: 2%;
}
</style>