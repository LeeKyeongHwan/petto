<template>
    <div align="center">
        <qna-write @submit="onSubmit"/>
    </div>
</template>

<script>
import QnaWrite from '@/components/member/qna/QnaWrite.vue'
import axios from 'axios'
export default {
    name: 'QnaWritePage',
    components: {
        QnaWrite
    },
    data() {
        return {
            answerState: '답변대기',
            adminAnswer: '답변대기'
        }
    },
    methods: {
        onSubmit (payload) {
            // const { title, content, writer= this.$store.state.session.id, answerState, adminAnswer} = payload
            const { title,  writer = this.$store.state.session.id , content } = payload
            const { answerState, adminAnswer } = this
            axios.post('http://localhost:8888/petto/qna/register', { title, writer, content, answerState, adminAnswer })
                    .then(res => {

                        alert(res.data)

                        const id = '관리자'
                        const commentator = this.$store.state.session.id
                        const title = payload.title
                        const typeOfPost = 'Q&A'
                        const postNo = res.data

                        axios.post('http://localhost:8888/petto/member/update_alarm', { id, commentator, title, typeOfPost, postNo })
                            .catch(err => {
                                console.log(err.response.massage)
                            })

                        alert('등록 성공! - ' + res)
                        this.$router.push({
                            name: 'MyQna'
                        })
                    })
                    .catch(res => {
                        alert(res.response.data.message)
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