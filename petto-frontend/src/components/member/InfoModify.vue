<template>

    <div style="margin-top: 60px; width: 300px; margin-right: 60px;">

        <form @submit.prevent="onSubmitModified">
      
            <label style="float: right;">
                <h7 style="margin-right: 20px;">id</h7>
                <input v-model="id" style="width: 200px;" required/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">이메일</h7>
                <input v-model="email" style="width: 200px;" required/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">휴대번호</h7>
                <input v-model="phoneNumber" style="width: 200px;" required/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">이름</h7>
                <input v-model="name" style="width: 200px;" required/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">생년월일(6자)</h7>
                <input v-model="birthday" style="width: 200px;" required/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">닉네임</h7>
                <input v-model="nickname" style="width: 200px;" required/>
            </label>
            
            <div style="margin-top: 150px; margin-left: 60px;">

                <p v-show="idDupli" class="normalText" style="color: red">동일한 id가 존재합니다!</p>

                <p v-show="nicknameDupli" class="normalText" style="color: red">동일한 닉네임이 존재합니다!</p>

                <v-btn text class="btnText" type="submit">
                    제출
                </v-btn>

            </div>

        </form>

    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'InfoModify',
    props: {
        userInfo: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            id: this.userInfo.id,
            email: this.userInfo.email,
            phoneNumber: this.userInfo.phoneNumber,
            name: this.userInfo.name,
            birthday: this.userInfo.birthday,
            nickname: this.userInfo.nickname,

            idDupli: false,
            nicknameDupli: false
        }
    },
    methods: {
        onSubmitModified() {

            if(!this.idDupli && !this.nicknameDupli) {

                const memberNo = this.userInfo.memberNo
                const id = this.id
                const email = this.email
                const phoneNumber = this.phoneNumber
                const name = this.name
                const birthday = this.birthday
                const nickname = this.nickname

                this.$emit('submit', { memberNo, id, email, phoneNumber, name, birthday, nickname })

            } else {
                alert('id와 닉네임을 확인해주세요.')
            }
        }
    },
    watch: {
        id() {
            if(7 <= this.id.length) {
            
            const id = this.id

            axios.post(`http://localhost:8888/petto/member/idDupliChk/${ id }`)
                .then((res) => {
                    if(res.data) {

                        this.idDupli = false
                    } else {
                        this.idDupli = true
                    }
                })
                
            } else this.idDupli = false
        },
        nickname() {
            const nickname = this.nickname

            axios.post(`http://localhost:8888/petto/member/nicknameDupliChk/${ nickname }`)
                .then((res) => {
                    if(res.data) {

                        this.nicknameDupli = false
                    } else {
                        this.nicknameDupli = true
                    }
                })             
        }
    },    

}
</script>