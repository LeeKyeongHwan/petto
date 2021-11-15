<template>
<div id="member">
    <h1>회원목록</h1>
     <member-list :members="members"/>
</div>
</template>


<script>
import MemberList from '@/components/member/MemberList.vue'
import { mapState, mapActions } from 'vuex'
export default {
    name: 'AdminPage',
    components: {
        MemberList
    },
    
    computed: {
        ...mapState(['members'])
    },
    mounted () {
        this.fetchMemberList()
    },
    
    methods: {
        ...mapActions(['fetchMemberList'])
    }
}
</script>