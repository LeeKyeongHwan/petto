<template>
    <v-container style="margin:350px;">
    <aside class="profile-card" >

    <header>
        <span style="float: right;">
        <v-tooltip bottom>

          <template v-slot:activator="{ on, attrs }">

          <v-btn text color="#FFB300"  v-on="on" v-bind="attrs"  href="passwordCheckPage">
            <v-icon>
              settings
            </v-icon>
          </v-btn>

          </template>

          <span>내 정보 수정</span>

        </v-tooltip>

        <v-tooltip bottom>

          <template v-slot:activator="{ on, attrs }">

          <v-btn text color="#FFB300"  v-on="on" v-bind="attrs"  href="passwordCheckWithdrawalPage">
            <v-icon>
              delete
            </v-icon>
          </v-btn>

          </template>

          <span>탈퇴</span>

        </v-tooltip>
      </span>
        <a style="margin: 25px; margin-left: 160px;">
            <img src="data:image/jpeg;base64,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" />
        </a>


    </header>

    <!-- bit of a bio; who are you? -->
   <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
        <div class="row">
            <div class="col-md-2">
                <label>아이디</label>
            </div>
            <div class="col-md-2">
                <p>{{userInfo.id}}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>이름</label>
            </div>
            <div class="col-md-2">
                <p>{{userInfo.name}}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>이메일</label>
            </div>
            <div class="col-md-2">
                <p>{{userInfo.email}}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>휴대번호</label>
            </div>
            <div class="col-md-2">
                <p>{{userInfo.phoneNumber}}</p>
            </div>
        </div>
        <div class="row">
           <div class="col-md-2">
                <label>생년월일</label>
            </div>
           <div class="col-md-2">
                <p>{{userInfo.birthday}}</p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>닉네임</label>
            </div>
            <div class="col-md-2">
                <p>{{userInfo.nickname}}</p>
            </div>
        </div>
    </div>

    </aside>
    </v-container>

</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
    name: 'MyProfile2',
    methods: {
        ...mapActions(['fetchUserInfo']),
    },
    mounted() {
        if(this.$cookies.isKey("user")) {

            this.$store.state.session = this.$cookies.get("user");

            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)

                const userNo = this.$store.state.session.memberNo
                this.fetchUserInfo(userNo)

                this.$store.state.isLoggedIn = true;
            }
        }

        document.querySelectorAll('footer')[0].style.padding = '0px';
        document.querySelectorAll('footer')[0].style.height = '150px';
        document.querySelectorAll('.v-main__wrap')[0].style.background = "#ffe259;";
        document.querySelectorAll('.v-main__wrap')[0].style.background = " -webkit-linear-gradient(to right, #ffe259, #ffa751)";
        document.querySelectorAll('.v-main__wrap')[0].style.background = "linear-gradient(to right, #ffe259, #ffa751)";

    },
    computed: {
        ...mapState(['userInfo', 'fetchAlarmList', 'session'])
    }
}
</script>

<style lang="scss" scoped>
#main{
    position: relative;
    min-height: 100vh;
    display: flex;
    padding: 12rem 2rem;
    color: white;
    background-image: linear-gradient(to bottom, rgba(172, 172, 172, 0.014) 0%, rgba(0, 0, 0, 0.6) 100%), url(https://images.pexels.com/photos/4641866/pexels-photo-4641866.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940);
    background-blend-mode: multiply;
    background-size: cover;
}
html {
    height: 100%;
}
body {
  background: #bcdee7 url("http://victory-design.ru/sandbox/codepen/profile_card/bg.png") no-repeat center center fixed;
    background-size: 120% auto;
    position: fixed;
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 100%;
  font: normal 14px/1.618em "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
}
body:before {
    content: "";
    height: 0px;
    padding: 0px;
    border: 110em solid #313440;
  position: absolute;
  left: 50%;
  top: 100%;
    z-index: 2;
    display: block;
    -webkit-border-radius: 50%;
    border-radius: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
    -webkit-animation: puff_portrait 0.5s 1.8s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, borderRadius 0.2s 2.3s linear forwards;
    animation: puff_portrait 0.5s 1.8s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, borderRadius 0.2s 2.3s linear forwards;
}

.col-md-2{
    margin-left:140px;
}
h1, h2 {
    font-weight: 400;
    margin: 0px 0px 5px 0px;
}
h1 {
    font-size: 24px;
}
.profile-card header h1 {
    color: #FFB300;
}
h2 {
    font-size: 16px;
}
p {
    margin: 0px;
}

