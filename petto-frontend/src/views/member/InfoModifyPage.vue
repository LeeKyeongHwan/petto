<template>
    <div align="center" style="padding-top: 60px;">
        <p class="normalText">생년월일과 가입시 입력했던 이메일을 입력해주세요.</p>
        
        <info-modify v-if="userInfo" v-bind:userInfo="userInfo" @submit="onSubmit"/>
        <p v-else>정보를 불러오고있습니다. 잠시만 기다려주세요.</p>

    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import InfoModify from '../../components/member/InfoModify.vue'

export default {
    name: 'InfoModifyPage',
    components: {
        InfoModify
    },
    methods: {
        ...mapActions(['fetchUserInfo']),

        onSubmit(payload) {
            axios.put('http://localhost:8888/petto/member/modifyUserInfo', payload)
                .then(() => {
                    alert('회원님의 정보가 수정되었습니다.' + payload)

                    this.$router.push({
                        name: "PettoHome"
                    })
                })
                .catch(() => {
                    alert('잠시 후에 다시 시도해주세요!')
                })
        }
    },
    mounted() {
        var userNo = 1 //추후에 변경
        this.fetchUserInfo(userNo)
    },
    computed: {
        ...mapState(['userInfo'])
    }
}
</script>