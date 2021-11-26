<template>
    <div id="adminMember">
        <admin-member-list :adminMembers="adminMembers"/>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import AdminMemberList from '../../components/admin/AdminMemberList.vue'
export default {
    name: 'AdminMemberListPage',
    components: {
        AdminMemberList
    },
    computed: {
        ...mapState(['adminMembers', 'session', 'isLoggedIn'])
    },
    mounted () {
        this.fetchAdminMemberList()

        if(this.$cookies.isKey("user")) {
  
            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)
                
                this.$store.state.isLoggedIn = true;
            }
        }
    },
    methods: {
        ...mapActions(['fetchAdminMemberList', 'fetchAlarmList'])
    }
}
</script>