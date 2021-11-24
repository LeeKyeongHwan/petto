<template>
  <div>
    <div id="main">
      <div id="header">

        <div style="width: 300px;">
          <router-link :to="{ name: 'PettoHome' }">
            <h1 class="logo">
              petto
            </h1>
          </router-link>
        </div>

        <div class="header-top">
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

          <v-btn plain color="white" v-if="isLogin" @click="logout">
            LOGOUT
          </v-btn>

          <v-btn
            plain
            color="orange"
            v-if="!isLogin"
            router
            :to="{ name: 'MemberLoginPage' }"
            >LOGIN
          </v-btn>

          <v-btn plain color="orange" v-if="!isLogin" router :to="{ name: 'SignupPage' }">
            JOIN US
          </v-btn>


          <v-app-bar-nav-icon plain color="orange" v-if="isLogin" @click="nav_drawer = !nav_drawer"></v-app-bar-nav-icon>
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

        <div>
          <ul>
            <li><a href="/aboutPage">소개</a></li>
            <li><a href="/abandonedAnimal/list/page=0&place=none&kind=none">유기동물</a></li>
            <li><a href="/reportBoard">제보</a></li>
            <li><a href="/voluntaryBoard">자원봉사</a></li>
            <li><a href="/map">지도</a></li>
          </ul>
        </div>
      </div>
      </div>
  </div> 
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
      auth: '',
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
    logout() {
      this.$cookies.remove("user");
      this.isLogin = false;
      this.$store.state.session = null;
      this.$router.push({ name: 'PettoHome' })
      alert('로그아웃 되었습니다.')
    }
  },
  mounted() {
    if( this.$cookies.isKey("user") == true) {
      this.$store.state.session = this.$cookies.get("user");
      this.auth = this.$cookies.get("user").auth
      if (this.$store.state.session != null) {
        this.isLogin = true;
    }
    }
  },
  computed: {
      ...mapState(['session']),
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


#main {
  position: relative;
  min-height: 100%;
  display: flex;
  color: white;
  margin-bottom: 11%;
  background-color: rgb(255, 249, 240);
  border-bottom: dotted 6px rgb(243, 222, 189);
}

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
  justify-content: center;
  align-items: center;
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
  font-size: 1.5vw;
  margin-left: 30px;
  margin-right: 30px;
}
#header ul li a::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 0;
  height: 4px;
  background: #f7b43e;
  transition: all 0.5s ease-out;
}
#header ul li a:hover::after {
  width: 100%;
}

.logo {
  color: rgb(248, 201, 131);
  letter-spacing: 3px;
}
@font-face {
    font-family: 'WandohopeB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/WandohopeB.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

h1 {
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
</style>