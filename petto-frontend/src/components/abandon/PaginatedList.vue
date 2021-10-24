<template>
  <div>
    <v-row justify="center">
      <v-card v-for="animal in paginatedData" :key="animal.notice_no" class="list-card"> 
        <figure class="snip1477">
            <img :src="animal.image" width="350" height="300"/>
            <div class="title">
                <div>
                <h4>상세보기 <v-icon color="white">keyboard_arrow_right</v-icon></h4>
                </div>
            </div>
            <figcaption>
                <p>
                {{animal.notice_no}}<br/>
                공고기간 : {{animal.notice_sd}} - {{animal.notice_ed}}<br/>
                상태 : {{ animal.state }}<br/>
                품종 : {{animal.kind}}<br/>
                발견장소 : {{animal.happenplace}}</p>
            </figcaption>
        </figure>
      </v-card>
    </v-row>
    
    <v-container>
      <div class="btn-cover">
        <v-btn :disabled="pageNum === 0" @click="prevPage" icon text>
          <v-icon>chevron_left</v-icon>
        </v-btn>
        <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <v-btn  :disabled="pageNum >= pageCount - 1" @click="nextPage" icon text>
          <v-icon>chevron_right</v-icon>
        </v-btn >
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'paginated-list',
  data () {
    return {
      pageNum: 0
    }
  },
  props: {
    animals: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 12
    }
  },
  methods: {
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    }
  },
  computed: {
    pageCount () {
      let listLeng = this.animals.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.animals.slice(start, end);
    }
  }
}
</script>

<style lang="scss">


table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 1.2rem;
}
table tr {
  height: 2rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 1.1rem;
}
.btn-cover {
  margin-top: 2rem;
  margin-bottom: 5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}

.list-card{
  float: left;
  margin: 0% 3% 2% 3%;
  align-items: center;
}


@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,700);
@import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
figure.snip1477 {
    font-family: 'Raleway', Arial, sans-serif;
    position: relative;
    overflow: hidden;
    margin: 10px;
    min-width: 230px;
    max-width: 315px;
    width: 100%;
    color: #ffffff;
    text-align: center;
    font-size: 16px;
    background-color: #000000;
}
figure.snip1477 *,
figure.snip1477 *:before,
figure.snip1477 *:after {
-webkit-box-sizing: border-box;
  box-sizing: border-box;
-webkit-transition: all 0.55s ease;
  transition: all 0.55s ease;
}
figure.snip1477 img {
  max-width: 100%;
  backface-visibility: hidden;
  vertical-align: top;
  opacity: 0.9;
}
figure.snip1477 .title {
  position: absolute;
  top: 58%;
  left: 25px;
  padding: 5px 10px 10px;
}
figure.snip1477 .title:before,
figure.snip1477 .title:after {
  height: 2px;
  width: 400px;
  position: absolute;
  content: '';
  background-color: #ffffff;
}
figure.snip1477 .title:before {
  top: 0;
  left: 10px;
-webkit-transform: translateX(100%);
  transform: translateX(100%);
}
figure.snip1477 .title:after {
  bottom: 0;
  right: 10px;
-webkit-transform: translateX(-100%);
  transform: translateX(-100%);
}
figure.snip1477 .title div:before,
figure.snip1477 .title div:after {
  width: 2px;
  height: 300px;
  position: absolute;
  content: '';
  background-color: #ffffff;
}
figure.snip1477 .title div:before {
  top: 10px;
  right: 0;
-webkit-transform: translateY(100%);
  transform: translateY(100%);
}
figure.snip1477 .title div:after {
  bottom: 10px;
  left: 0;
-webkit-transform: translateY(-100%);
  transform: translateY(-100%);
}
figure.snip1477 h2 {
  font-weight: 400;
}

@font-face {
  font-family: 'SangSangRock';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/SangSangRockOTF.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

figure.snip1477 h4 {
  font-family: 'SangSangRock';
  font-size: 20px;
  display: block;
  font-weight: 500;
  // background-color: rgba(0, 0, 0, 0.3);
  padding: 1px 1px;
  color: #fff;
}
figure.snip1477 figcaption {
  position: absolute;
  bottom: 42%;
  left: 25px;
  text-align: left;
  opacity: 0;
  padding: 5px 60px 5px 10px;
  font-size: 0.8em;
  font-weight: 500;
  letter-spacing: 1.5px;
}
figure.snip1477 figcaption p {
  margin: 0;
}
figure.snip1477 a {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
figure.snip1477:hover img,
figure.snip1477.hover img {
  zoom: 1;
  filter: alpha(opacity=35);
-webkit-opacity: 0.35;
  opacity: 0.35;
}
figure.snip1477:hover .title:before,
figure.snip1477.hover .title:before,
figure.snip1477:hover .title:after,
figure.snip1477.hover .title:after,
figure.snip1477:hover .title div:before,
figure.snip1477.hover .title div:before,
figure.snip1477:hover .title div:after,
figure.snip1477.hover .title div:after {
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0);
}
figure.snip1477:hover .title:before,
figure.snip1477.hover .title:before,
figure.snip1477:hover .title:after,
figure.snip1477.hover .title:after {
    -webkit-transition-delay: 0.15s;
    transition-delay: 0.15s;
}
figure.snip1477:hover figcaption,
figure.snip1477.hover figcaption {
    opacity: 1;
    -webkit-transition-delay: 0.2s;
    transition-delay: 0.2s;
}

</style>