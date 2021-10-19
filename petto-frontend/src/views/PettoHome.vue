<template>
  <div id="main">
    <div id="header">
      <a href="/pettohome" class="logo"><h1>petto</h1></a>
      <div class="header-top">
        <v-btn
          plain
          color="white"
          v-if="!isLogin"
          router
          :to="{ name: 'MemberLoginPage' }"
          >LOGIN</v-btn
        >
        <v-btn plain color="white" v-if="isLogin" @click="logout">LOGOUT</v-btn>
        <v-btn plain color="white" router :to="{ name: 'SignupPage' }"
          >JOIN US</v-btn
        >
      </div>
      <div>
        <ul>
          <li><a href="#">소개</a></li>
          <li><a href="#">유기동물</a></li>
          <li><a href="#">제보</a></li>
          <li><a href="#">자원봉사</a></li>
          <li><a href="#">Q&A</a></li>
        </ul>
      </div>
    </div>
    <div>
      <v-card v-if="layers">
        <div id="layer">
          <!-- <img src="https://d2v80xjmx68n4w.cloudfront.net/gigs/jZCCL1620102805.png" alt="자원봉사" height="600"> -->
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
            >자세히보기</v-btn
          >
          <v-btn class="today-close" @click="TodayClose()" plain
            >오늘 하루 보지 않기</v-btn
          >
        </div>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      layers: false,
      isLogin: false,
    };
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
    },
  },
  mounted() {
    console.log(this.$cookies.isKey("TodayPopUpClose"));
    if (this.$cookies.isKey("TodayPopUpClose") === false) {
      this.layers = true;
    }
    this.$store.state.session = this.$cookies.get("user");
    if (this.$store.state.session != null) {
      this.isLogin = true;
    }
  },
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
  min-height: 100vh;
  display: flex;
  padding: 12rem 2rem;
  color: white;
  background: {
    image: linear-gradient(
        to bottom,
        rgba(172, 172, 172, 0.014) 0%,
        rgba(0, 0, 0, 0.6) 100%
      ),
      url("https://images.pexels.com/photos/4641866/pexels-photo-4641866.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
    blend-mode: multiply;
    size: cover;
  }
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

.logo {
  color: #fff;
  letter-spacing: 3px;
}

#header ul {
  display: flex;
  justify-content: center;
  align-items: center;
}

#header ul li {
  list-style: none;
  margin-left: 15px;
}

#header ul li a {
  text-decoration: none;
  padding: 6px 12px;
  color: white;
  position: relative;
  font-family: "GowunDodum-Regular";
  font-size: 21px;
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

h1 {
  font-family: "Manse";
  font-size: 80px;
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
</style>
