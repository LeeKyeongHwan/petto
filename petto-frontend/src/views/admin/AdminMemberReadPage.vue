<template>
    <div>
        <v-container id="nav">
            <admin-page-side-bar/>
        </v-container>
        <div align="center">
            <h2>회원정보</h2>
            <admin-member-read v-if="adminMember" :adminMember="adminMember"/>
            <p v-else>로딩중 ...... </p>
            <router-link :to="{ name: 'AdminMemberModifyPage', params: { memberNo } }">
                게시물 수정
            </router-link>
            <button @click="onDelete">삭제</button>
            <router-link :to="{ name: 'AdminMemberListPage' }">
                게시물 보기
            </router-link>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import AdminMemberRead from '../../components/admin/AdminMemberRead.vue'
import AdminPageSideBar from '@/components/admin/AdminPageSideBar.vue'

export default {
    name: 'AdminMemberReadPage',
    props: {
        memberNo: {
            type: String,
            required: true
        }
    },
    components: {
        AdminMemberRead,
        AdminPageSideBar
    },
    computed: {
        ...mapState(['adminMember', 'session', 'isLoggedIn'])
    },
    mounted() {
        this.fetchAdminMember(this.memberNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.push()
                })

        if(this.$cookies.isKey("user")) {
  
            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)
                
                this.$store.state.isLoggedIn = true;
            }
        }
    },
    methods: {
        ...mapActions(['fetchAdminMember', 'fetchAlarmList']),
        onDelete () {
            const { memberNo } = this.adminMember
            axios.delete(`http://localhost:8888/petto/admin/${memberNo}`)
                .then(() => {
                    alert('회원을 삭제했습니다.')
                    this.$router.push({ name: 'AdminMemberListPage' })
                })
                .catch(res => {
                    alert(res.response.data.message)
                })
        }
    }
}
</script>

<style scoped>
#nav{
    overflow: hidden;
}
</style>