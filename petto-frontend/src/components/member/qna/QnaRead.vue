<template>
    <div>
        <v-container>
        <table>
 
            <tr>
                <td>답변상태</td>
                <td><input type="text" :value="qnaboard.answerState" readonly></td>
            </tr>
            <tr>
                <td>제목</td>
                <td><input type="text" :value="qnaboard.title" readonly></td>
            </tr>
            <tr>
                <td>본문</td>
                <td><textarea style="width:100%; min-height:300px; padding:10px;" :value="qnaboard.content" readonly></textarea></td>
            </tr>
            <tr v-if="qnaboard.answerState != '답변대기'">
                <td>답변</td>
                <td><textarea cols="50" rows="20" :value="qnaboard.adminAnswer" readonly></textarea></td>
            </tr>
        </table>
        </v-container>
    </div>
</template>

<script>
export default {
    name: 'QnaRead',
    props: {
        qnaboard: {
            type: Object,
            required: true
        }
    }    
}
</script>