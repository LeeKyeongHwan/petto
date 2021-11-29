<template>
  <form class="login-screen" >
    <div class="login-form">
      <div class="form-group">

        <input
          id="password"
          class="login-password"
          type="password"
          placeholder="Password"
          v-model="password"
        />
        <label for="password">
          <svg>
            <use xlink:href="#padlock" />
          </svg>
        </label>
      </div>
      <div class="form-group">
          
        <input style="border:0 padding-top:2; text-align:center;" class="login-submit" @click="chkPassword" value="확인" />
      </div>

      <svg>
        <symbol id="padlock" viewBox="0 0 1792 1792">
          <path
            d="M640 768h512V576q0-106-75-181t-181-75-181 75-75 181v192zm832 96v576q0 40-28 68t-68 28H416q-40 0-68-28t-28-68V864q0-40 28-68t68-28h32V576q0-184 132-316t316-132 316 132 132 316v192h32q40 0 68 28t28 68z"
          />
        </symbol>
      </svg>
    </div>
  </form>
</template>

<script>
import axios from 'axios'
export default {
    name: 'FindIdAndPwPage',
    data() {
        return {
            id: '',
            password: '',
        }
    },
    methods: {
        chkPassword() {
         let id = this.$store.state.session.id
         let password = this.password
         
         axios
         .post("http://localhost:8888/petto/member/passwordChk",{ id, password })
         .then((res) => {
                if(res.data == true) {
                        
                const memberNo = this.$store.state.session.memberNo
                axios.delete(`http://localhost:8888/petto/member/${memberNo}`)
                .then(() => {
                    alert('정상적으로 탈퇴 처리되었습니다.')
                    this.isLogin = false
                    this.$router.push({ name: 'PettoHome' })
                    this.$store.state.session = null
                    this.$cookies.remove("user")                    
                })
               .catch(res => {
                    alert(res.response.data.message)
                })
               
                }
                else{
                    alert('비밀번호를 다시 입력하세요' )
                }
         })
        },
    },
    mounted() {
        if(this.$cookies.get("user").id) {
        this.$store.state.session = this.$cookies.get("user")
        }
    },
}

</script>

<style scoped>

.form-group {
  margin-top: 15px;
  font-family: Arial, Helvetica, sans-serif;
}
.login-form {
  
  font-family: Arial, Helvetica, sans-serif;
  width: 346px;
  text-align: center;
  top: 65%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin: auto;
  position: absolute;
}

.login-form .login-password {
  background-color: white;
  -webkit-appearance: none;
  height: 45px;
  border-radius: 4px;
  border: none;
  width: 301px;
  display: block;
  box-shadow: rgba(0, 0, 0, 0.1) 3px 3px 5px;
  font-size: 13px;
}
.login-form .login-submit {
  -webkit-appearance: none;
  height: 45px;
  border-radius: 4px;
  border: none;
  width: 301px;
  display: block;
  box-shadow: rgba(0, 0, 0, 0.1) 3px 3px 5px;
  font-size: 13px;
}
.login-form label {
  height: 45px;
  width: 45px;
  background: white;
  display: inline-block;
  border-radius: 0 4px 4px 0;
  margin-left: -2px;
}
.login-form label > svg {
  width: 15px;
  height: 100%;
  text-align: center;
  fill: #2b5876;
  vertical-align: middle;
}
.login-form .login-password {
  padding: 0 20px 0 20px;
  float: left;
  border-radius: 4px 0 0 4px;
  box-sizing: border-box;
}
.login-form .login-submit {
  color: white;
  background: #FFB300;
  width: 100%;
  cursor: pointer;
  font-size: 14px;
  font-weight: bold;
}
.login-form .login-submit:hover,
.login-form .login-submit:active {
  background: #FFB300;
  outline: none;
}
.login-form input:focus,
.login-form input:focus {
  outline: 0;
}
.login-form h1,
.login-form p,
.login-form a {
  color: white;
  margin: 10px 0 10px 0;
  text-decoration: none;
}
.login-form h1 {
  font-weight: 100;
  margin-bottom: 20px;
  font-size: 42px;
  font-family: "Open Sans", sans-serif;
}
.login-form .login-motd {
  font-size: 14px;
  width: 300px;
  margin: 0 auto 20px auto;
}


</style>
