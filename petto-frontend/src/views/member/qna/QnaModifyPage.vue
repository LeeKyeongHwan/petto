<template>
    <div align="center">
        <h2>게시물 수정</h2>
        <qna-modify v-if="qnaboard" :qnaboard="qnaboard" @submit="onSubmit"/>
        <p v-else>로딩중 .......</p>
    </div>
</template>

<script>
import QnaModify from '@/components/member/qna/QnaModify'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'QnaModifyPage',
    components: {
        QnaModify
    },
    props: {
        qnaNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['qnaboard'])
    },
    methods: {
        ...mapActions(['fetchQnA']),
        onSubmit (payload) {
            
            const { qnaNo, title, writer, content,regDate, answerState, adminAnswer } = payload
            
            axios.put(`http://localhost:8888/petto/qna/modify`, { qnaNo, title, writer, content, regDate, answerState, adminAnswer })
                    .then( ()=> {
                        alert('수정 성공!')
                        this.$router.push({
                            name: 'MyQna'
                        })
                    })
                    .catch(err => {
                        alert(err)
                    })
        }
    },
    created () {
        this.fetchQnA(this.qnaNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.back()
                })
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