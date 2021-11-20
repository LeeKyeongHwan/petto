<template>
    <div>
        <table style="width:1000px; margin:50px;">
            <h2 style="font-size:30px;">문의 내용</h2>
            <tr>
                <td>등록일자</td>
                <td><input type="text" :value="adminQnA.regDate" readonly></td>
            </tr>
            <tr>
                <td>답변여부</td>
                <td><input type="text" :value="adminQnA.answerState" readonly></td>
            </tr>
            <tr>
                <td>제목</td>
                <td><input type="text" :value="adminQnA.title" readonly></td>
            </tr>
            <tr>
                <td>작성자</td>
                <td><input type="text" :value="adminQnA.writer" readonly></td>
            </tr>
            <tr>
                <td>본문</td>
                <td><textarea cols="50" rows="20" :value="adminQnA.content" readonly  
                style="height:200px;"></textarea></td>
            </tr>
            <tr v-if="adminQnA.adminAnswer != '답변대기'">
                <td>답변</td>
                <td><textarea cols="50" rows="20" :value="adminQnA.adminAnswer" readonly  
                style="height:200px;"></textarea></td>
            </tr>
        </table>

        
        <table style="width:1000px; margin-bottom:100px;">
            <h2 style="font-size:30px;">관리자 답변</h2>
             <tr>
                <td>문의상태</td>
                <td>
                    <v-radio-group v-model="radioGroup" row>
                        <v-radio v-for="kinds in answerStates" :key="kinds" :label="`${kinds}`"></v-radio>
                    </v-radio-group>
                </td>
            </tr>

            <tr>
                <td>답변</td>
                <td><textarea cols="50" rows="20" v-model="adminAnswer" style="height:200px;"></textarea></td>
                <td>
                    <v-btn @click="complete()" icon><v-icon>done</v-icon></v-btn>
                </td>
            </tr>

        </table>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'BoardRead',
    props: {
        adminQnA: {
            type: Object,
            required: true
        }
    },
    data () {
        return {
            radioGroup: 1,
            answerStates: [
                '답변완료',
                '답변대기'
            ],
            adminAnswer: ''
        }   
    },   
    methods: {
        complete () {
            const { qnaNo } = this.adminQnA
            const { radioGroup, adminAnswer } = this
            const answerState = radioGroup == 0 ? '답변완료' : '답변대기'
            console.log(answerState)
            axios.put(`http://localhost:8888/petto/admin/qna/${qnaNo}`, { answerState, adminAnswer })
                    .then(() => {
                        alert('답변을 작성했습니다.')
                        this.$router.push({
                            name: 'QnaListPage'
                        })
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        }
    }
}
</script>