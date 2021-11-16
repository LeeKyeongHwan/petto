<template>
    <div>
        <form @submit.prevent="onSubmit">
            <table style="width:500px;">
            
                <tr>
                    <td>권한</td>
                    <td>
                        <v-radio-group v-model="radioGroup" row>
                            <v-radio v-for="kinds in kindsOfMember" :key="kinds" :label="`${kinds}`"></v-radio>
                        </v-radio-group> 
                    </td>
                </tr>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" :value="adminMember.id" disabled></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="text" :value="adminMember.email" disabled></td>
                </tr>
                <tr>
                    <td>전화번호</td>
                    <td><input type="text" :value="adminMember.phoneNumber" disabled></td>
                </tr>
                <tr>
                    <td>이름</td>
                    <td><input type="text" :value="adminMember.name" disabled></td>
                </tr>
                <tr>
                    <td>생일</td>
                    <td><input type="text" :value="adminMember.birthday" disabled></td>
                </tr>
                <tr>
                    <td>닉네임</td>
                    <td><input type="text" :value="adminMember.nickname" disabled></td>
                </tr>
                <tr>
                    <td>반려동물 유무</td>
                    <td><input type="text" :value="adminMember.petsRaised" disabled></td>
                </tr>
                <tr>
                    <td>등록일자</td>
                    <td><input type="text" :value="adminMember.regDate" disabled></td>
                </tr>
            </table>

            <div>
                <button type="submit">수정 완료</button>
                <router-link :to="{ name: 'AdminMemberReadPage', params: { memberNo: adminMember.memberNo.toString() } }">
                    취소
                </router-link>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: 'AdminMemberModify',
    props: {
        adminMember: {
            type: Object,
            required: true
        }
    },
    data () {
        return {
            radioGroup: 1,
            kindsOfMember: [
                '개인',
                '관리자'
            ]
        }   
    },
    methods: {
        onSubmit () {
            const { radioGroup } = this
            const auth = radioGroup == 0 ? '개인' : '관리자'
            this.$emit('submit', { auth })
        }
    }
}
</script>