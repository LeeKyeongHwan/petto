<template>
    <form>
            <div class="board-box">
            <v-col cols="12" md="12" >
            <section><img :src="require(`@/assets/vol/content/${voluntaryboard.contentFileName}`)"
              /></section>
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
    } 
}
</script>

<style scoped>

.board-box {
    text-align: center;
    width: 50%;
    padding-top: 5%;
    margin: 0 auto;
}
.register-box{
    border: 1px solid #cccccc;
    background-color: white;
    float: left;
    width: 500px;
    height: 500px;
}

.btn-size{
    margin: 0 auto;
    text-align: center;
    width: 50%;
    padding: 3% 0% 5% 0%;
}

h1{
    font-family: "Gowun Dodum";
    font-size: 30px;
    margin-top: 5%;
    text-align: center;

}

</style>