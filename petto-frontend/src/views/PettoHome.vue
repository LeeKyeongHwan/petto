<template>
  <div>
    <div id="main">
      <!-- 영상으로 교체 -->
      <div>
       <video src="@/assets/img/mains.mp4" muted loop autoplay class="video-main"/>

       <section>
            <div class="main-text">
                <div class="fade-in-box">
                    <span><h4>Dogs are not our whole life,</h4></span>
                    <span><h4>but they make our lives whole.</h4></span>
                </div>
            </div>
          </section>
      </div>
      <!--  -->

      <div id="header">

        <router-link :to="{ name: 'PettoHome' }">
          <h1 class="logo">
            petto
          </h1>
        </router-link>

        <div class="header-top">
          <!-- 메뉴추가 -->
          <ul id="menu">
              <li><v-btn plain color="white">MENU</v-btn>
                <ul>
                  <li><a href="/aboutPage">소개</a></li>
                  <li><a href="/abandonedAnimal/list/page=0&place=none&kind=none">유기동물</a></li>
                  <li><a href="/reportBoard">제보</a></li>
                  <li><a href="/voluntaryBoard">자원봉사</a></li>
                  <li><a href="/map">지도</a></li>
                </ul>
              </li>
            </ul>
            <!--  -->

          <v-tooltip bottom v-if="isLogin">

            <template v-slot:activator="{ on, attrs }">
            
            <div text v-on="on" v-bind="attrs" v-show="updatedNewsNum > 0" style="display: inline-block;">
              <alarm-dialog :session="session"/>
            </div>

            <v-btn text v-on="on" v-bind="attrs" v-show="updatedNewsNum == 0">
              <v-icon color="grey">
                add_alert
              </v-icon>
            </v-btn>

            </template>

            <span v-show="updatedNewsNum > 0">{{ updatedNewsNum }} 개의 최신 소식이 있어요!</span>

            <span v-show="updatedNewsNum == 0">아직 새로운 사항이 없습니다.</span>

          </v-tooltip>

          &emsp;

          <!-- <v-btn plain color="white" v-if="isLogin" @click="logout">
            LOGOUT
          </v-btn> -->

          <v-btn
            plain
            color="white"
            v-if="!isLogin"
            router
            :to="{ name: 'MemberLoginPage' }"
            >LOGIN
          </v-btn>

          <v-btn plain color="white" v-if="!isLogin" router :to="{ name: 'SignupPage' }">
            JOIN US
          </v-btn>


          <v-app-bar-nav-icon plain color="white" v-if="isLogin" @click="nav_drawer = !nav_drawer"></v-app-bar-nav-icon>
          <v-navigation-drawer app v-model="nav_drawer" temporary  v-if="this.auth == '개인'">
            <v-list nav dense>
                <v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
                    <v-list-item v-for="link in links" :key="link.name" router :to="link.route">
                        <v-list-item-action>
                            <v-icon left>{{ link.icon }}</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title v-if="link.name == 'logout'" @click="logout()">{{ link.text }}</v-list-item-title>
                            <v-list-item-title v-else>{{ link.text }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
          </v-navigation-drawer>

          <v-navigation-drawer app v-model="nav_drawer" temporary v-else>
            <v-list nav dense>
                <v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
                    <v-list-item v-for="admin in adminLinks" :key="admin.name" router :to="admin.route">
                        <v-list-item-action>
                            <v-icon left>{{ admin.icon }}</v-icon>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-list-item-title v-if="admin.text == '로그아웃'" @click="logout()">{{ admin.text }}</v-list-item-title>
                            <v-list-item-title v-else>{{ admin.text }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
          </v-navigation-drawer>


        </div>

        <!-- <div>
          <ul>
            <li><a href="#">소개</a></li>
            <li><a href="/abandonedAnimal/list/page=0&place=none&kind=none">유기동물</a></li>
            <li><a href="/reportBoard">제보</a></li>
            <li><a href="/voluntaryBoard">자원봉사</a></li>
            <li><a href="/map">지도</a></li>
          </ul>
        </div> -->
      </div>
      </div>

      <section>
        <div style="width: 100%; padding-top:6em;">
          <h3 style="text-align:center; margin:0%;">입양해주세요
            <v-btn outlined color="orange" route :to="'/abandonedAnimal/list/page=0&place=none&kind=none'" style="position:absolute; right:20%;">
              <v-icon>navigate_next</v-icon>MORE</v-btn>
          </h3>
        </div>

        <v-row justify="center">
          <v-container class="justify center" style="margin-top:0%; margin-bottom:0%;">
              <div class="container">
                  <div id="animal">
                        <div v-for="animal in olderList" :key="animal.notice_no">
                            <v-card height="200" class="grow">
                              <img :src="animal.image" width="230" height="230" @click="toDetailPage(animal.id)"/>
                            </v-card>
                        </div>
                  </div>
              </div>
          </v-container>
        </v-row>
      </section>


        <section>
          <div class="container2">

            <statistics/>
            
          </div>
        </section>
        <section>
          <div class="container3">
            <div id="youtube">
              <h3>petto 유튜브</h3>
                  <ul>
                      <li><iframe src="https://www.youtube.com/embed/BtjKQUW8Eg0?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/3HimGmjD73k?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/vrPm4SFRviY?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/R-b2LwMCYC8?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/EVrx-UfXS8o?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/CMI2TdvLMBE?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/nBdpJIN9QEU?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/4on_v7ZebSw?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/0anYp7gZJ3w?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/wy2qwD_xx9k?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                      <li><iframe src="https://www.youtube.com/embed/qllzIfMSMMc?list=PLPXeAXyrXJPBzLIA0cCdHNUG-XfOZyMqV"></iframe></li>
                  </ul>
            </div>
          </div>
        </section>

          <div>
            <div>
                <v-card v-if="layers">
                  <div id="layer">
                    <img src="@/assets/img/popup.png" alt="자원봉사" />
                    <v-btn class="close" @click="Close()" small icon
                      ><v-icon>close</v-icon></v-btn
                    >
                    <v-btn
                      class="img-link"
                      color="#feecae"
                      depressed
                      width="200"
                      height="40"
                      >자세히보기</v-btn>
                    <v-btn class="today-close" @click="TodayClose()" plain>오늘 하루 보지 않기</v-btn>
                  </div>
                </v-card>
              </div>
          </div>
  </div> 
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Statistics from "@/components/crawling/Statistics.vue";
import AlarmDialog from '../components/dialogue/AlarmDialog.vue';

export default {
  components: {
    Statistics,
    AlarmDialog
  },
  data() {
    return {
      layers: false,
      isLogin: false,
      nav_drawer: false,
      group: false,
      auth: this.$cookies.get("user").auth,
      links: [
                { icon: 'account_circle', text: '내 정보', name: 'my_info', route: '/myProfile' },
                { icon: 'favorite', text: '찜한 동물 리스트', name: 'my_favorite', route: '/myLikedAnimals' },
                { icon: 'keyboard', text: '내 게시물', name: 'my_board', route: '/myBoard' },
                { icon: 'help_outline', text: '1:1 문의', name: 'QnA', route: '/MyQna' },
                { icon: 'input', text: '로그아웃', name: 'logout'}
      ],
      adminLinks: [
          { icon: 'people', text: '회원관리', name: 'my_board', route: '/admin' },
          { icon: 'help_outline', text: '1:1 문의관리', name: 'QnA', route: '/qnalist' },
          { icon: 'input', text: '로그아웃', name: 'logout'}
      ]
    }
  },
  methods: {
    Close() {
      this.layers = false;
    },
    TodayClose() {
      this.$store.dispatch("TodayPopUp");
      this.layers = false;
    },
    logout() {
      this.$cookies.remove("user");
      this.isLogin = false;
      this.$store.state.session = null;
      this.$router.push({ name: 'PettoHome' })
      alert('로그아웃 되었습니다.')
    },
    ...mapActions(['fetchOlderAnimalList']),
      toDetailPage(id) {
      this.$router.push({
        name: 'AnimalDetailPage',
        params: { "id": id }
      })
    }
  },
  mounted() {
    // console.log(this.$cookies.isKey("TodayPopUpClose"));
    if (this.$cookies.isKey("TodayPopUpClose") == false) {
      this.layers = true;
    }
    if( this.$cookies.isKey("user") == true) {
      // this.auth = this.$cookies.get("user").auth
      this.$store.state.session = this.$cookies.get("user");
      if (this.$store.state.session != null) {
        this.isLogin = true;
    }
    }
    this.fetchOlderAnimalList()
  },
  computed: {
      ...mapState(['olderList', 'session']),

      updatedNewsNum() {
        return this.session.updateAlarmList.length
      }
  },
  watch: {
     group () {
       this.nav_drawer = false
     }
   }
};
</script>

<style lang="scss" scoped>

@font-face {
  font-family: "Manse";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Manse.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "GowunDodum-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.video-main{ 
position: relative;
 display: flex;
width: 100%;
}

#main {
  position: relative;
  min-height: 100%;
  display: flex;
  color: white;
  background: {
    image: linear-gradient(
        to bottom,
        rgba(172, 172, 172, 0.014) 0%,
        rgba(0, 0, 0, 0.6) 100%
      );
    size: cover;
  }
}

// #main {
//   position: relative;
//   min-height: 100vh;
//   display: flex;
//   padding: 12rem 2rem;
//   color: white;
//   background: {
//     image: linear-gradient(
//         to bottom,
//         rgba(172, 172, 172, 0.014) 0%,
//         rgba(0, 0, 0, 0.6) 100%
//       ),
//       url("https://images.pexels.com/photos/4641866/pexels-photo-4641866.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//     blend-mode: multiply;
//     size: cover;
//   }
// }
#header {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  padding: 15px 60px;
  justify-content: space-between;
  align-items: center;
}
.logo {
  color: #fff;
  letter-spacing: 3px;
}
// #header ul {
//   display: flex;
//   justify-content: center;
//   align-items: center;
// }
// #header ul li {
//   list-style: none;
//   margin-left: 15px;
// }
// #header ul li a {
//   text-decoration: none;
//   padding: 6px 12px;
//   color: white;
//   position: relative;
//   font-family: "GowunDodum-Regular";
//   font-size: 21px;
//   margin-left: 30px;
//   margin-right: 30px;
// }
// #header ul li a::after {
//   content: "";
//   position: absolute;
//   bottom: 0;
//   left: 50%;
//   transform: translateX(-50%);
//   width: 0;
//   height: 4px;
//   background: #f7b43e;
//   transition: all 0.5s ease-out;
// }
// #header ul li a:hover::after {
//   width: 100%;
// }
h1 {
  // font-family: "Manse";
  font-family: 'WandohopeB';
  font-size: 6vw;
  text-shadow: 2px 2px 2px gray;
}
.header-top {
  position: absolute;
  top: 1em;
  list-style: none;
  right: 2em;
}
//  팝업
#layer {
  position: fixed;
  left: 50px;
  top: 50px;
  width: 450px;
  border: 3px solid #fff;
  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.4);
}
#layer img {
  width: 100%;
  display: block;
}
#layer .close {
  position: absolute;
  right: 0.2em;
  top: 3px;
  font-size: 20px;
}
#layer .today-close {
  position: absolute;
  right: 0.2em;
  bottom: 0px;
  font-size: 12px;
}
#layer .img-link {
  position: absolute;
  right: 10em;
  bottom: 3em;
  font-size: 12px;
}

