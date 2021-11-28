<template>
    <div align="center"> 

        <report-read v-bind:report="report" style="margin-bottom: 100px;"/>

        <v-container style="width: 51%; margin-bottom: 100px; margin-top: 50px;" v-if="report.expired == false">
            <label class="normalText" style="float: left; color: grey;">
                댓글 &emsp;
            </label>
            <input v-model="reply" style="width: 60%; float: left; margin-top: -15px; color: grey;" class="normalText" @keydown.enter="saveReply"/>

            <v-btn text class="normalText" style="float: right;" @click="saveReply">
                댓글달기
            </v-btn>

            <br><br><br><br>

            <v-simple-table v-if="replyList" class="normalText">
                <template v-slot:default>
                    <thead style="border: none;">
                        <tr>
                            <th class="text-left" style="width: 70px;">
                            작성자
                            </th>
                            <th class="text-left">
                            글
                            </th>
                            <th class="text-right">
                            날짜
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr v-for="(reply, idx) in replyList" :key="idx" @dblclick="editReply(idx)">

                            <td style="font-size: 14px;">{{ reply.writer }}</td>

                            <td v-show="targetModifyReply != idx" style="font-size: 14px;">{{ reply.replyContent }}</td>

                            <td v-show="targetModifyReply == idx" style="font-size: 14px;">
                                <input v-model="replyEdit"/>
                            </td>

                            <td v-show="targetIdx != idx" style="font-size: 14px; width: 150px;" class="text-right">{{ reply.regDate }}</td>

                            <td v-show="targetIdx == idx" style="font-size: 14px; width: 200px;" class="text-right">

                                <v-btn text small @click="modifyReply(reply.replyNo, idx, reply.replyContent, reply.regDate, reply.writer)" class="normalText" style="color: blue;">
                                    수정
                                </v-btn>

                                <v-btn text small @click="deleteReply(reply.replyNo, reply.writer)" class="normalText" style="color: red;">
                                    삭제
                                </v-btn>

                                <v-btn text small @click="cancel" class="normalText">
                                    취소
                                </v-btn>
                   
                            </td>
                        </tr>
                    </tbody>

                </template>
            </v-simple-table>
        </v-container>

    </div>
</template>
<script>
import { mapActions, mapState } from 'vuex'
import ReportRead from '@/components/report/ReportRead.vue'
import axios from 'axios'

export default {
    components: {
        ReportRead
    },
    props: {
        reportNo: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            reply: '',
            targetIdx: -1,
            targetModifyReply: -1,
            replyEdit: '',
        }
    },

    methods: {
        ...mapActions(["fetchReport", 'fetchReplyList']),

        saveReply() {
            if(this.session) {
                
                if(this.reply == '') {
                    alert('내용을 입력해주세요!')
                    return false
                }
                
                const reportNo = this.reportNo
                const writer = this.$store.state.session.id
                const replyContent = this.reply

                axios.post('http://localhost:8888/petto/report/saveReply', { reportNo, writer, replyContent })
                    .then(() => {
                        this.reply = ''

                        setTimeout(() => {
                            this.$store.dispatch("fetchReplyList", this.reportNo)
                        }, 100)

                        //////////////////// 댓글 작성 후 알람 생성

                        if(writer == this.report.writer) return false

                        const id = this.report.writer
                        const commentator = this.$store.state.session.id
                        const title = this.report.title
                        const typeOfPost = '공고 게시판'
                        const postNo = this.report.reportNo

                        axios.post('http://localhost:8888/petto/member/update_alarm', { reportNo, id, commentator, title, typeOfPost, postNo })
                            .catch(err => {
                                console.log(err.response.massage)
                            })
                    })
                    .catch((err) => {
                        alert(err)
                    })

            } else alert('로그인이 필요합니다.')
        },

        editReply(idx) {
            this.targetIdx = idx
            this.targetModifyReply = -1
            this.replyEdit = ''
        },

        cancel() {
            this.targetIdx = -1
            this.targetModifyReply = -1
            this.replyEdit = ''
        },

        deleteReply(replyNo, writer) {

            if(this.$store.state.session && this.$store.state.session.id == writer) {
            
                axios.delete(`http://localhost:8888/petto/report/deleteReply/${replyNo}`)
                    .then(() => {
                
                        const targerIndex = this.$store.state.replyList.findIndex(v => v.replyNo == replyNo)
                        this.replyList.splice(targerIndex, 1)

                        this.targetIdx = -1
                        this.targetModifyReply = -1

                    })
                    .catch((err) => {
                        alert(err)
                    })

            } else alert('권한이 없습니다!')
        },

        modifyReply(reply_no, idx, reply_content, reg_date, writer) {

            if(this.$store.state.session && this.$store.state.session.id == writer) {
                
                this.targetModifyReply = idx

                if(this.replyEdit == '') this.replyEdit = reply_content

                else if(this.replyEdit != '' && this.replyEdit != reply_content) {
                    
                    const replyNo = reply_no
                    const reportNo = this.reportNo
                    const writer = this.session.id
                    const replyContent = this.replyEdit
                    const regDate = reg_date
            
                    axios.put('http://localhost:8888/petto/report/modifyReply', { replyNo, reportNo, writer, replyContent, regDate })
                        .then(() => {
  
                            const targerIndex = this.$store.state.replyList.findIndex(v => v.replyNo == reply_no)
                            this.$store.state.replyList[targerIndex].replyContent = replyContent

                            this.targetIdx = -1
                            this.targetModifyReply = -1
                        })

                        .catch((err) => {
                            alert(err)
                        })
                    
                    this.replyEdit = ''
                }

            } else alert('권한이 없습니다!')
        }
    },

    computed: {
        ...mapState(['report', 'session', 'replyList']),
    },

    mounted() {
        this.$store.dispatch("fetchReport", this.reportNo)
            .then(() => {
                if(!this.$cookies.isKey("user").id || this.$cookies.get("user").id != this.report.writer) 
                axios.post(`http://localhost:8888/petto/report/plusViewCnt/${this.reportNo}`)
            })
            
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