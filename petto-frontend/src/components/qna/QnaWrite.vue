<template>
    <form @submit.prevent="onSubmit">
        <v-container>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" v-model="title"></td>
                </tr>
                <tr>
                    <td>본문</td>
                    <td><textarea cols="50" rows="20" v-model="content"></textarea></td>
                </tr>
            </table>
        </v-container>

        <div>
            <button type="submit">등록</button>
            <router-link :to="{ name: 'MyQna' }">
                취소
            </router-link>
        </div>
    </form>
</template>

<script>
export default {
    name: 'QnaWriteForm',
    data () {
        return {
            title: '제목을 작성하세요.',
            writer: '', 
            content: '본문을 작성하면 됩니다.',
            answerState: '',
            adminAnswer: ''
        }
    },
    methods: {
        onSubmit () {
            const { title, content,answerState, adminAnswer } = this
            const { writer } = this.$store.state.session.id
            this.$emit('submit', { title, writer, content, answerState, adminAnswer })
        }
    },

}
</script>