// 유기동물 api 오래된 순

.container{
  width: 90%;
  margin-top: 5%;
  margin-bottom: 10%;
  position: relative;
}

#animal{
  width: 100%;
}

#animal div{
  display:inline-block;
  position: relative;
  margin: 0px 0px 13px 13px;
}

.grow {
	transition-property: transform;
	&:hover {
		transform: scale(1.1);
	}
}

// 통계

.container2{
  width: 100%;
  position: relative;
  margin-bottom: 5%;
}

#stats{
  margin-left: -2vw;
  margin-right: -2vw;
  padding: 2% 8em 10% 0%;
  background-color: #f7b43e;
  display: inline-block;
}

// 유튜브

.container3{
  width: 100%;
  position: relative;
  margin-top:5%;
  margin-bottom: 5%;
}

#youtube{
  padding: 0% 5em 5% 5em;
  max-width: 100vw;
}

#youtube ul {
  white-space:nowrap;
  overflow-x: auto;
  text-align:center;
  overflow-x: scroll;
  width: 100%;
}


#youtube ul::-webkit-scrollbar{
    width: 1em;
    height: 0.8em;
}


#youtube ul::-webkit-scrollbar-thumb{
    height: 10%;
    background-color:#f7b43e;
    border-radius: 10px;
}


