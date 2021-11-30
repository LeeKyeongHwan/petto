<template>
    <v-card flat width="100%" tile>
 
      <v-toolbar height="90">
        <div>
          <v-btn  text plain route :to="{ name: 'PettoHome' }" height="90">
            <h1>
              petto
            </h1>
          </v-btn>
        </div>

        <v-spacer></v-spacer>

        <div id="header" class="main-menu">
        <v-spacer></v-spacer>
            <ul>
              <li><a href="/aboutPage">소개</a></li>
              <li><a href="/abandonedAnimal/list/page=0&place=none&kind=none">유기동물</a></li>
              <li><a href="/reportBoard">제보</a></li>
              <li><a href="/voluntaryBoard">자원봉사</a></li>
              <li><a href="/map">지도</a></li>
            </ul>
          </div>

          <div id="header">
            <ul>
              <li><a href="/memberLoginPage" style="font-size:0.8vw; margin-right:0px;" v-if="!isLoggedIn">LOGIN</a></li>
              <li><a href="/signupPage" style="font-size:0.8vw; margin-right:20px;" v-if="!isLoggedIn">JOIN US</a></li>
              <!-- <li><a href="#" style="font-size:0.8vw; margin-right:20px;" v-if="isLogin" @click="logout()">LOGOUT</a></li> -->
            </ul>
          </div>

          <div id="header" v-if="isLoggedIn">
            <ul v-if="this.auth == '개인'">
              <li><a href="/myProfile" style="font-size:0.8vw; margin-right:0px;">내정보</a></li>
              <li><a href="/myLikedAnimals" style="font-size:0.8vw; margin-right:20px;" >찜리스트</a></li>
              <li><a href="/myBoard" style="font-size:0.8vw; margin-right:20px;">내 게시물</a></li>
              <li><a href="/MyQna" style="font-size:0.8vw; margin-right:20px;">문의</a></li>
              <li><a href="#" style="font-size:0.8vw; margin-right:20px;" @click="logout()">LOGOUT</a></li>
            </ul>

            <ul v-else-if="this.auth == '관리자'">
              <li><a href="/admin" style="font-size:0.8vw; margin-right:0px;">회원관리</a></li>
              <li><a href="/qnalist" style="font-size:0.8vw; margin-right:20px;">문의관리</a></li>
              <li><a href="#" style="font-size:0.8vw; margin-right:20px;" @click="logout()">LOGOUT</a></li>
            </ul>
          </div>

          <v-tooltip bottom v-if="isLoggedIn">
            <template v-slot:activator="{ on, attrs }">
              <div text v-on="on" v-bind="attrs" v-show="updatedNewsNum > 0" style="display: inline-block;">
                <alarm-dialog :updateAlarmList="updateAlarmList"/>
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
              

          <!-- <v-app-bar-nav-icon plain color="orange" v-if="isLogin" @click="nav_drawer = !nav_drawer"></v-app-bar-nav-icon>
            <v-navigation-drawer app v-model="nav_drawer" temporary  v-if="this.auth == '개인'">
              <v-list nav dense>
                  <v-list-item-group v-model="group" active-class="deep-purple--text text--accent-4">
                      <v-list-item v-for="link in links" :key="link.name" router :to="link.route">
                          <v-list-item-action>
                              <v-icon left>{{ link.icon }}</v-icon>
                          </v-list-item-action>
                          <v-list-item-content>
                              <v-list-item-title>{{ link.text }}</v-list-item-title>
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
                              <v-list-item-title>{{ admin.text }}</v-list-item-title>
                          </v-list-item-content>
                      </v-list-item>
                  </v-list-item-group>
              </v-list>
            </v-navigation-drawer> -->
      </v-toolbar>
  </v-card>     
</template>

<script>
import { mapState } from 'vuex';
import AlarmDialog from '../components/dialogue/AlarmDialog.vue';

export default {
  components: {
    AlarmDialog
  },
  data() {
    return {
      isLogin: false,
      nav_drawer: false,
      group: false,
      //auth: this.session.auth,
      //access: this.$cookies.isKey("user"),
      links: [
          { icon: 'account_circle', text: '내 정보', name: 'my_info', route: '/myProfile' },
          { icon: 'favorite', text: '찜한 동물 리스트', name: 'my_favorite', route: '/myLikedAnimals' },
          { icon: 'keyboard', text: '내 게시물', name: 'my_board', route: '/myBoard' },
          { icon: 'help_outline', text: '1:1 문의', name: 'QnA', route: '/MyQna' },
      ],
      adminLinks: [
          { icon: 'people', text: '회원관리', name: 'my_board', route: '/admin' },
          { icon: 'help_outline', text: '1:1 문의관리', name: 'QnA', route: '/qnalist' },
      ]
    }
  },
  methods: {
    logout() {
      this.$cookies.remove("user");
      this.isLogin = false;
      this.$store.state.session = null;
      this.$router.push({ name: 'PettoHome' })
      alert('로그아웃 되었습니다.')
    }
  },
  // mounted() {
  //   if(this.$cookies.isKey("user")) {
  //     this.auth = this.$cookies.get("user").auth
  //     this.$store.state.session = this.$cookies.get("user");
  //     if(this.$store.state.session != null) {
  //       this.$store.dispatch('fetchAlarmList', this.session.id)
  //       this.isLogin = true;
  //     }
  //   } else {
  //     this.auth = '비회원'
  //   }
  // },
  computed: {
      ...mapState(['updateAlarmList', 'fetchAlarmList', 'session']),
      updatedNewsNum() {
        return this.updateAlarmList.length
      },

      isLoggedIn() {
        if(this.session != null) return true
        return false
      },
      auth() {
        return this.session.auth;
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

#header {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  padding: 15px 60px;
  justify-content: space-between;
  align-items: center;
}

#header ul {
  display: flex;
  justify-content: right;
  align-items: right;
  margin-right: 1%;
}
#header ul li {
  list-style: none;
}
#header ul li a {
  text-decoration: none;
  padding: 6px 12px;
  color: rgb(100, 93, 93);
  position: relative;
  font-family: "GowunDodum-Regular";
  font-size: 1.2vw;
  margin-left: 10px;
  margin-right: 20px;
}

.main-menu{
  margin-top:3vh;
}


h1 {
font-family: 'WandohopeB';
  font-size: 3vw;
  margin:0vw;
  letter-spacing: 4px;
  color: black;
  /* text-shadow: 2px 2px 2px gray; */
}

.header-top {
  position: absolute;
  top: 1em;
  list-style: none;
  right: 2em;
}


</style>