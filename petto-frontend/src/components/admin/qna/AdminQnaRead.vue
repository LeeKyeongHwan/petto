<template>
    <div>
    <section>
        <div id="board">
            <h3 style="font-size:30px; text-align:left;"><b>문의 내용</b></h3>
            <v-row align="center" >
            <v-divider ></v-divider>
            </v-row>

                <v-row align="center" style="margin-top:1vw;">
                    <v-chip :color="getColor(adminQnA.answerState)" style="width:90px; color:white; border:0; margin:0;">{{adminQnA.answerState}}
                    </v-chip>
                </v-row>

                <span><p style="font-size:20px; text-align:left; margin-bottom:0.5vw;">{{adminQnA.title}}</p></span>

                <span><p style="text-align:left; margin-bottom:0.5vw;"><v-icon style="margin-right:0.5vw;">person</v-icon>{{adminQnA.writer}}</p></span>
                <span><p style="text-align:left;">{{adminQnA.regDate}}</p></span>

                <span><p style="font-size:17px; text-align:left;">{{adminQnA.content}}</p></span>

            <v-row v-if="adminQnA.adminAnswer != '답변대기'" align="center" style="margin-top:1vw;">
                <v-card style="width:50vw;">
                    <v-card-text><p style="text-align:left;"><v-icon>subdirectory_arrow_right</v-icon><b>관리자 답변 :</b> {{adminQnA.adminAnswer}}</p></v-card-text>
                </v-card>
            </v-row>

            <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="500"
    >
      <template v-slot:activator="{ on, attrs }">
          <v-row align="center" style="margin:3%;" justify="center">
            <v-btn route :to="{ name: 'QnaListPage' }"
            color="gray" outlined plain>
            목록보기
            </v-btn>
            <v-btn
            color="gray"
            style="margin:0% 1% 0% 1%;"
            v-bind="attrs"
            v-on="on"
            >
            답변 작성
            </v-btn>
          </v-row>
      </template>
      <v-card>
        <v-card-title class="text-h5">
          관리자 답변
        </v-card-title>
        <v-card-subtitle>
            <span>
                <v-radio-group v-model="radioGroup" row>
                    <v-radio color="orange" v-for="kinds in answerStates" :key="kinds" :label="`${kinds}`"></v-radio>
                </v-radio-group>
            </span>
        </v-card-subtitle>
        <v-card-text>
            <td><v-textarea cols="55" rows="30" v-model="adminAnswer" label="답변"
                outlined height="200"></v-textarea></td>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="orange darken-1"
            text
            @click="dialog = false"
          >
            취소
          </v-btn>
          <v-btn
            color="orange darken-1"
            text
            @click="complete()"
          >
            등록
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
        </div>
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
            dialog: false,
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
                        this.dialog = false
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

#board{
    position: relative;
    left:25vw;
    top:0vw;
}
#nav{
    overflow: hidden;
}
</style>