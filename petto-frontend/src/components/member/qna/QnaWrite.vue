<template>
    <form @submit.prevent="onSubmit">
        <h4 id="title"  style="width:50%; margin-top: 50px;">문의하기</h4>
        <v-container>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" v-model="title"></td>
                </tr>
                <tr>
                    <td>본문</td>
                    <td><textarea style="width:100%; min-height:300px; padding:10px;" v-model="content"></textarea></td>
                </tr>
            </table>
        </v-container>
        <div>
            <button style="margin-bottom:70px" type="submit">등록</button>
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
            content: '내용을 작성하면 됩니다.',
            // answerState: '',
            // adminAnswer: ''
        }
    },
    methods: {
        onSubmit () {
            const { title, content } = this
            const { writer } = this.$store.state.session.id
            this.$emit('submit', { title, writer, content })
        }
    },

}
</script>

<style scoped>
#title {
  font-family: "GowunDodum-Regular";
}
	
</style>