.profile-card {
  background: #FFB300;
  width: 56px;
  height: 56px;
  position: absolute;
  left: 50%;
  top: 50%;
    z-index: 2;
  overflow: hidden;
    opacity: 0;
    margin-top: 70px;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  -webkit-border-radius: 50%;
  border-radius: 50%;
  -webkit-box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16), 0px 3px 6px rgba(0, 0, 0, 0.23);
  box-shadow: 0px 3px 6px rgba(0 ,0, 0, 0.16), 0px 3px 6px rgba(0, 0, 0, 0.23);
    -webkit-animation: init 0.5s 0.2s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, moveDown 1s 0.8s cubic-bezier(0.6, -0.28, 0.735, 0.045) forwards, moveUp 1s 1.8s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards, materia_landscape 0.5s 2.7s cubic-bezier(0.86, 0, 0.07, 1) forwards;
    animation: init 0.5s 0.2s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, moveDown 1s 0.8s cubic-bezier(0.6, -0.28, 0.735, 0.045) forwards, moveUp 1s 1.8s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards, materia_landscape 0.5s 2.7s cubic-bezier(0.86, 0, 0.07, 1) forwards;
}
.profile-card header {
    width: 179px;
    height: 280px;
    padding: 40px 20px 30px 20px;
    display: inline-block;
    float: left;
    border-right: 2px dashed #EEEEEE;
  background: #FFFFFF;
    color: #000000;
    margin-top: 50px;
    opacity: 0;
    text-align: center;
    -webkit-animation: moveIn 1s 3.1s ease forwards;
    animation: moveIn 1s 3.1s ease forwards;
}

.profile-card header a {
    display: inline-block;
    text-align: center;
    position: relative;

}
.profile-card header a > img {
    width: 120px;
    max-width: 100%;
    -webkit-border-radius: 50%;
    border-radius: 50%;
    -webkit-transition: -webkit-box-shadow 0.3s ease;
    transition: box-shadow 0.3s ease;
    -webkit-box-shadow: 0px 0px 0px 8px rgba(0, 0, 0, 0.06);
    box-shadow: 0px 0px 0px 8px rgba(0, 0, 0, 0.06);
}
.profile-card header a:hover > img {
    -webkit-box-shadow: 0px 0px 0px 12px rgba(0, 0, 0, 0.1);
    box-shadow: 0px 0px 0px 12px rgba(0, 0, 0, 0.1);
}
.profile-card .profile-bio {
    width: 175px;
    height: 180px;
    display: inline-block;
    float: right;
    padding: 50px 20px 30px 20px;
  background: #FFFFFF;
    color: #333333;
    margin-top: 50px;
    text-align: center;
    opacity: 0;
    -webkit-animation: moveIn 1s 3.1s ease forwards;
    animation: moveIn 1s 3.1s ease forwards;
}



