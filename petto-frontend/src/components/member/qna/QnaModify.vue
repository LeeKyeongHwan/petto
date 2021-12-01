  
<template>
    <div>
        <form @submit.prevent="onSubmit">
            <v-container style="margin-top: 50px;">
                <table>
                    <tr>
                        <td >글번호</td>
                        <td><input type="text" :value="qnaboard.qnaNo" disabled></td>
                    </tr>
                    <tr>
                        <td>제목</td>
                        <td><input type="text" v-model="title"></td>
                    </tr>
                    <tr>
                        <td >본문</td>
                        <td><textarea style="width:100%; min-height:300px; padding:10px;" v-model="content"></textarea></td>
                    </tr>
                </table>
            </v-container>

            <div>
                <button type="submit" style="margin-bottom: 70px;">수정 완료</button>
                <router-link :to="{ name: 'QnaReadPage', params: { qnadNo: qnaboard.qnaNo.toString() } }">
                    취소
                </router-link>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: 'QnaModify',
    props: {
        qnaboard: {
            type: Object,
            required: true
        }
    },
    data () {
        return {

        }
    },
    methods: {
        onSubmit () {
            const { qnaNo, title, writer,content, regDate, answerState, adminAnswer } = this
            this.$emit('submit', { qnaNo, title, content, writer, regDate, answerState, adminAnswer })
        }
    },
    created () {
        this.qnaNo = this.qnaboard.qnaNo
        this.title = this.qnaboard.title
        this.content = this.qnaboard.content
        this.writer = this.qnaboard.writer
        this.regDate = this.qnaboard.regDate
        this.answerState = this.qnaboard.answerState
        this.adminAnswer = this.qnaboard.adminAnswer

    }
}
</script>




