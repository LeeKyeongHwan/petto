<template>
    <div class="sectiontitle">
        <h3>유기동물 통계</h3>
        <span class="headerLine"></span>
        <div id="projectFacts" class="sectionClass">
            <div class="fullWidth eight columns">
                <div class="projectFactsWrap ">
                      <div class="item wow fadeInUpBig animated animated" style="visibility: visible;">
                        <i><v-icon>pets</v-icon></i>
                        <p id="number1" class="number">{{ numOfAnimals }}</p>
                        <span></span>
                        <p>유기동물 수</p>
                    </div>
                    <div class="item wow fadeInUpBig animated animated" data-number="12" style="visibility: visible;">
                        <i><v-icon>local_hospital</v-icon></i>
                        <p id="number2" class="number">{{crawlInfo[0]}}</p>
                        <span></span>
                        <p>오늘 구조된 동물</p>
                    </div>
                    <div class="item wow fadeInUpBig animated animated" data-number="55" style="visibility: visible;">
                        <i><v-icon>favorite</v-icon></i>
                        <p id="number3" class="number">{{crawlInfo[1]}}%</p>
                        <span></span>
                        <p>입양률</p>
                    </div>
                    <div class="item wow fadeInUpBig animated animated" data-number="359" style="visibility: visible;">
                        <i><v-icon>sentiment_dissatisfied</v-icon></i>
                        <p id="number4" class="number">{{crawlInfo[2]}}%</p>
                        <span></span>
                        <p>안락사율</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
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

          //console.log(res);
          if(res.status == 200){

            //console.log(res.data);
            //console.log(res.data.split('\n'));
            if(res.data?.split('\n') && res.data?.split('\n').length > 8){
  
              //split('\n') \n이 엔터고 split('\n')은 엔터마다 자르고 배열로 만든다.
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

<style scoped>

@import url(https://fonts.googleapis.com/css?family=PT+Sans+Narrow);

body {
  font-family: Open Sans, "Helvetica Neue", "Helvetica", Helvetica, Arial,   sans-serif;
  font-size: 13px;
  color: #666;
  position: relative;
  -webkit-font-smoothing: antialiased;
  margin: 0;
}

* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form, p, blockquote, th, td {
  margin: 0;
  padding: 0;
  font-size: 13px;
  direction: ltr;
}

.sectionClass {
  padding: 20px 0px 50px 0px;
  position: relative;
  display: block;
}

.fullWidth {
  width: 100% !important;
  display: table;
  float: none;
  padding: 0;
  min-height: 1px;
  height: 100%;
  position: relative;
}


.sectiontitle {
  background-position: center;
  margin: 30px 0 0px;
  text-align: center;
  min-height: 20px;
}

.sectiontitle h3 {
  font-size: 30px;
  color: #222;
  margin-bottom: 0px;
  padding-right: 10px;
  padding-left: 10px;
}


.headerLine {
  width: 160px;
  height: 2px;
  display: inline-block;
  background: #101F2E;
}


.projectFactsWrap{
    display: flex;
  margin-top: 30px;
  flex-direction: row;
  flex-wrap: wrap;
}


#projectFacts .fullWidth{
  padding: 0;
}

.projectFactsWrap .item{
  width: 25%;
  height: 100%;
  padding: 50px 0px;
  text-align: center;
}

.projectFactsWrap .item:nth-child(1){
  background: rgb(255, 187, 0);
}

.projectFactsWrap .item:nth-child(2){
  background: rgb(255, 200, 48);
}

.projectFactsWrap .item:nth-child(3){
  background: rgb(255, 187, 0);
}

.projectFactsWrap .item:nth-child(4){
  background: rgb(255, 200, 48);
}

.projectFactsWrap .item p.number{
  font-size: 40px;
  padding: 0;
  font-weight: bold;
}

.projectFactsWrap .item p{
  color: rgba(255, 255, 255, 0.8);
  font-size: 18px;
  margin: 0;
  padding: 10px;
  font-family: 'Open Sans';
}


.projectFactsWrap .item span{
  width: 60px;
  background: rgba(255, 255, 255, 0.8);
  height: 2px;
  display: block;
  margin: 0 auto;
}


.projectFactsWrap .item i{
  vertical-align: middle;
  font-size: 50px;
  color: rgba(255, 255, 255, 0.8);
}


.projectFactsWrap .item:hover i, .projectFactsWrap .item:hover p{
  color: white;
}

.projectFactsWrap .item:hover span{
  background: white;
}

@media (max-width: 786px){
  .projectFactsWrap .item {
     flex: 0 0 50%;
  }
}


</style>