@media screen and (min-aspect-ratio: 4/3) {
    body {
        background-size: 100% auto;
    }
    body:before {
        width: 0px;
    }
    @-webkit-keyframes puff {
        0% {
            top: 100%;
            width: 0px;
            padding-bottom: 0px;
        }
      100% {
            top: 50%;
            width: 100%;
            padding-bottom: 100%;
        }
    }
    @keyframes puff {
        0% {
            top: 100%;
            width: 0px;
            padding-bottom: 0px;
        }
      100% {
            top: 50%;
            width: 100%;
            padding-bottom: 100%;
        }
    }
}
@media screen and (min-height: 480px) {
  .profile-card {
    -webkit-animation: init 0.5s 0.2s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, moveDown 1s 0.8s cubic-bezier(0.6, -0.28, 0.735, 0.045) forwards, moveUp 1s 1.8s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards, materia_portrait 0.5s 2.7s cubic-bezier(0.86, 0, 0.07, 1) forwards;
    animation: init 0.5s 0.2s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, moveDown 1s 0.8s cubic-bezier(0.6, -0.28, 0.735, 0.045) forwards, moveUp 1s 1.8s cubic-bezier(0.175, 0.885, 0.32, 1.275) forwards, materia_portrait 0.5s 2.7s cubic-bezier(0.86, 0, 0.07, 1) forwards;
  }
  .profile-card header {
        width: auto;
        height: auto;
        padding: 30px 20px;
        display: block;
        float: none;
        border-right: none;
    }
    .profile-card .profile-bio {
        width: auto;
        height: auto;
        padding: 15px 20px 30px 20px;
        display: block;
        float: none;
    }
    .profile-social-links {
        width: 100%;
        display: block;
        float: none;
    }
}
@media screen and (min-aspect-ratio: 4/3) {
    body {
        background-size: 100% auto;
    }
    body:before {
        width: 0px;
    -webkit-animation: puff_landscape 0.5s 1.8s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, borderRadius 0.2s 2.3s linear forwards;
    animation: puff_landscape 0.5s 1.8s cubic-bezier(0.55, 0.055, 0.675, 0.19) forwards, borderRadius 0.2s 2.3s linear forwards;
  }
}
@-webkit-keyframes init {
    0% {
      width: 0px;
      height: 0px;
    }
  100% {
        width: 56px;
        height: 56px;
        margin-top: 0px;
        opacity: 1;
    }
}
@keyframes init {
    0% {
      width: 0px;
      height: 0px;
    }
  100% {
        width: 56px;
        height: 56px;
        margin-top: 0px;
        opacity: 1;
    }
}
@-webkit-keyframes puff_portrait {
    0% {
        top: 100%;
        height: 0px;
        padding: 0px;
    }
  100% {
        top: 50%;
        height: 100%;
        padding: 0px 100%;
    }
}
@keyframes puff_portrait {
    0% {
        top: 100%;
        height: 0px;
        padding: 0px;
    }
  100% {
        top: 50%;
        height: 100%;
        padding: 0px 100%;
    }
}
@-webkit-keyframes puff_landscape {
  0% {
    top: 100%;
    width: 0px;
    padding-bottom: 0px;
  }
  100% {
    top: 50%;
    width: 100%;
    padding-bottom: 100%;
  }
}
@keyframes puff_landscape {
  0% {
    top: 100%;
    width: 0px;
    padding-bottom: 0px;
  }
  100% {
    top: 50%;
    width: 100%;
    padding-bottom: 100%;
  }
}
@-webkit-keyframes borderRadius {
    0% {
        -webkit-border-radius: 50%;
    }
  100% {
        -webkit-border-radius: 0px;
    }
}
@keyframes borderRadius {
    0% {
        -webkit-border-radius: 50%;
    }
  100% {
        border-radius: 0px;
    }
}
@-webkit-keyframes moveDown {
    0% {
        top: 50%;
    }
  50% {
     top: 40%;
    }
    100% {
       top: 100%;
    }
}
@keyframes moveDown {
    0% {
        top: 50%;
    }
  50% {
     top: 40%;
    }
    100% {
       top: 100%;
    }
}
@-webkit-keyframes moveUp {
    0% {
        background: #FFB300;
        top: 100%;
    }
  50% {
     top: 40%;
    }
    100% {
       top: 50%;
       background: #E0E0E0;
    }
}
@keyframes moveUp {
    0% {
        background: #FFB300;
        top: 100%;
    }
  50% {
     top: 40%;
    }
    100% {
       top: 50%;
       background: #E0E0E0;
    }
}
@-webkit-keyframes materia_landscape {
    0% {
        background: #E0E0E0;
    }
    50% {
        -webkit-border-radius: 4px;
    }
    100% {
        width: 440px;
        height: 280px;
        background: #FFFFFF;
        -webkit-border-radius: 4px;
    }
}
@keyframes materia_landscape {
    0% {
        background: #E0E0E0;
    }
    50% {
        border-radius: 4px;
    }
    100% {
        width: 440px;
        height: 280px;
        background: #FFFFFF;
        border-radius: 4px;
    }
}
@-webkit-keyframes materia_portrait {
  0% {
    background: #E0E0E0;
  }
  50% {
    -webkit-border-radius: 4px;
  }
  100% {
    width: 700px;
    height: 560px;
    background: #facb49;
    -webkit-border-radius: 4px;
  }
}
@keyframes materia_portrait {
  0% {
    background: #E0E0E0;
  }
  50% {
    border-radius: 4px;
  }
  100% {
    width: 700px;
    height: 560px;
    background: #FFFFFF;
    border-radius: 4px;
  }
}
@-webkit-keyframes moveIn {
    0% {
        margin-top: 50px;
        opacity: 0;
    }
  100% {
        opacity: 1;
        margin-top: -20px;
    }
}
@keyframes moveIn {
    0% {
        margin-top: 50px;
        opacity: 0;
    }
  100% {
        opacity: 1;
        margin-top: -20px;
    }
}
@-webkit-keyframes scaleIn {
    0% {
        -webkit-transform: scale(0);
    }
  100% {
        -webkit-transform: scale(1);
    }
}
@keyframes scaleIn {
    0% {
        transform: scale(0);
    }
  100% {
        transform: scale(1);
    }
}
@-webkit-keyframes ripple {
    0% {
        transform: scale3d(0, 0, 0);
    }
    50%, 100% {
        -webkit-transform: scale3d(1, 1, 1);
    }
    100% {
        opacity: 0;
    }
}
@keyframes ripple {
    0% {
        transform: scale3d(0, 0, 0);
    }
    50%, 100% {
        transform: scale3d(1, 1, 1);
    }
    100% {
        opacity: 0;
    }
}


</style>