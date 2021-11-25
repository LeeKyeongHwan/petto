<template>
    <div>
        <v-container class="justify-center">
            <v-card style="float:left; margin-right:2vw; overflow:hidden;">
                <v-form style="width:500px;">
                    <h3 style="font-size:30px;">문의 내용</h3>
                    <tr>
                        <td>등록일자</td>
                        <td><input type="text" :value="adminQnA.regDate" readonly></td>
                    </tr>
                    <tr>
                        <td>답변여부</td>
                        <td>
                            <v-chip :color="getColor(adminQnA.answerState)" style="width:90px; text-align:center;">
                                <input type="text" :value="adminQnA.answerState" readonly style="color:white; border:0; margin:0;">
                            </v-chip></td>
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
                        <td><v-textarea cols="40" rows="20"  :value="adminQnA.content" readonly  outlined height="200"></v-textarea></td>
                    </tr>
                    <!-- <tr v-if="adminQnA.adminAnswer != '답변대기'">
                        <td>답변</td>
                        <td><v-textarea cols="40" rows="20" :value="adminQnA.adminAnswer" readonly  
                          outlined height="200"></v-textarea></td>
                    </tr> -->
                </v-form>
            </v-card>

            <v-card  style="float:left; overflow:hidden;">
                <form style="width:500px;">
                    <h3 style="font-size:30px;">관리자 답변</h3>
                    <tr v-if="adminQnA.adminAnswer != '답변대기'">
                        <td>등록된<br> 답변</td>
                        <td><v-textarea cols="40" rows="20" :value="adminQnA.adminAnswer" readonly  
                          outlined height="100"></v-textarea></td>
                    </tr>
                    <tr>
                        <td>문의상태</td>
                        <td>
                            <v-radio-group v-model="radioGroup" row>
                                <v-radio color="orange" v-for="kinds in answerStates" :key="kinds" :label="`${kinds}`"></v-radio>
                            </v-radio-group>
                        </td>
                    </tr>

                    <tr>
                        <td>답변</td>
                        <td><v-textarea cols="40" rows="20" v-model="adminAnswer"
                        outlined height="200"></v-textarea></td>
                    </tr>
                    <span>
                        <v-btn @click="complete()" icon x-large color="orange">
                                ...send<v-icon>send</v-icon></v-btn>
                    </span>
                </form>

                
            </v-card>
        </v-container>

        <section style="content: '';display: block;clear: both;">
        <v-container class="justify-center">
            <!-- <span> -->
                <v-btn route :to="{ name: 'QnaListPage' }" style="margin-top:5%;" 
                color="orange" outlined rounded plain>
                목록보기
                </v-btn>
            <!-- </span> -->
        </v-container>
        </section>
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
        getColor (answer) {
            if (answer =='답변완료') return 'green'
            else if (answer == '답변대기') return 'orange'
        },
        complete() {
            const { qnaNo } = this.adminQnA
            const { radioGroup, adminAnswer } = this
            const answerState = radioGroup == 0 ? '답변완료' : '답변대기'
            console.log(answerState)
            axios.put(`http://localhost:8888/petto/admin/qna/${qnaNo}`, { answerState, adminAnswer })
                    .then(() => {
                        
                        const id = this.adminQnA.writer
                        const commentator = '관리자'
                        const title = this.adminQnA.title
                        const typeOfPost = 'Q&A'
                        const postNo = this.adminQnA.qnaNo

                        axios.post('http://localhost:8888/petto/member/update_alarm', { id, commentator, title, typeOfPost, postNo })
                            .catch(err => {
                                console.log(err.response.massage)
                            })
                            
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

<style scoped>
@font-face {
    font-family: 'ChosunSm';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.1/ChosunSm.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'MapoGoldenPier';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoGoldenPierA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
h3{
    font-family: 'ChosunSm';
}
p{
    font-family: 'MapoGoldenPier';
    font-size: 15px;
    margin: 0;
}
</style>