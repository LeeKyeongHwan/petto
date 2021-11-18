<template>
    <div>
        <v-container>
        <table>
            <tr>
                <td>글번호</td>
                <td><input type="text" :value="qnaboard.qnaNo" readonly></td>
            </tr>
            <tr>
                <td>등록일자</td>
                <td><input type="text" :value="qnaboard.regDate" readonly></td>
            </tr>
            <tr>
                <td>제목</td>
                <td><input type="text" :value="qnaboard.title" readonly></td>
            </tr>
            <tr>
                <td>작성자</td>
                <td><input type="text" :value="qnaboard.writer" readonly></td>
            </tr>
            <tr>
                <td>본문</td>
                <td><textarea cols="50" rows="20" :value="qnaboard.content" readonly></textarea></td>
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