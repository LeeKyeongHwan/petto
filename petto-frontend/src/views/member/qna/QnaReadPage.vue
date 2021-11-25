<template>
    <div align="center">
        <h2>내 질문</h2>
        <qna-read v-if="qnaboard" :qnaboard="qnaboard"/>
        <p v-else>로딩중 ...... </p>
        <router-link :to="{ name: 'QnaModifyPage', params: { qnaNo } }">
            게시물 수정
        </router-link>
        <button @click="onDelete">삭제</button>
    </div>
</template>

<script>
import QnaRead from '@/components/member/qna/QnaRead.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
    name: 'QnaReadPage',
    props: {
        qnaNo: {
            type: String,
            required: true
        }
    },
    components: {
        QnaRead
    },
    computed: {
        ...mapState(['qnaboard'])
    },
    created () {
         this.fetchQnA(this.qnaNo)
                 .catch(err => {
                     alert(err.response.data.message)
                     this.$router.push()
                 })
    },
    methods: {
        ...mapActions(['fetchQnA']),
        onDelete () {
            const { qnaNo } = this.qnaboard
            axios.delete(`http://localhost:8888/petto/qna/${qnaNo}`)
                .then(() => {
                    alert('삭제 성공!')
                    this.$router.push({ name: 'MyQna'})
                })
                .catch(err => {
                    alert(err.response.data.message)
                })
        }
    },

    mounted() {
        this.fetchMyLikedAnimalList(this.$store.state.session.memberNo) 
        
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