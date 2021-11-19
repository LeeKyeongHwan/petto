<template>
    <div align="center" style="padding-top: 60px;">
        <p class="normalText">비밀번호를 입력하세요</p>
            <label>
                <input type="password" v-model="password" style="width: 200px; color: black;"/>
                <v-btn @click="chkPassword"> 확인</v-btn>
            </label>
    </div>
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
