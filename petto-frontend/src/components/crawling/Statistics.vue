<template>
    <!-- <v-container> -->
      <section style="width:100%; margin:3%;">
        <div>
          <v-card class="sta-card grow">
            <p>유기동물 수</p>
            <span></span>
            <p style="font-size:20px;">{{ numOfAnimals }}</p>
          </v-card>
          <v-card class="sta-card grow">
            <p class="texts-overflow">오늘 구조된 동물</p>
            <span></span>
            <p style="font-size:20px;">{{crawlInfo[0]}}마리</p>
          </v-card>
        </div>
        <div>
          <v-card class="sta-card grow">
            <p>입양률</p>
            <span></span>
            <p style="font-size:20px;">{{crawlInfo[1]}}%</p>
          </v-card>
          <v-card class="sta-card grow">
            <p>안락사율</p>
            <span></span>
            <p style="font-size:20px;">{{crawlInfo[2]}}%</p>
          </v-card>
        </div>
      </section>
    <!-- </v-container> -->
</template>

<script>

import axios from "axios";
import { mapState } from "vuex";
import { FETCH_NUM_OF_ANIMALS } from '@/store/mutation-types'


export default {
  name: "Statistics",
  created(){
      axios.post("http://localhost:5000/pawinhandCrawling")
        .then(res => {

          if(res.status == 200){

            if(res.data?.split('\n') && res.data?.split('\n').length > 8){
              this.$store.state.crawlInfo = [];
              this.$store.state.crawlInfo[0] = res.data.split('\n')[1];
              this.$store.state.crawlInfo[1] = res.data.split('\n')[4];
              this.$store.state.crawlInfo[2] = res.data.split('\n')[7];
            }
          } else {
            alert('일시적인 오류입니다. \n잠시후 다시 실행해 주세요');
          }
        });
  },
  computed: {
    ...mapState({crawlInfo: state => state.crawlInfo,
                numOfAnimals: state => state.numOfAnimals})
  },

  mounted () {
      axios.get('http://localhost:8888/petto/animals/numOfAnimals')
        .then((res) => {
          this.$store.commit(FETCH_NUM_OF_ANIMALS, res.data)
        })
        .catch((err) => {
          console.log(err.message)
        })
  },


}
</script>

<style lang="scss" scoped>

@import url(https://fonts.googleapis.com/css?family=PT+Sans+Narrow);

.sta-card{
  position: relative;
  float:left;
  width:48%;
  height: 100%;
  padding:10% 3% 10% 3%;
  margin:1%;
  background-color: rgba(235, 169, 46, 0.3);
}

.grow {
	transition-property: transform;
	&:hover {
		transform: scale(1.1);
    background-color: rgba(255, 183, 49, 0.8);
    color:white;
	}
}

p{
  font-size: 13px;
  text-align: center;
}

.texts-overflow{
    display: block;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
}

</style>