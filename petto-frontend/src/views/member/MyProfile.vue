<template>
    <div align="center" style="padding-top: 60px;">
        <h4><p class="normalText">내 정보</p></h4>
        <myprofile-form v-if="userInfo" v-bind:userInfo="userInfo"/>
        <p v-else>정보를 불러오고있습니다. 잠시만 기다려주세요.</p>

    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import MyprofileForm from '../../components/member/MyprofileForm.vue'

export default {
    name: 'MyProfile',
    components: {
        MyprofileForm
    },
    methods: {
        ...mapActions(['fetchUserInfo']),
    },
    mounted() {
        var userNo =this.$store.state.session.memberNo //추후에 변경
        this.fetchUserInfo(userNo)

        if(this.$cookies.get("user").id) {
        this.$store.state.session = this.$cookies.get("user")
        }
    },
    computed: {
        ...mapState(['userInfo'])
    }
}
</script>