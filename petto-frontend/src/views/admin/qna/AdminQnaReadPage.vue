<template>
  <div>
      <v-container id="nav">
            <admin-page-side-bar/>
        </v-container>
    <div align="center" style="margin:5% 0% 15% 0%;">
        <admin-qna-read v-if="adminQnA" :adminQnA="adminQnA"/>
        <p v-else>로딩중 ...... </p>
    </div>
  </div>
</template>

<script>
import AdminQnaRead from '@/components/admin/qna/AdminQnaRead.vue'
import AdminPageSideBar from '@/components/admin/AdminPageSideBar.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name: 'AdminQnaReadPage',
    props: {
        qnaNo: {
            type: Number,
            required: true
        }
    },
    components: {
        AdminQnaRead,
        AdminPageSideBar
    },
    computed: {
        ...mapState(['adminQnA'])
    },
    data() {
        return {
            access: ''
        }
    },
    created () {
        this.fetchAdminQnA(this.qnaNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.push()
                })
    },
    methods: {
        ...mapActions(['fetchAdminQnA'])
    },
    mounted() {
        if(this.$cookies.isKey('user') == true){
            this.access = this.$cookies.get('user').auth
            this.$store.state.session = this.$cookies.get("user");
            if(this.access != '관리자'){
                alert('권한이 필요한 서비스입니다')
                this.$router.push({
                name:'PettoHome',
                })
            }
        } else {
            alert('권한이 필요한 서비스입니다')
            this.$router.push({
            name:'PettoHome',
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