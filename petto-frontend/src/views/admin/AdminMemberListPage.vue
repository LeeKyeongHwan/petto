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
        ...mapState(['adminMembers'])
    },
    mounted () {
        this.fetchAdminMemberList()
    },
    methods: {
        ...mapActions(['fetchAdminMemberList'])
    }
}
</script>