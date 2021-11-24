<template>
    <div style="margin-top: 60px; width: 300px; margin-right: 60px;">
        <form>
            <label style="float: right;">
                <h7 style="margin-right: 20px;">id</h7>
                <input v-model="id" style="width: 200px;" required readonly/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">이메일</h7>
                <input v-model="email" style="width: 200px;" required readonly/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">휴대번호</h7>
                <input v-model="phoneNumber" style="width: 200px;" required readonly/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">이름</h7>
                <input v-model="name" style="width: 200px;" required readonly/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">생년월일(6자)</h7>
                <input v-model="birthday" style="width: 200px;" required readonly/>
            </label>

            <br/>
            <br/>

            <label style="float: right;">
                <h7 style="margin-right: 20px;">닉네임</h7>
                <input v-model="nickname" style="width: 200px;" required readonly/>
            </label>
            
            <div style="margin-top: 120px; margin-left: 60px; margin-bottom: 40px;" >


                <v-btn text class="btnText" color="blue" style="font-size:16px;" href="passwordCheckPage">
                    정보 수정
                </v-btn>
                <v-btn text class="btnText" color="red" style="font-size:16px;" href="passwordCheckWithdrawalPage">
                    탈퇴
                </v-btn>
            <div style="margin-top: 120px; margin-left: 60px;">


                <v-btn text class="btnText" color="blue" style="font-size:16px;" href="passwordCheckPage">
                    정보 수정
                </v-btn>
                <v-btn text class="btnText" color="red" style="font-size:16px;" href="passwordCheckWithdrawalPage">
                    탈퇴

                <v-btn text class="btnText" color="blue" style="font-size:16px;" href="passwordCheckPage">
                    정보 수정
                </v-btn>
                <v-btn text class="btnText" color="red" style="font-size:16px;" href="passwordCheckWithdrawalPage">
                    탈퇴
                </v-btn>
            </div>
            </div>

        </form>

    </div>

</template>

<script>

export default {
    name: 'MyprofileForm',
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
}
</script>