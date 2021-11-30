<template>
    <form>
         <div class="title-box">
                <span><img :src="require(`@/assets/vol/title/${voluntaryboard.fileName}`)" style="width:800px; height:200px;"/></span>
                <span class="title"><h1>{{voluntaryboard.volTitle}}</h1>
                <p style="font-size:15px;"> - 유기동물과 사람을 위한 사회 만들기 PETTO 가 시작합니다.</p></span>
        </div>

        <v-divider></v-divider>
            <div class="board-box">
            <v-col cols="12" md="12" >
                <span><h1 style="font-size:25px;">봉사정보</h1></span>
                
                <v-container>
                <span><p><v-icon color="orange lighten-2">bookmarks</v-icon>{{voluntaryboard.volunteerNo}}</p></span>
                <span><p><v-icon color="orange lighten-2">event</v-icon> 마감일 :  {{voluntaryboard.closingDate}} </p></span>
                </v-container>

                <span><img :src="require(`@/assets/vol/content/${voluntaryboard.contentFileName}`)"/></span>
            
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



@font-face {
    font-family: 'GowunDodum-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
h1 {
    font-family: 'GowunDodum-Regular';
    font-size: 23px;
    text-align: center;
}

@font-face {
    font-family: 'MapoGoldenPier';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoGoldenPierA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

p{
    font-family: 'MapoGoldenPier';
    font-size: 18px;
    /* margin: 2vw 0vw 2vw 6vw; */
    text-align: left;
}

h2{
    font-family: 'MapoGoldenPier';
    font-size: 18px;
}

.board-box {
    text-align: center;
    width: 50%;
    padding-top: 1%;
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
.title-box{
    /* width:100%;  */
    height:100%; 
    background-color:rgb(240, 240, 240);
    padding:2% 5% 2% 5%;
    margin-top: 5%;
}

.title{
    display:inline-block;
    margin-left:5vw;
    /* background-color: white; */
}
</style>