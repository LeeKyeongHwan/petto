<template>
    <div align="center">
        <h2>회원 정보 수정</h2>
        <admin-member-modify v-if="adminMember" :adminMember="adminMember" @submit="onSubmit"/>
        <p v-else>로딩중 ......</p>
    </div>
</template>

<script>

import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import AdminMemberModify from '../../components/admin/AdminMemberModify.vue'

export default {
    name: 'AnimalMemberModifyPage',
    components: {
        AdminMemberModify
    },
    props: {
        memberNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['adminMember', 'isLoggedIn', 'session'])
    },
    methods: {
        ...mapActions(['fetchAdminMember', 'fetchAlarmList']),
        onSubmit (payload) {
            const { auth } = payload
            axios.put(`http://localhost:8888/petto/admin/${this.memberNo}`, { auth })
                    .then(res => {
                        alert('수정하였습니다.')
                        this.$router.push({
                            name: 'AdminMemberReadPage',
                            params: { memberNo: res.data.memberNo.toString() }
                        })
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        }
    },
    created () {
        this.fetchAdminMember(this.memberNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.back()
                })
    },

    mounted() {
        if(this.$cookies.isKey("user")) {
  
            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)
                
                this.$store.state.isLoggedIn = true;
            }
        }
    }
}
</script>