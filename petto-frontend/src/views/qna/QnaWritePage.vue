<template>
    <div align="center">
        <qna-write @submit="onSubmit"/>
    </div>
</template>

<script>
import QnaWrite from '@/components/qna/QnaWrite.vue'
import axios from 'axios'
export default {
    name: 'QnaWritePage',
    components: {
        QnaWrite
    },
    methods: {
        onSubmit (payload) {
            const { title, content, writer= this.$store.state.session.id, answerState, adminAnswer} = payload
            axios.post('http://localhost:8888/petto/qna/register', { title, writer, content, answerState, adminAnswer })
                    .then(res => {
                        alert('등록 성공! - ' + res)
                        this.$router.push({
                            name: 'MyQna'
                        })
                    })
                    .catch(res => {
                        alert(res.response.data.message)
                    })
        }
    }
}
</script>