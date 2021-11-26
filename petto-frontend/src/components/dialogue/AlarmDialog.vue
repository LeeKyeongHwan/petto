<template>
    <div>
        <v-dialog v-model="dialog" persistent max-width="400">

        <template v-slot:activator="{ on }">

            <v-btn text v-on="on">
                <v-icon color="error">
                    add_alert
                </v-icon>
            </v-btn> 

        </template>

        <v-card style="position: fixed; top: 80px; right: 80px; width: 400px;">

            <v-card-title>

                <p class="normalText">계정 알림사항</p>

            </v-card-title>

            <v-card-text>
                <!-- <v-layout wrap> 이게 있으면 세로로 안나옴 -->
                <ul v-for="(alarm, idx) in updateAlarmList" :key="idx">
                    <li v-if="alarm.id === '관리자'" style="display: inline-block; width: 300px; font-size: 12px;" @click="toAnswerPage(alarm.postNo)">

                        {{ alarm.commentator }}회원님이 Q&A 게시판에 '{{ alarm.title }}' 글을 작성했어요!
                        <br>
                    </li>

                    <li v-else-if="alarm.commentator === '관리자'" style="display: inline-block; width: 300px; font-size: 12px;" @click="toQnAPage(alarm.postNo)">

                        {{ alarm.commentator }}가 회원님의 Q&A '{{ alarm.title }}' 글에 답변했어요! 
                        <br>
                    </li>

                    <li v-else style="display: inline-block; width: 300px; font-size: 12px;" @click="toDetailPage(alarm.postNo)">

                        {{ alarm.commentator }}님이 {{ alarm.regDate }}시에 '{{ alarm.title }}' 글에 답변했어요! 
                        <br>
                    </li>

                    <v-btn text x-small style="float: right;" @click="deleteAlaram(alarm.alarmNo, idx)">
                        <v-icon color="#D5D5D5">
                            cancel
                        </v-icon>
                    </v-btn>
                </ul>
                        
                <!-- </v-layout> -->
            </v-card-text>
            
            <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn text @click="deleteAllAlarms" class="normalText">
                    전체 삭제
                </v-btn>

                <v-btn text @click="cancel" class="normalText">
                    닫기
                </v-btn>
            </v-card-actions>

        </v-card>
    </v-dialog>

    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'AlarmDialog',
    props: {
        updateAlarmList: {
            type: Array,
            required: true
        }
    },
    data() {
        return {
            dialog: false
        }
    },

    methods: {
        cancel() {
            this.dialog = false
        },

        toAnswerPage(postNo) {

            let routeData = this.$router.resolve({
                name: 'AdminQnaReadPage',
                params: { qnaNo: postNo }
            })
            window.open(routeData.href, '_blank')
        },

        toDetailPage(postNo) {

            let routeData = this.$router.resolve({
                name: 'ReportReadPage',
                params: { reportNo: postNo }
            })
            window.open(routeData.href, '_blank')
        },

        toQnAPage(postNo) {

            let routeData = this.$router.resolve({
                name: 'QnaReadPage',
                params: { qnaNo: postNo }
            })
            window.open(routeData.href, '_blank')
        },

        deleteAlaram(alarmNo, idx) {

            axios.delete(`http://localhost:8888/petto/member/delete_alarm/${alarmNo}`)
                .then(() => {
                    this.updateAlarmList.splice(idx, 1)
                })
                .catch(err => {
                    console.log(err.message)
                })
        },

        deleteAllAlarms() {

            const id = this.$store.state.session.id
            
            axios.delete(`http://localhost:8888/petto/member/deleteAllAlarms/${id}`)
                .then(() => {
                    this.updateAlarmList = []
                })
                .catch(err => {
                    console.log(err.response.message)
                })
        }
    }
}
</script>

<style>

li {
    cursor: pointer;
}

</style>