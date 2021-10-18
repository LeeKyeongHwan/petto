<template>
    <div align="center">
        <span>
            <v-btn text class="mainText" @click="onFindId">
                아이디 찾기
            </v-btn>

            <v-btn text class="mainText" @click="onFindPw">
                비밀번호 찾기
            </v-btn>
        </span>

        <div style="margin-top: 60px;">

            <div v-if="findId">
                <p class="normalText">가입시 입력했던 이메일을 입력해주세요.</p>

                <input class="mainText" v-model="email" style="width: 200px; color: black;"/>

                <v-btn text class="mainText" style="margin-top: 0px;" @click="checkValidEmail">
                    확인
                </v-btn>

                <p v-if="emailValidChk != ''" class="normalText" style="color: black;">{{ emailValidChk }}</p>
            </div>

            <div v-if="findPw">
                <p class="normalText">아이디와 가입시 입력했던 이메일을 입력해주세요.</p>

                <label>
                    아이디
                    <input class="mainText" v-model="id" style="width: 200px; color: black;"/>
                </label>

                <br/>

                <label>
                    이메일
                    <input class="mainText" v-model="email" style="width: 200px; color: black;"/>
                </label>

                <br/>

                <v-btn text class="mainText" style="margin-top: 0px;" @click="checkValidEmail">
                    확인
                </v-btn>

                <div v-if="codeSent" style="margin-top: 30px;">

                    <p class="normalText" style="color: black;">이메일로 코드를 전송했습니다. 해당 코드를 입력해주세요.</p>

                    <input class="mainText" v-model="code" style="width: 200px; color: black;"/>

                    <br/>

                    <v-btn text class="mainText" style="margin-top: 0px;" @click="checkCode">
                        확인
                    </v-btn>

                </div>

                <p v-else-if="!codeSent" class="normalText" style="margin-top: 30px; color: black;">{{ codeMsg }}</p>

            </div>

            <div v-if="changePw">

                <p class="normalText" style="color: black;">새로운 비밀번호와 비밀번호 확인을 입력해주세요.</p>

                <label>
                    비밀번호
                    <input type="password" class="mainText" v-model="password" style="width: 200px; color: black;"/>
                </label>

                <br/>

                <label>
                    재확인
                    <input type="password" class="mainText" v-model="passwordChk" style="width: 200px; color: black;"/>
                </label>

                <br/>

                <v-btn text class="mainText" style="margin-top: 0px;" @click="changePassword">
                    변경
                </v-btn>

            </div>

        </div>

    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'FindIdAndPwPage',
    data() {
        return {
            findId: false,
            findPw: false,

            id: '',
            email: '',

            emailValidChk: '',

            codeSent: false,

            code: '',
            codeMsg: '',

            changePw: false,

            password: '',
            passwordChk: ''
        }
    },

    methods: {
        onFindId() {
            this.findId = true
            this.findPw = false

            this.changePw = false

            this.id = ''
        },

        onFindPw() {
            this.findPw = true
            this.findId = false

            this.changePw = false
        },

        checkValidEmail() {

            let formData = new FormData()

            let email = this.email
            let id = this.id

            formData.append("email", email)
            formData.append("id", id)

            axios.post('http://localhost:8888/petto/member/checkValidEmail', formData)
            .then((res) => {
                if(res.data == 'EmailExists') {
                    
                    this.emailValidChk = '이메일로 id를 전송했습니다.'
                } else if(res.data == 'NotEmailExists') {

                    this.emailValidChk = '유효하지 않는 이메일입니다. 다시 확인해주세요 :)'
                } else {
                    this.$cookies.set("code", res.data, '300s')
                    
                    if(this.$cookies.get("code") != 'unvalid') {
                        this.codeSent = true

                    } else {
                        this.codeMsg = '아이디와 이메일이 올바르지않습니다.'
                        this.codeSent = false
                    }
                }
            })
            .catch(() => {
                alert('잠시 후에 다시 시도해주세요.')
            })
        },
        checkCode() {
            if(this.$cookies.isKey("code")) {

                if(this.code == this.$cookies.get("code")) {
                    this.changePw = true

                    this.findId = false
                    this.findPw = false
                    
                } else {
                    alert('확인 코드가 일치하지 않습니다.')
                }
            } else {
                alert('시간이 만료되었습니다.')
            }
        },

        changePassword() {

            if(this.password != "" && this.password == this.passwordChk) {
                
                const id = this.id
                const password = this.password

                axios.post('http://localhost:8888/petto/member/changePassword', { id, password })
                    .then(() => {
                        alert('비밀번호가 변경되었습니다.')

                        this.$router.push({
                            name: 'PettoHome'
                        })
                    })
                    .catch(() => {
                        alert('잠시 후에 다시 시도해주세요.')
                    })
            } else {
                alert('비밀번호와 확인을 일치시켜주세요.')
            }
        }
    }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@700&display=swap');

.normalText {
    font-family: 'Gowun Batang', serif;
    color: #42b8d4;
}

</style>