#youtube ul li{
    margin-left: 15px;
    display:inline-block;

    position: relative;
    margin-right: 10px;
    font-size: 20px;
    cursor: pointer;
    transform-origin: center left;

}

#youtube ul li iframe{
    text-decoration:none;
    color:inherit;
}

h3{
    font-size: 2vw;
    color: black;
    font-family: "GowunDodum-Regular";
    margin: 0% 0% 1% 1%;
}


// 
@font-face {
    font-family: 'WandohopeB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/WandohopeB.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
h4{
  font-size:4.5vw;
  font-family: 'WandohopeB';
}
//
.fade-in-box {
  margin-top:25%;
  display: inline-block;
  color:white;
  // padding: 10px;
  animation: fadein 10s;
  -moz-animation: fadein 10s; /* Firefox */
  -webkit-animation: fadein 10s; /* Safari and Chrome */
  -o-animation: fadein 10s; 
}
@keyframes fadein {
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-moz-keyframes fadein { /* Firefox */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-webkit-keyframes fadein { /* Safari and Chrome */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-o-keyframes fadein { /* Opera */
    from {
        opacity:0;
    }
    to {
        opacity: 1;
    }
}
//

.main-text{
    position: absolute;
    right: 0%;
    top:10%;
    width: 100%;
    min-height: 100vh;
    padding: 4vw 4vw 0vw 4vw;
    display: flex;
}

  #menu {
    position: relative;
    float: left;
  }

  #menu, #menu ul {
    list-style: none;
  }

  #menu a {
    display: block;
    position: relative;
    padding: .7vw;
    text-decoration: none;
    color: rgb(3, 3, 3);
    line-height: 1;
    font-size: 1vw;
    letter-spacing: -.05em;
    background: transparent;    
    text-shadow: 0 1px 1px rgba(255,255,255,.9);
    transition: all .25s ease-in-out;
  
  }

  #menu > li:hover > a {
    background: rgb(255, 255, 255);
    color: #f7b43e;
    text-shadow: none;
  }

  #menu li ul  {
    position: absolute;
    left: 0;
    z-index: 1;
    width: 130px;
    padding: 0;
    opacity: 0;
    visibility: hidden;
    border-bottom-left-radius: 4px;
    border-bottom-right-radius: 4px;
    background: transparent;
    overflow: hidden;
    transform-origin: 50% 0%;
  }


  #menu li:hover ul {
    
    padding: 15px 0;
    background: rgb(255, 255, 255);
    opacity: 1;
    visibility: visible;
    box-shadow: 1px 1px 7px rgba(0,0,0,.5);
    animation-name: swingdown;
    animation-duration: 1s;
    animation-timing-function: ease;

  }

  @keyframes swingdown {
    0% {
      opacity: .99999;
      transform: rotateX(90deg);
    }

    30% {     
      transform: rotateX(-20deg) rotateY(5deg);
      animation-timing-function: ease-in-out;
    }

    65% {
      transform: rotateX(20deg) rotateY(-3deg);
      animation-timing-function: ease-in-out;
    }

    100% {
      transform: rotateX(0);
      animation-timing-function: ease-in-out;
    }
  }

  #menu li li a {
    padding-left: 15px;
    font-weight: 400;
    text-shadow: none;
    border-top: dotted 1px transparent;
    border-bottom: dotted 1px transparent;
    transition: all .15s linear;
  }

  #menu li li a:hover {
    color: #f7b43e;
    border-top: dotted 1px rgba(255,255,255,.15);
    border-bottom: dotted 1px rgba(255,255,255,.15);
    background: rgba(252, 214, 0, 0.02);
  }
</style>