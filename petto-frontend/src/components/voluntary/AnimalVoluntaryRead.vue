<template>
    <form>
            <div class="board-box">
            <v-col cols="12" md="12" >
              <!--  <section><img :src="require(`@/assets/vol/content/${voluntaryboard.contentFileName}`)"/></section> -->

                 <section><h2 style="font-size:30px;">모집마감일 : {{voluntaryboard.closingDate}}</h2></section>
            </v-col>
            </div>
    </form>
</template>

<script>
export default {
    name: 'AnimalVoluntaryRead',
    props: {
        voluntaryboard: {
            type: Object,
            required: true
        }
    },
}
</script>

<style scoped>

.board-box {
    text-align: center;
    width: 50%;
    padding-top: 5%;
    margin: 0 auto;
}
</style>